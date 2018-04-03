package com.nghia.tut.mss.domain.service;

import com.nghia.tut.mss.domain.User;
import com.nghia.tut.mss.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public User findByCode(String userCode) {
        return userRepository.findOneUser(userCode);
    }

    public User findById(String id) {
        return userRepository.findbyId(id);
    }

    public User createOne(User user) {
        return userRepository.createOne(user);
    }

    public void createBatch(Collection<User> users) {
        userRepository.createBatch(users, User.class);
    }

    public List<User> find(String orgName) {
        return userRepository.find(orgName);
    }

    public boolean updateMulti(String orgCode) {
        return userRepository.updateMulti(orgCode);
    }

    public boolean remove(User user) {
        return userRepository.remove(user);
    }

    public void softRemove(User user) {
        userRepository.softRemove(user);
    }
}
