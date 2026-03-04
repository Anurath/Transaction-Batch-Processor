package com.ex.fm.wise.repositoty;


import com.ex.fm.wise.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WiseRepository extends JpaRepository<Transaction, Long> {
}
