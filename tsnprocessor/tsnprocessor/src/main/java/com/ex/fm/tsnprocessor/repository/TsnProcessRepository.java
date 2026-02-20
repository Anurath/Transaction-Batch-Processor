package com.ex.fm.tsnprocessor.repository;

import com.ex.fm.tsnprocessor.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TsnProcessRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE t.processStatus = 'N' ORDER BY t.createdAt ASC")
    List<Transaction> fetchNextBatch(Pageable pageable);

}
