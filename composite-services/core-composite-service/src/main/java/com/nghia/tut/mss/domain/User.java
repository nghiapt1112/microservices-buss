package com.nghia.tut.mss.domain;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractObject;

import java.util.UUID;

public class User extends AbstractObject {
    private String id;
    private String name;
    private String userCode;
    private String organizationCode; // multiple tenant.

    public User testData() {
        this.name = "testName" + UUID.randomUUID().toString();
        this.userCode = UUID.randomUUID().toString();
        this.organizationCode = "test Organization";
        return this;
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
}
