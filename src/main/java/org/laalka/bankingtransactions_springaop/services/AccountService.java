package org.laalka.bankingtransactions_springaop.services;

import lombok.AllArgsConstructor;
import org.laalka.bankingtransactions_springaop.aspects.TrackTime;
import org.laalka.bankingtransactions_springaop.models.Account;
import org.laalka.bankingtransactions_springaop.models.Transaction;
import org.laalka.bankingtransactions_springaop.repositories.AccountRepository;
import org.laalka.bankingtransactions_springaop.repositories.TransactionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    @TrackTime
    public void transferMoney(Long idSender,
                              Long idReceiver,
                              BigDecimal amount) {

        Account sender = accountRepository.findById(idSender)
                .orElseThrow(() -> new RuntimeException("Account sender not found"));
        Account receiver = accountRepository.findById(idReceiver)
                .orElseThrow(() -> new RuntimeException("Account receiver not found"));

        if (!senderHasMoneyToTransfer(sender, amount))
            throw new RuntimeException("Not enough balance");
        else {
            BigDecimal newSenderBalance = sender.getBalance().subtract(amount);
            BigDecimal newReceiverBalance = receiver.getBalance().add(amount);

            sender.setBalance(newSenderBalance);
            receiver.setBalance(newReceiverBalance);
            accountRepository.save(sender);
            accountRepository.save(receiver);
//                accountRepository.updateBalance(newSenderBalance, idSender);
//                accountRepository.updateBalance(newReceiverBalance, idReceiver);

            transactionService.createTransaction(idSender, idReceiver, amount);
        }
    }

    @TrackTime
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public boolean senderHasMoneyToTransfer(Account sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;
    }
}
