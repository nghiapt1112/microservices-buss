package com.nghia.tut.mss.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.nghia.tut.mss.infrustructure.base.JsonSerializationException;
import com.nghia.tut.mss.infrustructure.exception.DomainException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class JsonUtils {
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        OBJECT_MAPPER = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        ;
        OBJECT_MAPPER.getSerializerProvider()
                .setNullKeySerializer(new NullKeySerialize());

        OBJECT_MAPPER.setVisibility(OBJECT_MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY).withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE))
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setDateFormat(new SimpleDateFormat(ISO_8601));
    }

    public static <T> String toJson(T object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new DomainException(DomainException.Domain.General.getDomainCode(), e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return OBJECT_MAPPER.<T>readValue(json, type);
        } catch (Exception e) {
            throw new DomainException(DomainException.Domain.General.getDomainCode(), e);
        }
    }

    public static <T> byte[] toBytes(T object) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new DomainException(DomainException.Domain.General.getDomainCode(), e);
        }
    }

    public static <T> T fromByte(byte[] json, Class<T> type) {
        try {
            return OBJECT_MAPPER.<T>readValue(json, type);
        } catch (Exception e) {
            throw new DomainException(DomainException.Domain.General.getDomainCode(), e);
        }
    }

    public static <E, T extends Collection<E>> T toCollection(String json, Class<T> collectionType, Class<E> elementType)
            throws JsonSerializationException {
        try {
            return OBJECT_MAPPER.readValue(json, constructCollectionType(collectionType, elementType));
        } catch (Exception e) {
            throw new JsonSerializationException("Cannot convert Json to Object type "
                    + constructCollectionType(collectionType, elementType).toString(), e);
        }
    }

    public static JavaType constructCollectionType(Class<? extends Collection<?>> collectionClass,
                                                   Class<?> elementType) {
        return OBJECT_MAPPER.getTypeFactory().constructCollectionType(collectionClass, elementType);
    }
}

class NullKeySerialize extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeFieldName("null");
    }
}