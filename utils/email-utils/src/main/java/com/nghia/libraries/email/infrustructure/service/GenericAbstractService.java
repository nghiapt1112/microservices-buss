package com.nghia.libraries.email.infrustructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.Objects;

public abstract class GenericAbstractService {
    private final String DEFAULT_MSG = "Default Error Message";
    private final int DEFAULT_CODE = -1;
    @Autowired
    protected Environment env;

    public String toStr(String p) {
        if (StringUtils.isEmpty(p)) {
            return DEFAULT_MSG;
        }
        String val = env.getProperty(p);
        return StringUtils.isEmpty(val) ? p : val;
    }

    public Integer toInt(String p) {
        Integer val = env.getProperty(p, Integer.class);
        return Objects.isNull(val) ? DEFAULT_CODE : val;
    }
}
