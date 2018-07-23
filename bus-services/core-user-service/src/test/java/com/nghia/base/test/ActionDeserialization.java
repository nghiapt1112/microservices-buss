package com.nghia.base.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ActionDeserialization extends JsonDeserializer<Action> {

    @Override
    public Action deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jp.getCodec();
        String action = objectCodec.readValue(jp, String.class);
        switch (action) {
            case "POST":
                return new PostAction();
            case "GET":
                return new GetAction();
            case "DELETE":
                return new DeleteAction();
            case "PUT":
                return new PutAction();

        }
        return null;
    }
}
