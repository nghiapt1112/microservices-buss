package com.nghia.tut.mss.service;

import com.nghia.tut.mss.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User findByCode(String authKey, String uCode);
    User defaultUser(String authKey, String uCode);
}
