package com.nghia.libraries.commons.mss.domain.repository;

import com.nghia.libraries.commons.mss.domain.User;
import com.nghia.libraries.commons.mss.infrustructure.repository.CustomRepository;

import java.util.List;

public interface UserRepository extends CustomRepository<User> {
    User findOneUser(String userCode);
    User findbyId(String id);

    List<User> find(String org);

    boolean updateMulti(String org);
}
