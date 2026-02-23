package com.ex.fm.transaction.service;

import com.ex.fm.transaction.dto.TransactionDTO;
import com.ex.fm.transaction.dtotransformer.DtoTransformer;
import com.ex.fm.transaction.entity.Transaction;
import com.ex.fm.transaction.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionService {

    public static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private  DtoTransformer dtoTransformer;

    public Transaction createTransaction(TransactionDTO request){

        Transaction transaction = new Transaction();

        if(request != null){
            LOG.info("Transaction proceed to save.");
            transaction = dtoTransformer.dtoToTransaction(request);
            transaction.setCreatedAt(Instant.now());
            transactionRepository.save(transaction);
        }

        return transaction;
    }

}
