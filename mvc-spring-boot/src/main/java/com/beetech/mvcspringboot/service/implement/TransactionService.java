package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.Transaction;
import com.beetech.mvcspringboot.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Optional<Transaction> findOne(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void test1() {
        Optional<Transaction> transactionOptional = transactionRepository.findById(1L);
        if (transactionOptional.isEmpty()) {
            return;
        }
        var transaction = transactionOptional.get();
        System.out.println("Before deposit: " + transaction.getBalance());
        transaction.setBalance(transaction.getBalance() + 1000);
        System.out.println("uncommitted deposit: " + transaction.getBalance());
        try {
            test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void test2() {
        transactionRepository.save(null);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void test3() {
        var transactionOptional = transactionRepository.findById(1L);
        Transaction transaction;
        if (transactionOptional.isEmpty()) {
            return;
        }
        transaction = transactionOptional.get();
        System.out.println("Before deposit: " + transaction.getBalance());
        transaction.setBalance(transaction.getBalance() + 1000);
        System.out.println("uncommitted deposit: " + transaction.getBalance());
        try {
            test4();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test4() throws Exception {
        throw new Exception("test error");
    }
}
