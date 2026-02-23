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
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    public static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private  DtoTransformer dtoTransformer;

    public List<Transaction> createTransaction(List<TransactionDTO> request){

        Transaction transaction = new Transaction();

        List<Transaction> response = new ArrayList<>();
        if(request != null){
            int idx = 0;
            while(idx<request.size()){
                LOG.info("Transaction proceed to save.");
                transaction = dtoTransformer.dtoToTransaction(request.get(idx));
                transaction.setCreatedAt(Instant.now());
                transactionRepository.save(transaction);
                response.add(transaction);
                idx++;
            }
        }

        return response;
    }

    public List<Transaction> getListTransactionData() {
        return transactionRepository.findAll();
    }
}
