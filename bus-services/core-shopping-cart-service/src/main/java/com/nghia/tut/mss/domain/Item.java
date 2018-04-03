package com.nghia.tut.mss.domain;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

public class Item {
    private String id;
    private String itemName;
    private String description;

    public Item() {
    }

    public Item(String id, int no) {
        if (StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
        this.itemName = "item number: ".concat(String.valueOf(no));
        this.description = "description";
    }

    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }
}
