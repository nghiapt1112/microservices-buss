package com.nghia.tut.mss.infrastructure.repository;

import com.nghia.libraries.mysql.infrustructure.domain.AbstractEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public abstract class AbstractRepository<E extends AbstractEntity, ID> extends SimpleJpaRepository<E, ID> {
    protected EntityManager entityManager;

    public AbstractRepository(Class<E> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    public <T> T getSingleResultOrNull(Query query) {
        query.setMaxResults(1);
        List<T> results = query.getResultList();
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        return results.get(0);
    }

//    public E getEntityInstance() {
//        Class<E> type = this.getEntityClass();
//        E inst = null;
//        try {
//            inst = type.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace(); // TODO hande e
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();// TODO hande e
//        }
//        return inst;
//    }
//
//    public Class<E> getEntityClass() {
//        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
//        Class<E> type = (Class<E>) superClass.getActualTypeArguments()[0];
//        return type;
//    }
}
