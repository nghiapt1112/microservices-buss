package com.nghia.tut.mss.domain;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractObject;

public class User extends AbstractObject {
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
