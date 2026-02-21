package com.ex.fm.transaction.dtotransformer;

import com.ex.fm.transaction.dto.TransactionDTO;
import com.ex.fm.transaction.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DtoTransformer {

    public static final Logger LOG = LoggerFactory.getLogger(DtoTransformer.class);

    public Transaction dtoToTransaction(TransactionDTO dto){
        Transaction transaction = new Transaction();

        transaction.setLocation(dto.getLocation());
        transaction.setStatus(dto.getStatus());
        transaction.setProcessStatus(dto.getProcessStatus());
        transaction.setBalance(dto.getBalance());
        transaction.setTransactionAmount(dto.getTransactionAmount());
        transaction.setTax(dto.getTax());
        transaction.setReceiverId(dto.getReceiverId());

        LOG.info("TransactionDTO transformed to Transaction.");
        return transaction;
    }
}
