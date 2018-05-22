package com.nghia.libraries.commons.mss.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

import java.util.Collection;

public class CustomJson2MessageConverter extends AbstractMessageConverter{

    private ObjectMapper objectMapper;

    {
        this.objectMapper = JsonUtils.OBJECT_MAPPER;
    }

    public CustomJson2MessageConverter(Collection<MimeType> supportedMimeTypes) {
        super(supportedMimeTypes);
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // should not be called, since we override canConvertFrom/canConvertTo instead
//        this.objectMapper.getFactory()
        throw new UnsupportedOperationException();
    }

}
