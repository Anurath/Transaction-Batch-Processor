package com.ex.fm.wise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SharvamConfig {
    @Value("${sarvam.ai.api-key}")
    private String apiKey;

    @Value("${sarvam.ai.sharvamUrl}")
    private String sharvamUrl;

    @Bean
    public WebClient sarvamWebClient() {
        return WebClient.builder()
                .baseUrl(sharvamUrl)
                .defaultHeader("api-subscription-key", apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
