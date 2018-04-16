package com.nghia.tut.mss.domain;

import com.nghia.tut.mss.infrustructure.domain.AbstractEntity;
import com.nghia.tut.mss.infrustructure.domain.Child2LayerObject;
import com.nghia.tut.mss.infrustructure.domain.ChildObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "user")
public class User extends AbstractEntity {

    @Field("userName")
    private String name;
    private Date birth;
    private String userCode;
    private String organizationCode; // multiple tenant.
    private List<String> jobs;
    private ChildObject childObject1;
    private ChildObject childObject2;
    private Child2LayerObject child2LayerObject1;
    private Child2LayerObject child2LayerObject2;
    private Child2LayerObject child2LayerObject3;
    private Child2LayerObject child2LayerObject4;
    private Child2LayerObject child2LayerObject5;
    private Child2LayerObject child2LayerObject6;
    private Child2LayerObject child2LayerObject7;
    private Child2LayerObject child2LayerObject8;
    private Child2LayerObject child2LayerObject9;
    private Child2LayerObject child2LayerObject10;


    public User() {
    }

    public User(String name, String userCode) {
        this.name = name;
        this.userCode = userCode;
    }

    public User(String name, String userCode, String orgCode) {
        this.name = name;
        this.userCode = userCode;
        this.organizationCode = orgCode;
    }

    public User(String id, String name, String userCode, String orgCode) {
        if (StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
        this.name = name;
        this.userCode = userCode;
        this.organizationCode = orgCode;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public User initTestData(String userCode) {
        return new User(null, "default user".concat(userCode), userCode, "default orgCoce");
    }

    public Date getBirth() {
        return birth;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public ChildObject getChildObject1() {
        return childObject1;
    }

    public ChildObject getChildObject2() {
        return childObject2;
    }

    public Child2LayerObject getChild2LayerObject1() {
        return child2LayerObject1;
    }

    public Child2LayerObject getChild2LayerObject2() {
        return child2LayerObject2;
    }

    public Child2LayerObject getChild2LayerObject3() {
        return child2LayerObject3;
    }

    public Child2LayerObject getChild2LayerObject4() {
        return child2LayerObject4;
    }

    public Child2LayerObject getChild2LayerObject5() {
        return child2LayerObject5;
    }

    public Child2LayerObject getChild2LayerObject6() {
        return child2LayerObject6;
    }

    public Child2LayerObject getChild2LayerObject7() {
        return child2LayerObject7;
    }

    public Child2LayerObject getChild2LayerObject8() {
        return child2LayerObject8;
    }

    public Child2LayerObject getChild2LayerObject9() {
        return child2LayerObject9;
    }

    public Child2LayerObject getChild2LayerObject10() {
        return child2LayerObject10;
    }
}
