package io.paymentgateway.paymentmodule.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {
    @Bean
    public RestClient restClient() {
        RestTemplate oldRestTemplate = new RestTemplate();
        return  RestClient.create(oldRestTemplate);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("").build();
    }


}
