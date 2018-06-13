package com.nghia.tut.mss.service;

import com.nghia.tut.mss.domain.User;

public interface UserService {
    User findUserByCode(String authKey, String uCode);
    User defaultUser(String authKey, String uCode);
}
