package com.nghia.libraries.commons.mss.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.nghia.libraries.commons.mss.infrustructure.base.JsonSerializationException;
import com.nghia.libraries.commons.mss.infrustructure.exception.DomainException;

import java.io.IOException;
import java.util.Collection;

public class JsonUtils {
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();

        OBJECT_MAPPER
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .getSerializerProvider()
                .setNullKeySerializer(new NullKeySerialize());

        OBJECT_MAPPER.setVisibility(OBJECT_MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY).withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE))
                .enable(SerializationFeature.INDENT_OUTPUT);

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