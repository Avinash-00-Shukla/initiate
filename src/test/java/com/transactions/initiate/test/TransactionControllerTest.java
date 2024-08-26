package com.transactions.initiate.test;  
import org.junit.jupiter.api.BeforeEach;

import com.transactions.initiate.service.*; 

public class TransactionControllerTest { 
	    private TransactionService transactionService;   
        @BeforeEach
        public void setUp() {
            transactionService = new TransactionService();
        }
}
