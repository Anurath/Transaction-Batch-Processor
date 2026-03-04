package com.ex.fm.wise.controller;

import com.ex.fm.wise.dto.PromptRequest;
import com.ex.fm.wise.service.WiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wise")
public class WiseController {

    @Autowired
    private WiseService wiseService;

    @GetMapping
    public String generateResponse(){
        return wiseService.generateResponse();
    }
}
