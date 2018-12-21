package com.example.clientdemo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @SuppressWarnings("UnusedDeclaration")
    private static final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("Configuring jackson ObjectMapper");
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();

        //configure Joda serialization
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS, false);

        // Other options such as how to deal with nulls or identing...
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        converter.setObjectMapper(objectMapper);

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
    /*
    StringHttpMessageConverter must appear first in the list so that Spring has a chance to use
     it for Spring RestController methods that return simple String. Otherwise, it will use
      MappingJackson2HttpMessageConverter and clutter the response with escaped quotes and such
     */
        converters.add(stringHttpMessageConverter);
        converters.add(converter);
        super.configureMessageConverters(converters);
    }
}
