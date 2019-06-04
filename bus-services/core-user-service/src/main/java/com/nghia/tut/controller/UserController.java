package com.nghia.tut.controller;

import com.nghia.tut.domain.user.User;
import com.nghia.tut.domain.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {
    protected static final Logger CONTROLLER_LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private Environment env;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @GetMapping("/")
    public User getUserByCode(@RequestParam String userCode) {
        return userService.findByCode(userCode);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        CONTROLLER_LOGGER.info("info Finding user with id: {}", id);
        CONTROLLER_LOGGER.info("\t\t\tdb name {}", dbName);

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


    @GetMapping("/env")
    public String getEnvVar(@PathVariable String envVar) {
        CONTROLLER_LOGGER.info("Get info of variable configuration: {}, \t value: {}", envVar, env.getProperty(envVar));

        return env.getProperty(envVar);
    }
}
