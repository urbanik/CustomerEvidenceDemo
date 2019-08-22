package com.inloopx.userservice.repository;

import com.inloopx.userservice.entity.*;
import com.inloopx.userservice.exception.ViolatedBusinessRule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

@Stateless
public class UserRepository extends BaseRepository<User> {

    @PersistenceContext(unitName = "userService")
    EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private String issuer;


    @Inject
    @ConfigProperty(name = "privateKey")
    private String privateKey;


    @EJB
    private RoleRepository roleRepository;

    @EJB
    private TokenRepository tokenRepository;

    @EJB
    OrderByParser orderByParser;

    public User registerUser(User user) {

        if (!isUniqueUsername(user.getUsername())) {
            throw new ViolatedBusinessRule("Username is already taken!");
        }

        String pass = user.getPassword();
        user.setPassword(hashPassword(pass));

        Optional<Role> maybeRole = roleExist(user);

        if (!maybeRole.isPresent()) {
            throw new ViolatedBusinessRule("Role does not exist!");
        }

        user.setRole(maybeRole.get());
        entityManager.persist(user);
        return user;

    }

    public Token checkLogin(User user) {

        Optional<User> maybeUser = userExist(user);

        if (!maybeUser.isPresent()) {

            throw new ViolatedBusinessRule("Username does not exists!");
        }

        if (!isPasswordValid(user.getPassword(), maybeUser.get().getPassword())) {
            throw new ViolatedBusinessRule("Wrong password!");
        }
        if (hasUserRefreshToken(maybeUser.get().getId())) {

            return refreshAccessToken(maybeUser.get().getId());

        } else {

            return createNewTokens(maybeUser);
        }

    }

    private boolean isUniqueUsername(String username) {
        QUser qUser = QUser.user;
        return getJpaQueryFactory().selectFrom(qUser)
                .where(qUser.username.eq(username))
                .fetchCount() <= 0;
    }

    private Optional<Role> roleExist(User user) {

        QRole qRole = QRole.role;
        Role role = getJpaQueryFactory()
                .select(qRole)
                .from(qRole)
                .where(qRole.name.eq(user.getRole().getName()))
                .fetchOne();

        return Optional.ofNullable(role);

    }

    private Optional<User> userExist(User user) {

        QUser qUser = QUser.user;
        User dbUser = getJpaQueryFactory()
                .select(qUser)
                .from(qUser)
                .where(qUser.username.eq(user.getUsername()))
                .fetchOne();

        return Optional.ofNullable(dbUser);

    }

    private boolean hasUserRefreshToken(int id) {
        QToken qToken = QToken.token;
        return getJpaQueryFactory().selectFrom(qToken)
                .where(qToken.user.id.eq(id))
                .fetchCount() > 0;
    }

    private Token refreshAccessToken(int id) {

        QToken qToken = QToken.token;
        Token token = getJpaQueryFactory().selectFrom(qToken)
                .where(qToken.user.id.eq(id))
                .fetchOne();

        QUser qUser = QUser.user;
        User user = getJpaQueryFactory()
                .select(qUser)
                .from(qUser)
                .where(qUser.id.eq(id))
                .fetchOne();

        Token newToken = token;
        newToken.setAccessToken(createAccessJWT(Integer.toString(user.getId()), user.getUsername(), user.getRole().getName(), 60000));
        tokenRepository.updateModel(token, newToken);
        return newToken;

    }

    private Token createNewTokens(Optional<User> maybeUser) {

        String id = Integer.toString(maybeUser.get().getId());
        Token token = new Token();
        token.setAccessToken(createAccessJWT(id, maybeUser.get().getUsername(), maybeUser.get().getRole().getName(), 60000));
        token.setRefreshToken(createRefreshJWT());
        token.setUser(maybeUser.get());
        return tokenRepository.saveModel(token);
    }

    private JPAQueryFactory getJpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    private static int workload = 12;

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return hashed_password;
    }

    public static boolean isPasswordValid(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return password_verified;
    }


    public String createAccessJWT(String id, String subject, String role, long ttlMillis) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("groups", Arrays.asList(role));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] keyBytes = Base64.getDecoder().decode(privateKey);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

        Key signingKey = null;
        
        try{
            
        KeyFactory kf = KeyFactory.getInstance("RSA");
        signingKey = kf.generatePrivate(spec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(subject)
                .addClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static String createRefreshJWT() {

        int length = 16;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);

        }
        return sb.toString();
    }


    public Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(privateKey))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

}
