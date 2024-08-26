package com.transactions.initiate.resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.transactions.initiate.model.*; 

public class TransactionRepository {

    public List<Transaction> transactions = new ArrayList<>(); 
    public TransactionRepository() {    
        // Fake transactions for testing
        Random random = new Random();  
        for (int i = 0; i < 20; i++) { 
            Transaction transaction = new Transaction(
                (long) (Math.random() * 1000000007),  
                getRandomTransactionModeString(random),  
                getRandomTransactionTypeString(random),  
                (Math.random() * 100000),  
                LocalDateTime.now().minusDays(random.nextInt(30)),  
                "Demo Transaction " + (i + 1),  
                getRandomTransactionStatusString(random),  
                "INR",
                "USER" + ((i + 3) % 5),
                "USER" + ((i + 1) % 5)
            );
            transactions.add(transaction); 
        } 
        transactions.add(new Transaction(44L, "NEFT", "CREDIT", 5000.00, LocalDateTime.now(), "Test Transaction", "SUCCESS", "INR", "USER3", "USER1"));
    }

    // Helper methods to generate random enum values as strings
    public void addTransaction(Transaction t){ 
        if(TransactionValidations.validateTransaction(t)) transactions.add(t);
    }

    private String getRandomTransactionModeString(Random random) {
        String[] modes = {"CREDIT_CARD", "DEBIT_CARD", "NEFT", "CASH", "UPI", "CHEQUE"};
        return modes[random.nextInt(modes.length)];
    }

    private String getRandomTransactionTypeString(Random random) {
        String[] types = {"CREDIT", "DEBIT"};
        return types[random.nextInt(types.length)];
    }

    private String getRandomTransactionStatusString(Random random) {
        String[] statuses = {"PENDING", "SUCCESS", "FAILED", "CANCELLED"};
        return statuses[random.nextInt(statuses.length)];
    }
}
