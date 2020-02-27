package org.apache.camel.example.service;

import org.apache.camel.example.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("transactionService")
public class TransactionService {

    public List<Transaction> getTransactions(String accountId) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(0));
        transactions.add(new Transaction(1));
        transactions.add(new Transaction(10000));
        return transactions;

    }
}
