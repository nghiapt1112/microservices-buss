package com.nghia.libraries.commons.mss.infrustructure.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Objects;

public abstract class GenericAbstractService {
    @Autowired
    protected Environment env;


    public String toStr(String p) {
        String val = env.getProperty(p);
        if (StringUtils.isEmpty(val)) {
            return p;
        } else return val;
    }

    public Integer toInteger(String p) {
        Integer val = env.getProperty(p, Integer.class);
        if (Objects.isNull(val)) {
            return -1;
        } else return val;
    }
}
