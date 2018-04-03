package com.nghia.tut.mss.infrustructure.repository.impl;

import com.nghia.tut.mss.infrustructure.domain.AbstractEntity;
import com.nghia.tut.mss.infrustructure.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

public class AbstractCustomRepository<E extends AbstractEntity> implements CustomRepository<E> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public E createOne(E entity) {
        entity.initDefaultFieldsCreate();
        //    => slower than using template.insert(entity, "COLLECTION_NAME")
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public void createBatch(Collection<E> entities, Class<?> type) {
        entities.forEach(el -> el.initDefaultFieldsCreate());
        mongoTemplate.insert(entities, type);
    }

    @Override
    public void update(E entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public boolean updateMulti(Query query, Update update, Class<E> clazz) {
        return mongoTemplate.updateMulti(query, update, clazz).wasAcknowledged();
    }


    @Override
    public boolean remove(E entity) {
        return mongoTemplate.remove(entity).wasAcknowledged();
    }

    @Override
    public List<E> findAll(Class<E> type) {
        return mongoTemplate.findAll(type);
    }

    @Override
    public List<E> find(Query query, Class<E> type) {
        return mongoTemplate.find(query, type);
    }

    @Override
    public E findOne(Query query, Class<E> type) {
        List<E> users = mongoTemplate.find(query, type);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void softRemove(E entity) {
        entity.setDeleted(true);
        mongoTemplate.save(entity);
    }

}
