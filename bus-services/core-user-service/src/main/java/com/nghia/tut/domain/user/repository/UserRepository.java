package com.nghia.libraries.commons.mss.domain.user.repository;

import com.nghia.tut.mss.domain.User;
import com.nghia.tut.mss.infrustructure.repository.CustomRepository;

import java.util.List;

public interface UserRepository extends CustomRepository<User> {
    User findOneUser(String userCode);
    User findbyId(String id);

    List<User> find(String org);

    boolean updateMulti(String org);
}
