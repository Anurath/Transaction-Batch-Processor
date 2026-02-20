package com.ex.fm.transaction.controller;

import com.ex.fm.transaction.dto.TransactionTDO;
import com.ex.fm.transaction.entity.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/transaction")
public class TransactionController {

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionTDO request){
        return null;
    }
}
