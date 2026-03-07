package com.ex.fm.wise.controller;

import com.ex.fm.wise.dto.PromptRequest;
import com.ex.fm.wise.dto.TextRequest;
import com.ex.fm.wise.service.WiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wise")
public class WiseController {

    @Autowired
    private WiseService wiseService;

    @Value("${nvidia.ai.api-key}")
    private String nvidiaApiKey;

    @Value("${nvidia.ai.url}")
    private String nvidiaUrl;

    @GetMapping
    public String generateResponse(){
        return wiseService.generateResponse();
    }

    @GetMapping("/nvidia")
    public String generateNvidiaResponse(){
        return wiseService.generateNvidiaResponse(nvidiaUrl,nvidiaApiKey);
    }

    @GetMapping("/sharvam")
    public byte[] convertTestToSpeach(@RequestBody TextRequest textRequest){
        return wiseService.convertTextToSpeech(textRequest.getText());
    }
}
