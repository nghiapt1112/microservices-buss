package com.nghia.base.test;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.nghia.libraries.commons.mss.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class TestCaseDeserialization extends JsonDeserializer<TestCase> {
    @Override
    public TestCase deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = parser.getCodec();
        Map<String, Object> action = objectCodec.readValue(parser, Map.class);

        String reqMethod = String.valueOf(action.get("action"));

        Action reqAction = null;
        switch (reqMethod) {
            case "POST":
                reqAction = new PostAction();
                break;
            case "GET":
                reqAction = new GetAction();
                break;
            case "DELETE":
                reqAction = new DeleteAction();
                break;
            case "PUT":
                reqAction = new PutAction();
                break;
            default:
                System.out.println("Unsupport Action");
                break;
        }

        String desc = String.valueOf(action.get("desc"));
        String api = String.valueOf(action.get("api"));

        Input input = JsonUtils.fromJson(JsonUtils.toJson(action.get("input")), Input.class);
        Output output = JsonUtils.fromJson(JsonUtils.toJson(action.get("output")), Output.class);

        reqAction.prepareData(api, input, output);
        return new TestCase(desc, reqAction);
    }
}
