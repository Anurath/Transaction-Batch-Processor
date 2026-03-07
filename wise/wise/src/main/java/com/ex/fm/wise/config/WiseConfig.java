package com.ex.fm.wise.config;

import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WiseConfig {

    @Value("${google.genai.api-key}")
    private String apiKey;

    @Bean
    public Client getGenAiClient(){
        return Client.builder().apiKey(apiKey).build();
    }


}
