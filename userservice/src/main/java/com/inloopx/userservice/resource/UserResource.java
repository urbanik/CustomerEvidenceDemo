package com.inloopx.userservice.resource;

import com.inloopx.userservice.dto.TokenDto;
import com.inloopx.userservice.dto.UserDto;
import com.inloopx.userservice.entity.User;
import com.inloopx.userservice.repository.UserRepository;
import com.inloopx.userservice.strucutremapper.TokenMapper;
import com.inloopx.userservice.strucutremapper.UserMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserResource {

    @EJB
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @Inject
    private TokenMapper tokenMapper;


    @POST
    @Path("/login")
    public TokenDto login(@Valid UserDto userDto) {

        return tokenMapper.tokenToTokenDto(userRepository.checkLogin(userMapper.userDtoToUser(userDto)));

    }

    @POST
    @Path("/register")
    public UserDto register(@Valid UserDto userDto) {

        User tmp = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.registerUser(tmp));

    }

}
