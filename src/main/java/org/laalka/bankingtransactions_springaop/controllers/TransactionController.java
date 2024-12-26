package org.laalka.bankingtransactions_springaop.controllers;

import lombok.AllArgsConstructor;
import org.laalka.bankingtransactions_springaop.dto.CreateAccountRequest;
import org.laalka.bankingtransactions_springaop.dto.TransferRequest;
import org.laalka.bankingtransactions_springaop.models.Account;
import org.laalka.bankingtransactions_springaop.models.Transaction;
import org.laalka.bankingtransactions_springaop.services.AccountService;
import org.laalka.bankingtransactions_springaop.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;


    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest transferRequest) {
        accountService.transferMoney(transferRequest.getSenderId(),
                transferRequest.getReceiverId(),
                transferRequest.getAmount());
    }

    @PostMapping("/create-account")
    public void createAccount(
            @RequestBody CreateAccountRequest createAccountRequest
    ) {
        Account newAccount = new Account();
        newAccount.setUserName(createAccountRequest.getUserName());
        newAccount.setBalance(createAccountRequest.getBalance());
        accountService.createAccount(newAccount);
    }

}
