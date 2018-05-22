package com.nghia.libraries.commons.mss.infrustructure.repository;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractEntity;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

public interface CustomRepository<E extends AbstractEntity> {

    E createOne(E entity);

    void createBatch(Collection<E> entities, Class<?> type);

    boolean updateMulti(Query query, Update update, Class<E> type);

    void update(E entity);

    boolean remove(E entity);

    void softRemove(E entity);

    List<E> findAll(Class<E> type);

    List<E> find(Query query, Class<E> type);

    E findOne(Query query, Class<E> type);
}
