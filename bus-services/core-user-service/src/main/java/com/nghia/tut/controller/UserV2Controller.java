package com.nghia.tut.controller;

import com.nghia.tut.domain.user.User;
import com.nghia.tut.domain.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mixi/users2")
public class UserV2Controller {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user")
    public User findById(@RequestParam("id") String userId) {
        return null;
    }



}
