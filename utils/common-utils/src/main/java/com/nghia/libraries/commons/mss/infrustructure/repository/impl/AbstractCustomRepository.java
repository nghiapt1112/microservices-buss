package com.nghia.libraries.commons.mss.infrustructure.repository.impl;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractEntity;
import com.nghia.libraries.commons.mss.infrustructure.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
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
    public void createBatch(Collection<E> entities) {
        entities.forEach(el -> el.initDefaultFieldsCreate());
        mongoTemplate.insert(entities, this.getEntityClass());
    }

    @Override
    public void update(E entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public boolean updateMulti(Query query, Update update) {
        return mongoTemplate.updateMulti(query, update, this.getEntityClass()).wasAcknowledged();
    }


    @Override
    public boolean remove(E entity) {
        return mongoTemplate.remove(entity).wasAcknowledged();
    }

    @Override
    public List<E> findAll() {
        return mongoTemplate.findAll(this.getEntityClass());
    }

    @Override
    public List<E> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    @Override
    public E findOne(Query query) {
        List<E> users = mongoTemplate.find(query, this.getEntityClass());
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

    public E getEntityInstance() {
        Class<E> type = this.getEntityClass();
        E inst = null;
        try {
            inst = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace(); // TODO hande e
        } catch (IllegalAccessException e) {
            e.printStackTrace();// TODO hande e
        }
        return inst;
    }

    public Class<E> getEntityClass() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<E> type = (Class<E>) superClass.getActualTypeArguments()[0];
        return type;
    }
}
