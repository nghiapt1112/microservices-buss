package com.nghia.file.test.user;

import com.nghia.base.config.BaseServiceTest;
import com.nghia.tut.domain.user.User;
import com.nghia.tut.domain.user.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testTest() {
        System.out.println("Test da run");
    }

    @Test
    public void findOne() {
        User foundUser = userService.findByCode("userCode12");
        System.out.println(foundUser);
    }


    @Test
    public void createOne() {
        User user = new User("userName12", "userCode12");
        userService.createOne(user);
    }

    @Test
    public void createBatch() {
        List<User> users = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .map(el -> new User("name" + el, "code" + el, "same_org_code"))
                .collect(Collectors.toList());
        userService.createBatch(users);
    }

    @Test
    public void find() {
        this.createBatch();
        List<User> users = userService.find("same_org_code");
        users.forEach(System.out::println);
    }

    @Test
    public void updateMulti(){
        this.createBatch();
        userService.updateMulti("same_org_code");
    }

    @Test
    public void remove() {
//        this.createBatch();
        User user = userService.findByCode("code1");
        userService.remove(user);
    }

    @Test
    public void softRemove() {
//        this.createBatch();
        User user = userService.findByCode("code1");
        userService.softRemove(user);
    }



}
