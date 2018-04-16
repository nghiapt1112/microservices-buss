package com.nghia.tut.mss.controller;

import com.nghia.tut.mss.domain.User;
import com.nghia.tut.mss.domain.service.UserServiceImpl;
import com.nghia.tut.mss.infrustructure.controller.AbstractCustomController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    protected static final Logger CONTROLLER_LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @GetMapping("/")
    public User getUserByCode(@RequestParam String userCode) {
        return userService.findByCode(userCode);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        CONTROLLER_LOGGER.info("info Finding user with id: {}", id);
        return userService.findById(id);
    }

    @PostMapping(value = {"/", ""})
    public User createUser(@RequestBody User user) {
        CONTROLLER_LOGGER.info("Creating user with : {}", user);
        User result = userService.createOne(user);
        return result;
    }

    @PostMapping(value = {"/batch/", "/batch"})
    public void createBatch(@RequestBody List<User> users) {
        userService.createBatch(users);
    }

}
