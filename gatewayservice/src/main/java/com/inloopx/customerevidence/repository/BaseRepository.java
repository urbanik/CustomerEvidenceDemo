package com.inloopx.customerevidence.repository;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLOperators;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.internal.util.Pair;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.logging.Logger;

public abstract class BaseRepository<E> {

    protected Class<E> clazz;

    public BaseRepository(Class<E> clazz) {
        this.clazz = clazz;
    }

    public BaseRepository() {
    }

    public abstract EntityManager getEntityManager();

    @Inject
    Logger LOG;

    @EJB
    OrderByParser orderByParser;

    public E save(E entity) {
            getEntityManager().persist(entity);
            return entity;
    }

    public E saveModel(E entity) {
        return save(entity);
    }

    public Optional<E> getById(int id) {

        return Optional.ofNullable((E) getEntityManager().find(clazz, id));
    }

    public List<E> getAll() {
        Pair<List<E>, Long> pair = readList(null, null, null, null, false);
        return pair.getLeft();
    }

    public E update(E dbEntity){

        getEntityManager().merge(dbEntity);

        return  dbEntity;
    }

    public abstract E updateModel(E dbEntity, E requestEntity);

    public void delete(E entity) {

        getEntityManager().remove(entity);
    }

    public void deleteModel(E entityToDelete){
        delete(entityToDelete);
    }

    public Pair<List<E>, Long> readList(String filter,
                                        String sorting,
                                        Integer offset,
                                        Integer limit,
                                        boolean getTotalRowCount) {


        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        Pair<Root<E>, CriteriaQuery<E>> resultFilter = applyRSqlFilter(filter);
        Root<E> root = resultFilter.getLeft();
        CriteriaQuery<E> query = resultFilter.getRight();

        List<OrderBy> orderByList = orderByParser.parse(sorting);
        for (OrderBy orderBy : orderByList) {

            final Path<Object> fieldPath = getPathObjectRecursively(root, orderBy.getFieldName());
            if (orderBy.getDirection() == OrderByDirection.ASC) {
                query.orderBy(cb.asc(fieldPath));
            } else {
                query.orderBy(cb.desc(fieldPath));
            }
        }

        final TypedQuery<E> typedQuery = this.getEntityManager().createQuery(query);

        if (limit != null) {
            typedQuery.setMaxResults(limit);
        }
        if (offset != null) {
            typedQuery.setFirstResult(offset);
        }

        List<E> results = typedQuery.getResultList();
        Set<E> resultsSet = removeDuplicateRows(results);
        results = new LinkedList<>(resultsSet);
        Long totalCount = -1L;
        if (getTotalRowCount) {
            totalCount = JpaUtils.count(getEntityManager(), query);
        }


        return Pair.of(results, totalCount);

    }

    private Pair<Root<E>, CriteriaQuery<E>> applyRSqlFilter(String filter) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<E> query = null;
            Root<E> root;
            if (StringUtils.isNotBlank(filter)) {
                Node rootNode = new RSQLParser(RSQLOperators.defaultOperators()).parse(filter);

                JpaCriteriaQueryVisitor<E> visitor = new JpaCriteriaQueryVisitor<E>(clazz.newInstance());

                query = rootNode.accept(visitor, getEntityManager());
                root = (Root<E>) query.getRoots().stream().findFirst().get();
            } else {
                query = cb.createQuery(clazz);
                root = query.from(clazz);
                query.select(root);
            }
            return Pair.of(root, query);
        } catch (RuntimeException e) {
//            throw new GeneralExceptionMapper(e);
            return null;
        } catch (Exception e) {
//            throw new GeneralExceptionMapper(e);
            return null;
        }
    }

    private Set<E> removeDuplicateRows(List<E> entities) {
        Set<E> result = new LinkedHashSet<>();
        for (E t : entities) {
            result.add(t);
        }
        return result;
    }

    private Path<Object> getPathObjectRecursively(Root<E> root, String fieldName) {

        List<String> multiLevelPath = Arrays.asList(fieldName.split("\\."));
        Path<Object> objectPath = null;
        for (String actualProperty : multiLevelPath) {
            if (objectPath == null) {
                objectPath = root.get(actualProperty);
            } else {
                objectPath = objectPath.get(actualProperty);
            }
        }
        return objectPath;
    }

}

