package com.transactions.initiate.test;
import org.junit.jupiter.api.BeforeEach; 
import com.transactions.initiate.service.TransactionService;

public class TransactionRepositoryTest {
    private TransactionService service;
    
    @BeforeEach
    public void setUp() {
        service = new TransactionService();
    } 
}
