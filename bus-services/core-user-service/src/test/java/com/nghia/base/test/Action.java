package com.nghia.base.test;

import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(Action.class);
    protected String api;
    protected Input input;
    protected Output output;

    protected WebTestClient webTestClient;
    protected Object req = null;
    Variable storedVars;

    public static void main(String[] args) {
        Map<String, Object> storedVars = new HashMap<>();
        storedVars.put("userId", "5a5c31f7675a080001e13e28");
        storedVars.put("userName", "pham tuan nghia");
        storedVars.put("userEmail", "nghia.pham@testetstest");

        String reqApi = "/user/#userId";
        String reqApi2 = "/user?id=userId&name=#userName&email=#userEmail";


        String nReqApi = "";
        String nReqApi2 = new String(reqApi2);
        for (Map.Entry<String, Object> entry : storedVars.entrySet()) {
            System.out.println("key:" + entry.getKey());
            nReqApi = reqApi.replace("#" + entry.getKey(), entry.getValue().toString());
            nReqApi2 = nReqApi2.replace("#" + entry.getKey(), entry.getValue().toString());
//            nReqApi2 = StringUtils.replace(nReqApi2, "#" + entry.getKey(), entry.getValue().toString());
        }

        System.out.println("\nnReqApi=" + nReqApi);
        System.out.println("\nnReqApi2=" + nReqApi2);
    }

    protected Action injectDependencies(WebTestClient wtc, Variable storedValues) {
        this.webTestClient = wtc;
        this.storedVars = storedValues;
        return this;
    }

    abstract WebTestClient.ResponseSpec exec();

    void run() {
        WebTestClient.ResponseSpec response = this
                .parseInput()
                .exec();


        // expect STATUS_RESPONSE
        response
                .expectStatus()
                .isEqualTo(this.output.getStatus());

        if (this.output.getStatus() == 400) {
            return;
        }

        // expect BODY_RESPONSE
        response
                .expectBody()
                .consumeWith(consumer -> this.assertBody(consumer.getResponseBody()));

    }

    private void assertBody(byte[] responseBody) {
        if (Objects.isNull(responseBody)) {
            return;
        }
        Object real = JsonUtils.fromJson(new String(responseBody), Object.class);
        if (real instanceof Map == false) {
            LOGGER.info("realResponse chi nhan gia tri la Map, chua implement cho type khac");
        }
        Map<String, Object> realResponse = (Map<String, Object>) real;
        // REAL RESPONSE JSON only
        for (Map.Entry entry : ((Map<String, Object>) output.getFirstBodyElement()).entrySet()) {
            String k = entry.getKey().toString();
            if (entry.getValue() instanceof String) {
                String v = entry.getValue().toString();
                if (v.startsWith("$") && realResponse.containsKey(k)) {
                    this.storedVars.addVariable(v, realResponse.get(k));
                    continue;
                }
                if (v.startsWith("#") && realResponse.containsKey(k)) {
                    Assert.assertEquals(this.storedVars.retrieve(v), realResponse.get(entry.getKey()));
                    continue;
                }
            }
            Assert.assertEquals("Expect exist key :" + k + " but not exist.", true, realResponse.containsKey(k));
            Assert.assertEquals(entry.getValue(), realResponse.get(entry.getKey()));
        }


    }

    public Action parseInput() {
        this.api = this.storedVars.parseReqFromVariables(this.api);
        this.storedVars.refresh(this.input);
        this.storedVars.refresh(this.output);

        // decode api_request
        // VD: /user/#userId thi phai decode dc #userId la bao nhieu.

        return this;
    }

    public void prepareData(String api, Input input, Output output) {
        this.api = api;
        this.input = input;
        this.output = output;
    }

}

class PostAction extends Action {

    @Override
    protected WebTestClient.ResponseSpec exec() {
        return webTestClient
                .post()
                .uri(this.api)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(this.input.getFirstBodyElement()))
                .exchange()
                ;
    }
}

class PutAction extends PostAction {
}

class GetAction extends Action {
    @Override
    protected WebTestClient.ResponseSpec exec() {
        return webTestClient
                .get()
                .uri(this.api)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }
}


class DeleteAction extends Action {
    @Override
    protected WebTestClient.ResponseSpec exec() {
        return null;
    }
}
