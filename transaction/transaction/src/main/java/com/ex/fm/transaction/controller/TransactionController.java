package com.ex.fm.transaction.controller;

import com.ex.fm.transaction.dto.TransactionDTO;
import com.ex.fm.transaction.entity.Transaction;
import com.ex.fm.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<List<Transaction>> createTransaction(@RequestBody List<TransactionDTO> request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(request));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getListTransactionData(){
       return ResponseEntity.ok(transactionService.getListTransactionData());
    }
}
