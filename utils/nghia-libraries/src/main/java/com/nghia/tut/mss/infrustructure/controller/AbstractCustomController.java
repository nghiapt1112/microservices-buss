package com.nghia.tut.mss.infrustructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class AbstractCustomController {

    protected static final Logger CONTROLLER_LOGGER = LoggerFactory.getLogger(AbstractCustomController.class);

    @Autowired
    Environment env;
}
