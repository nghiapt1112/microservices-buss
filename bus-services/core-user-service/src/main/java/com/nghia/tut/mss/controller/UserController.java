package com.nghia.tut.mss.controller;

import com.nghia.tut.mss.domain.User;
import com.nghia.tut.mss.domain.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public User getUserByCode(@RequestParam String userCode) {
        return userService.findByCode(userCode);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping(value = {"/", ""})
    public User createUser(@RequestBody User user) {
        User result = userService.createOne(user);
        return result;
    }

    @PostMapping(value = {"/batch/", "/batch"})
    public void createBatch(@RequestBody List<User> users) {
        userService.createBatch(users);
    }

}
