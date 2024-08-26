package com.transactions.initiate.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransactionDatabase {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "hr";
    private static final String PASS = "password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement()) {

            String createTableSQL = "CREATE TABLE Transactions (" +
                    "transactionId VARCHAR2(255) NOT NULL PRIMARY KEY, " +
                    "senderAccountId VARCHAR2(255) NOT NULL, " +
                    "receiverAccountId VARCHAR2(255) NOT NULL, " +
                    "transactionType VARCHAR2(50) NOT NULL, " +
                    "amount NUMBER(15, 2) NOT NULL CHECK (amount > 0), " +
                    "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                    "transactionStatus VARCHAR2(50) NOT NULL, " +
                    "description VARCHAR2(255), " +
                    "transactionMode VARCHAR2(50) NOT NULL, " +
                    "currency VARCHAR2(10) NOT NULL" +
                    ")";
            stmt.execute(createTableSQL);
            System.out.println("Table created successfully!");

            String insertSQL = "INSERT INTO Transactions (transactionId, senderAccountId, receiverAccountId, transactionType, amount, timestamp, transactionStatus, description, transactionMode, currency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                String[][] entries = {
                        { "TXN001", "ACC001", "ACC002", "CREDIT", "500.00", "2024-08-01 10:00:00", "SUCCESS",
                                "Payment for services", "CREDIT_CARD", "USD" },
                        { "TXN002", "ACC003", "ACC004", "DEBIT", "200.75", "2024-08-02 11:30:00", "PENDING",
                                "Purchase at store", "DEBIT_CARD", "USD" },
                        { "TXN003", "ACC005", "ACC006", "CREDIT", "1500.00", "2024-08-03 09:45:00", "FAILED",
                                "Online purchase", "UPI", "EUR" },
                        { "TXN004", "ACC007", "ACC008", "DEBIT", "320.00", "2024-08-04 12:00:00", "CANCELLED",
                                "Groceries", "CASH", "INR" },
                        { "TXN005", "ACC009", "ACC010", "CREDIT", "750.00", "2024-08-05 13:15:00", "SUCCESS", "Gift",
                                "CHEQUE", "GBP" },
                        { "TXN006", "ACC011", "ACC012", "DEBIT", "600.50", "2024-08-06 14:20:00", "PENDING",
                                "Bill payment", "NEFT", "USD" },
                        { "TXN007", "ACC013", "ACC014", "CREDIT", "1200.25", "2024-08-07 15:35:00", "SUCCESS", "Salary",
                                "UPI", "INR" },
                        { "TXN008", "ACC015", "ACC016", "DEBIT", "400.00", "2024-08-08 16:45:00", "FAILED", "Fuel",
                                "CREDIT_CARD", "EUR" },
                        { "TXN009", "ACC017", "ACC018", "CREDIT", "900.00", "2024-08-09 17:50:00", "SUCCESS",
                                "Freelance payment", "DEBIT_CARD", "USD" },
                        { "TXN010", "ACC019", "ACC020", "DEBIT", "250.00", "2024-08-10 18:00:00", "CANCELLED", "Dining",
                                "CASH", "GBP" },
                        { "TXN011", "ACC021", "ACC022", "CREDIT", "1400.75", "2024-08-11 19:15:00", "SUCCESS", "Bonus",
                                "NEFT", "JPY" },
                        { "TXN012", "ACC023", "ACC024", "DEBIT", "350.00", "2024-08-12 20:30:00", "PENDING", "Shopping",
                                "CHEQUE", "INR" },
                        { "TXN013", "ACC025", "ACC026", "CREDIT", "800.00", "2024-08-13 21:45:00", "SUCCESS",
                                "Loan repayment", "UPI", "USD" },
                        { "TXN014", "ACC027", "ACC028", "DEBIT", "450.50", "2024-08-14 22:00:00", "FAILED",
                                "Travel booking", "CREDIT_CARD", "EUR" },
                        { "TXN015", "ACC029", "ACC030", "CREDIT", "1250.00", "2024-08-15 23:15:00", "SUCCESS",
                                "Consulting fees", "DEBIT_CARD", "INR" },
                        { "TXN016", "ACC031", "ACC032", "DEBIT", "700.25", "2024-08-16 09:00:00", "CANCELLED",
                                "Utility payment", "CASH", "GBP" },
                        { "TXN017", "ACC033", "ACC034", "CREDIT", "1550.00", "2024-08-17 10:30:00", "SUCCESS",
                                "Commission", "NEFT", "JPY" },
                        { "TXN018", "ACC035", "ACC036", "DEBIT", "500.00", "2024-08-18 11:45:00", "PENDING",
                                "Subscription", "CHEQUE", "USD" },
                        { "TXN019", "ACC037", "ACC038", "CREDIT", "650.75", "2024-08-19 12:00:00", "SUCCESS",
                                "Dividend", "UPI", "EUR" },
                        { "TXN020", "ACC039", "ACC040", "DEBIT", "550.00", "2024-08-20 13:15:00", "FAILED",
                                "Rent payment", "CREDIT_CARD", "INR" },
                        { "TXN021", "ACC041", "ACC042", "CREDIT", "850.00", "2024-08-21 14:30:00", "SUCCESS", "Refund",
                                "DEBIT_CARD", "GBP" },
                        { "TXN022", "ACC043", "ACC044", "DEBIT", "300.00", "2024-08-22 15:45:00", "CANCELLED",
                                "Insurance premium", "CASH", "USD" },
                        { "TXN023", "ACC045", "ACC046", "CREDIT", "1750.50", "2024-08-23 16:00:00", "SUCCESS",
                                "Investment", "NEFT", "EUR" },
                        { "TXN024", "ACC047", "ACC048", "DEBIT", "450.00", "2024-08-24 17:15:00", "PENDING",
                                "Medical expenses", "CHEQUE", "INR" },
                        { "TXN025", "ACC049", "ACC050", "CREDIT", "950.75", "2024-08-25 18:30:00", "SUCCESS",
                                "Contract payment", "UPI", "GBP" },
                        { "TXN026", "ACC051", "ACC052", "DEBIT", "400.00", "2024-08-26 19:45:00", "FAILED",
                                "Service charges", "CREDIT_CARD", "USD" },
                        { "TXN027", "ACC053", "ACC054", "CREDIT", "1050.00", "2024-08-27 20:00:00", "SUCCESS",
                                "Payment to vendor", "DEBIT_CARD", "INR" },
                        { "TXN028", "ACC055", "ACC056", "DEBIT", "275.00", "2024-08-28 21:15:00", "CANCELLED",
                                "Charity donation", "CASH", "EUR" },
                        { "TXN029", "ACC057", "ACC058", "CREDIT", "1450.75", "2024-08-29 22:30:00", "SUCCESS",
                                "Bonus payout", "NEFT", "USD" },
                        { "TXN030", "ACC059", "ACC060", "DEBIT", "550.00", "2024-08-30 23:45:00", "PENDING",
                                "Grocery shopping", "CHEQUE", "GBP" },
                        { "TXN031", "ACC061", "ACC062", "CREDIT", "1650.00", "2024-08-31 09:00:00", "SUCCESS",
                                "Sale proceeds", "UPI", "INR" },
                        { "TXN032", "ACC063", "ACC064", "DEBIT", "325.25", "2024-09-01 10:15:00", "FAILED",
                                "Dining expenses", "CREDIT_CARD", "JPY" },
                        { "TXN033", "ACC065", "ACC066", "CREDIT", "1250.50", "2024-09-02 11:30:00", "SUCCESS",
                                "Loan payment", "DEBIT_CARD", "USD" },
                        { "TXN034", "ACC067", "ACC068", "DEBIT", "600.00", "2024-09-03 12:45:00", "CANCELLED",
                                "Car repair", "CASH", "EUR" },
                        { "TXN035", "ACC069", "ACC070", "CREDIT", "750.75", "2024-09-04 13:00:00", "SUCCESS",
                                "Service payment", "NEFT", "INR" },
                        { "TXN036", "ACC071", "ACC072", "DEBIT", "250.00", "2024-09-05 14:15:00", "PENDING",
                                "Utilities", "CHEQUE", "GBP" },
                        { "TXN037", "ACC073", "ACC074", "CREDIT", "1050.00", "2024-09-06 15:30:00", "SUCCESS",
                                "Project payment", "UPI", "USD" },
                        { "TXN038", "ACC075", "ACC076", "DEBIT", "300.50", "2024-09-07 16:45:00", "FAILED",
                                "Mobile recharge", "CREDIT_CARD", "EUR" },
                        { "TXN039", "ACC077", "ACC078", "CREDIT", "950.00", "2024-09-08 17:00:00", "SUCCESS",
                                "Software purchase", "DEBIT_CARD", "INR" },
                        { "TXN040", "ACC079", "ACC080", "DEBIT", "700.25", "2024-09-09 18:15:00", "CANCELLED",
                                "Subscription renewal", "CASH", "GBP" },
                        { "TXN041", "ACC081", "ACC082", "CREDIT", "1350.75", "2024-09-10 19:30:00", "SUCCESS",
                                "Freelance payment", "NEFT", "USD" },
                        { "TXN042", "ACC083", "ACC084", "DEBIT", "450.00", "2024-09-11 20:45:00", "PENDING",
                                "Insurance payment", "CHEQUE", "EUR" },
                        { "TXN043", "ACC085", "ACC086", "CREDIT", "850.50", "2024-09-12 21:00:00", "SUCCESS",
                                "Consulting fees", "UPI", "INR" },
                        { "TXN044", "ACC087", "ACC088", "DEBIT", "500.00", "2024-09-13 22:15:00", "FAILED",
                                "Utility payment", "CREDIT_CARD", "GBP" },
                        { "TXN045", "ACC089", "ACC090", "CREDIT", "1200.25", "2024-09-14 23:30:00", "SUCCESS",
                                "Payment for services", "DEBIT_CARD", "USD" },
                        { "TXN046", "ACC091", "ACC092", "DEBIT", "400.00", "2024-09-15 09:00:00", "CANCELLED",
                                "Shopping", "CASH", "EUR" },
                        { "TXN047", "ACC093", "ACC094", "CREDIT", "950.00", "2024-09-16 10:15:00", "SUCCESS",
                                "Loan repayment", "NEFT", "INR" },
                        { "TXN048", "ACC095", "ACC096", "DEBIT", "550.75", "2024-09-17 11:30:00", "PENDING",
                                "Dining expenses", "CHEQUE", "GBP" },
                        { "TXN049", "ACC097", "ACC098", "CREDIT", "1450.00", "2024-09-18 12:45:00", "SUCCESS",
                                "Rent payment", "UPI", "USD" },
                        { "TXN050", "ACC099", "ACC100", "DEBIT", "650.25", "2024-09-19 13:00:00", "FAILED",
                                "Fuel expenses", "CREDIT_CARD", "EUR" }
                };

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (String[] entry : entries) {
                    pstmt.setString(1, entry[0]);
                    pstmt.setString(2, entry[1]);
                    pstmt.setString(3, entry[2]);
                    pstmt.setString(4, entry[3]);
                    pstmt.setDouble(5, Double.parseDouble(entry[4]));

                    // Correctly parse the timestamp
                    try {
                        java.util.Date parsedDate = dateFormat.parse(entry[5]);
                        pstmt.setTimestamp(6, new Timestamp(parsedDate.getTime()));
                    } catch (ParseException e) {
                        System.err.println("ParseException: " + e.getMessage());
                        continue; // Skip this entry if parsing fails
                    }

                    pstmt.setString(7, entry[6]);
                    pstmt.setString(8, entry[7]);
                    pstmt.setString(9, entry[8]);
                    pstmt.setString(10, entry[9]);
                    pstmt.executeUpdate();
                }

                System.out.println("50 rows inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
