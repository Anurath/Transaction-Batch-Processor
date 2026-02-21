package com.ex.fm.tsnprocessor.service;

import com.ex.fm.tsnprocessor.entity.Transaction;
import com.ex.fm.tsnprocessor.repository.TsnProcessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
public class TsnProcessorService {

    public static final Logger LOG = LoggerFactory.getLogger(TsnProcessorService.class);

    @Autowired
    private TsnProcessRepository tsnProcessRepository;

    public void processTransactions(){

        while(true){

            Pageable limit = PageRequest.of(0,10);

            List<Transaction> bath = tsnProcessRepository.fetchNextBatch(limit);

            int resetCount = 0;
            while(resetCount<bath.size()){

                Transaction transaction = bath.get(resetCount);

                LOG.info("Transaction Accepted.");
                transaction.setProcessStatus("C");
                tsnProcessRepository.saveAllAndFlush(Collections.singletonList(transaction));

                resetCount++;
            }
            bath.clear();
        }
    }

    public boolean isInTransactionTime(Transaction transaction){

        if(Instant.now().toEpochMilli() - transaction.getCreatedAt().toEpochMilli() <= 5000 ){
            return true;
        }
        
        return false;
    }

}
