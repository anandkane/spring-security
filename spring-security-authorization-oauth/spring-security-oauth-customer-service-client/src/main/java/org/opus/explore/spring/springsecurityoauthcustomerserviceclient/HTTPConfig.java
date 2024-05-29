package org.opus.explore.spring.springsecurityoauthcustomerserviceclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class HTTPConfig {
    public final ObjectMapper objectMapper;  // provided by spring

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplateBuilder()
                .messageConverters(new FormHttpMessageConverter(), new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
        return template;
    }
}