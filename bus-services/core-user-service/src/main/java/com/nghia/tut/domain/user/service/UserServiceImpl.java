package com.nghia.tut.domain.user.service;

import com.nghia.tut.domain.user.User;
import com.nghia.tut.domain.user.repository.UserRepository;
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
        return userRepository.insert(user);
    }

    public void createBatch(Collection<User> users) {
        userRepository.insert(users);
    }

    public List<User> find(String orgName) {
        return userRepository.find(orgName);
    }

    public boolean updateMulti(String orgCode) {
        return userRepository.updateMulti(orgCode);
    }

    public boolean remove(User user) {
        userRepository.delete(user);
        return true;
    }

    public void softRemove(User user) {
        user.setDeleted(true);
        userRepository.save(user);
    }
}
