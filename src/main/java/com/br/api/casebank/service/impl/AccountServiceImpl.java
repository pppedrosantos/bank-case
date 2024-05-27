package com.br.api.casebank.service.impl;

import com.br.api.casebank.dto.AccountPostRequest;
import com.br.api.casebank.dto.AccountUpdateRequest;
import com.br.api.casebank.exception.DateHandlerException;
import com.br.api.casebank.mapper.AccountPostMapper;
import com.br.api.casebank.mapper.AccountUpdateMapper;
import com.br.api.casebank.model.Account;
import com.br.api.casebank.repository.AccountRepository;
import com.br.api.casebank.repository.PersonRespository;
import com.br.api.casebank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    PersonRespository personRespository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountPostMapper accountMapper;

    @Autowired
    AccountUpdateMapper accountUpdateMapper;

    public Account createAccount(AccountPostRequest accountRequest){

        Account account;

        log.info("Creating account.");
        if (personRespository.existsById(accountRequest.getIdPerson())){
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
            throw new DateHandlerException("Balance must be zero for deletion.");
        }
        return null;
    }

    public String updateBalanceAccount(Long accountNumber, AccountUpdateRequest accountUpdateRequest){
        log.info("Updating account balance.");
        Account account = accountRepository.findById(accountNumber).orElseThrow(
                () -> new NoSuchElementException("Id not found.")
        );
        account = accountUpdateMapper.convertToAccount(accountUpdateRequest, account);
        accountRepository.save(account);
        log.info("Updated balance.");

        return "Update Successfully";


    }

}
