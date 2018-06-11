package com.nghia.libraries.commons.mss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper OBJECT_MAPPER = JsonUtils.OBJECT_MAPPER;
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(ISO_8601));

        jsonConverter.setObjectMapper(OBJECT_MAPPER);
        return jsonConverter;
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());

        /**
         * Call to addDefaultHttpMessageConverters() is required
         * because the defaults are not applied when using custom converters.
         */
        super.addDefaultHttpMessageConverters(converters);
    }


}