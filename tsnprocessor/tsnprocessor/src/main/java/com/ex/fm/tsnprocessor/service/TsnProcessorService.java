package com.ex.fm.tsnprocessor.service;

import com.ex.fm.tsnprocessor.entity.Transaction;
import com.ex.fm.tsnprocessor.repository.TsnProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsnProcessorService {

    @Autowired
    private TsnProcessRepository tsnProcessRepository;

    public void processTransactions(){
        while(true){

            Pageable limit = PageRequest.of(0,10);

            List<Transaction> bath = tsnProcessRepository.fetchNextBatch(limit);

            int resetCount = 0;
            while(resetCount<bath.size()){

                
                resetCount++;
            }
        }
    }

}
