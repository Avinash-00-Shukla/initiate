package com.transactions.initiate.test;
import com.transactions.initiate.model.*;
import com.transactions.initiate.service.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;  

class TransactionServiceTest {

    private TransactionService service;

    @BeforeEach
     void setUp() {
        service = new TransactionService();
    }

    @Test
    void testAddTransaction() {                 
        boolean result = service.addTransaction(21L, "NEFT", "CREDIT", 5000.00, LocalDateTime.now(), "Test Transaction", "SUCCESS", "INR", "USER2", "USER1"); 
        assertTrue(result);  
        boolean result2 = service.addTransaction(21L, "UPI", "DEBIT", 1000.00, LocalDateTime.now(), "Test Transaction", "SUCCESS", "INR", "USER3", "USER1"); 
        assertTrue(result2);  
        boolean result3 = service.addTransaction(21L, "DUMMY", "CREDIT", 2000.00, LocalDateTime.now(), "Test Transaction", "SUCCESS", "INR", "USER3", "USER1"); 
        assertFalse(result3);   
    }

    @Test
    void testGetTransactionsByDateRange() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(10);
        LocalDateTime endDate = LocalDateTime.now();

        List<Transaction> transactions = service.getTransactionsByDateRange(startDate, endDate);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getDate().isAfter(startDate.minusSeconds(1)) && t.getDate().isBefore(endDate.plusSeconds(1)));
        }
    }

    @Test
    void testGetTransactionsByExactAmount() {
        double amount = 5000.00; 
        service.addTransaction(22L, "CASH", "DEBIT", amount, LocalDateTime.now(),
        "Test Transaction Exact Amount", "PENDING", "INR", "USER1", "USER2");

        List<Transaction> transactions = service.getTransactionsByExactAmount(amount);
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());

        for (Transaction t : transactions) {
            assertEquals(amount, t.getAmount(), 0.001);
        }
    }

    @Test
    void testGetTransactionsByMinAmount() {
        double minAmount = 1000.00;
        List<Transaction> transactions = service.getTransactionsByMinAmount(minAmount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getAmount() >= minAmount);
        }
    }

    @Test
    void testGetTransactionsByMaxAmount() {
        double maxAmount = 10000.00;
        List<Transaction> transactions = service.getTransactionsByMaxAmount(maxAmount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getAmount() <= maxAmount);
        }
    }

    @Test
    void testGetTransactionsByType() {
        TransactionType type = TransactionType.CREDIT; 
        service.addTransaction(23L, "UPI", "CREDIT", 7500.00, LocalDateTime.now(),
        "Test Transaction Type", "SUCCESS", "INR", "USER2", "USER3");

        List<Transaction> transactions = service.getTransactionsByType(type);
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());

        for (Transaction t : transactions) {
            assertEquals(type, t.getType());
        }
    }

    @Test
    void testGetTransactionsByStatus() {
        TransactionStatus status = TransactionStatus.SUCCESS;
        List<Transaction> transactions = service.getTransactionsByStatus(status);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(status, t.getStatus());
        }
    }

    @Test
    void testGetTransactionsByMode() {
        TransactionMode mode = TransactionMode.CASH; 
        service.addTransaction(24L, "CASH", "DEBIT", 3000.00, LocalDateTime.now(),
        "Test Transaction Mode", "FAILED", "INR", "USER3", "USER4");

        List<Transaction> transactions = service.getTransactionsByMode(mode);
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());

        for (Transaction t : transactions) {
            assertEquals(mode, t.getMode());
        }
    }

    @Test
     void testGetTransactionsByDescription() {
        String keyword = "Demo";
        List<Transaction> transactions = service.getTransactionsByDescription(keyword);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertTrue(t.getDescription().contains(keyword));
        }
    }

    @Test
     void testGetTransactionById() {
        Long transactionId = 44L;
        Transaction transaction = service.getTransactionById(transactionId);
        assertNotNull(transaction);
        assertEquals(transactionId, transaction.getId());
    }

    @Test
     void testGetTransactionsBySenderAccount() {
        String senderAccount = "USER1";
        List<Transaction> transactions = service.getTransactionsBySenderAccount(senderAccount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(senderAccount, t.getSenderAccount());
        }
    }

    @Test
     void testGetTransactionsByReceiverAccount() {
        String receiverAccount = "USER2";
        List<Transaction> transactions = service.getTransactionsByReceiverAccount(receiverAccount);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(receiverAccount, t.getRecieverAccount());
        }
    }

    @Test
     void testGetTransactionsByCurrency() {
        String currency = "INR";
        List<Transaction> transactions = service.getTransactionsByCurrency(currency);
        assertNotNull(transactions);

        for (Transaction t : transactions) {
            assertEquals(currency, t.getCurrency());
        }
    }
}
