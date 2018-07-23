package com.nghia.base.test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.test.web.reactive.server.WebTestClient;

@JsonDeserialize(using = TestCaseDeserialization.class)
public class TestCase {
    private String desc;
    private Action action;
    private WebTestClient webTestClient;
    private Variable storedValues;

    public TestCase(String desc, Action action) {
        this.action = action;
        this.desc = desc;
    }

    public void execTest() {
        this.action
                .injectDependencies(webTestClient, storedValues)
                .run();

    }

    public TestCase injectDependencies(WebTestClient webTestClient, Variable storedValues) {
        this.webTestClient = webTestClient;
        this.storedValues = storedValues;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Action getAction() {
        return action;
    }

}




