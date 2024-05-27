package com.br.api.casebank.service.impl;

import com.br.api.casebank.dto.AccountPostRequest;
import com.br.api.casebank.dto.AccountUpdateRequest;
import com.br.api.casebank.exception.DataHandlerException;
import com.br.api.casebank.mapper.AccountPostMapper;
import com.br.api.casebank.model.Account;
import com.br.api.casebank.repository.AccountRepository;
import com.br.api.casebank.repository.PersonRepository;
import com.br.api.casebank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountPostMapper accountMapper;

    @Transactional
    public Account createAccount(AccountPostRequest accountRequest){

        Account account;

        log.info("Creating account.");
        if (personRepository.existsById(accountRequest.getIdPerson())){
            account = accountMapper.convertToPerson(accountRequest);
            accountRepository.save(account);
            log.info("Account created.");
        } else {
            log.info("Id was not found.");
            throw new NoSuchElementException("Id not found.");
        }
        log.info("Account was created.");
        return account;

    }


    public Void deleteAccount(Long id){

        log.info("Attempt to delete account with Account Number: {}", id);

        if (!accountRepository.existsByAccountNumber(id)) {
            log.info("Id {} was not found.", id);
            throw new NoSuchElementException("Account Number not found.");
        }

        if (accountRepository.findBalanceByAccountNumber(id) == 0) {
            accountRepository.deleteById(id);
            log.info("Deleting account with id: {}", id);
        } else {
            throw new DataHandlerException("Balance must be zero for deletion.");
        }
        return null;
    }

    @Transactional
    public Account updateBalanceAccount(Long accountNumber, AccountUpdateRequest newBalance){

        return accountRepository.findById(accountNumber).map(account -> {
            account.setBalance(account.getBalance() + newBalance.getBalance());
            return accountRepository.save(account);
        }).orElseThrow(() -> new NoSuchElementException("Account with id " + accountNumber + " not found"));

    }

}
