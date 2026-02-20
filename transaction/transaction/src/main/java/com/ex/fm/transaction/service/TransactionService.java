package com.ex.fm.transaction.service;

import com.ex.fm.transaction.dto.TransactionTDO;
import com.ex.fm.transaction.entity.Transaction;
import com.ex.fm.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(TransactionTDO request){
        if(request != null){

            transactionRepository.save(null);
        }
        return null;
    }

}
