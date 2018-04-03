package com.nghia.tut.mss.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public abstract class BaseDomain implements Serializable {

    @Override
    public String toString() {
        return (new ReflectionToStringBuilder(this)).setExcludeFieldNames(new String[]{"password"}).toString();
    }
}
