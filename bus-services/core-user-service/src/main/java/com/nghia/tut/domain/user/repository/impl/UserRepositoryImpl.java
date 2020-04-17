package com.nghia.tut.domain.user.repository.impl;

import com.nghia.tut.domain.user.User;
import com.nghia.tut.domain.user.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User findOneUser(String userCode) {
        Query searchUserQuery = new Query(Criteria.where("userCode").is(userCode));
        return mongoTemplate.findOne(searchUserQuery, User.class);
    }

    @Override
    public User findbyId(String id) {
        Query searchUserQuery = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(searchUserQuery, User.class);
    }

    public List<User> find(String org) {
        Query searchQuery = new Query();
        Criteria criteria = Criteria.where("organizationCode").is(org);

        searchQuery.addCriteria(criteria);
        return mongoTemplate.find(searchQuery, User.class);
    }

    public boolean updateMulti(String org) {
        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("organizationCode").is(org));

        Update update = new Update();
        update.set("userName", "Updated Success");
        update.set("modifiedAt", new Date());
        mongoTemplate.updateMulti(searchQuery, update, User.class);
        return true;
    }


}
