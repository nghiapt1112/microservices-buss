package com.nghia.tut.mss.domain;

public class User extends BaseDomain {
    private String id;
    private String name;
    private String userCode;
    private String organizationCode; // multiple tenant.

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
