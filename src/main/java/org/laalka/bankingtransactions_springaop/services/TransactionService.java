package org.laalka.bankingtransactions_springaop.services;

import lombok.AllArgsConstructor;
import org.laalka.bankingtransactions_springaop.aspects.TrackTime;
import org.laalka.bankingtransactions_springaop.models.Transaction;
import org.laalka.bankingtransactions_springaop.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;

    public void createTransaction(Long idSender,
                                  Long idReceiver,
                                  BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setIdSender(idSender);
        transaction.setIdReceiver(idReceiver);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @TrackTime
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
