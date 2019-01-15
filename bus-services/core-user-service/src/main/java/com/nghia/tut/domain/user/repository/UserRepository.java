package com.nghia.tut.domain.user.repository;

import com.nghia.libraries.mongo.infrustructure.repository.CustomRepository;
import com.nghia.tut.domain.user.User;

import java.util.List;

public interface UserRepository extends CustomRepository<User> {
    User findOneUser(String userCode);
    User findbyId(String id);

    List<User> find(String org);

    boolean updateMulti(String org);
}
