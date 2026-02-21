package com.ex.fm.transaction.controller;

import com.ex.fm.transaction.dto.TransactionDTO;
import com.ex.fm.transaction.entity.Transaction;
import com.ex.fm.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(request));
    }
}
