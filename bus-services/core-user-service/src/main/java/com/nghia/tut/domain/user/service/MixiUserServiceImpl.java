package com.nghia.tut.domain.user.service;

import com.nghia.tut.domain.user.User;
import com.nghia.tut.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MixiUserServiceImpl implements MixiUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String userId) {

        // TODO: nghiep vu
        // TODO: nghiep vu
        // TODO: nghiep vu


//        TODO: call DAO

        return this.userRepository.findbyId(userId);
    }
}
