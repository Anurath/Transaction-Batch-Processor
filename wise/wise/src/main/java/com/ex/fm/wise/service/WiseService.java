package com.ex.fm.wise.service;

import com.ex.fm.wise.dto.PromptRequest;
import com.ex.fm.wise.repositoty.WiseRepository;
import com.google.genai.Client;
import com.ex.fm.wise.entity.Transaction;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class WiseService {

    @Autowired
    private WiseRepository wiseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final Client client;

    public WiseService(Client client){
        this.client = client;
    }

    public String generateResponse() {

        List<Transaction> all = wiseRepository.findAll();

        String mainData = objectMapper.writeValueAsString(all);

        String prompt = "Analyze this transaction and provide proper insights about my data."+mainData;

        try{
            GenerateContentResponse generateContentResponse = client.models.generateContent(
                    "gemini-2.0-flash",
                    prompt,
                    null
            );
            return generateContentResponse.text();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;

    }
}
