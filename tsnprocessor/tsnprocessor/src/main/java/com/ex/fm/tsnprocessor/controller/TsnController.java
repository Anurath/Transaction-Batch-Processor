package com.ex.fm.tsnprocessor.controller;

import com.ex.fm.tsnprocessor.service.TsnProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tsnprocessor")
public class TsnController {

    @Autowired
    private TsnProcessorService tsnProcessorService;

    @GetMapping
    public void processTransactions(){
        tsnProcessorService.processTransactions();
    }


}
