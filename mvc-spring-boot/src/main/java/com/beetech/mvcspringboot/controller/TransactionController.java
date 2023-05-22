package com.beetech.mvcspringboot.controller;

import com.beetech.mvcspringboot.service.implement.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/required")
    public ResponseEntity<String> testRequired() {
        transactionService.test1();
        System.out.println("After error: " + transactionService.findOne(1L).get().getBalance());
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/required-new")
    public ResponseEntity<String> testRequiredNew() {
        transactionService.test3();
        System.out.println("After error: " + transactionService.findOne(1L).get().getBalance());
        return ResponseEntity.ok("Hello");
    }
}
