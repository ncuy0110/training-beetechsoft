package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
