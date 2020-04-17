package com.nghia.tut.domain.user;

import com.nghia.tut.infrastructure.utils.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "user")
@Data
@NoArgsConstructor
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

    @Transient
    private String ignoreField;

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


    public User initTestData(String userCode) {
        return new User(null, "default user".concat(userCode), userCode, "default orgCoce");
    }


    public String getIgnoreField() {
        return ignoreField;
    }
}
