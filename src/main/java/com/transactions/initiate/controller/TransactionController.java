package com.transactions.initiate.controller; 
import com.transactions.initiate.service.*; 

public class TransactionController { 
    
    private TransactionService transactionService; 
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    } 
}
