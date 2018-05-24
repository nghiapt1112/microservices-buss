package com.nghia.tut.domain.user.repository.impl;

import com.nghia.libraries.commons.mss.infrustructure.repository.impl.AbstractCustomRepository;
import com.nghia.tut.domain.user.User;
import com.nghia.tut.domain.user.repository.UserRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractCustomRepository<User> implements UserRepository {

    public User findOneUser(String userCode) {
        Query searchUserQuery = new Query(Criteria.where("userCode").is(userCode));
        return super.findOne(searchUserQuery, User.class);
    }

    @Override
    public User findbyId(String id) {
        Query searchUserQuery = new Query(Criteria.where("_id").is(id));
        return super.findOne(searchUserQuery, User.class);
    }

    public List<User> find(String org) {
        Query searchQuery = new Query();
        Criteria criteria = Criteria.where("organizationCode").is(org);

        searchQuery.addCriteria(criteria);
        return super.find(searchQuery, User.class);
    }

    public boolean updateMulti(String org) {
        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("organizationCode").is(org));

        Update update = new Update();
        update.set("userName", "Updated Success");
        update.set("modifiedAt", new Date());
        return super.updateMulti(searchQuery, update, User.class);
    }


}
