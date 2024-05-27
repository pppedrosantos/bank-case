package com.br.api.casebank.controller;

import com.br.api.casebank.dto.AccountPostRequest;
import com.br.api.casebank.dto.AccountUpdateRequest;
import com.br.api.casebank.model.Account;
import com.br.api.casebank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/caseBank")
public class AccountController {

    @Autowired
    private AccountService service;

    //Cadastro de uma nova conta vinculado ao usuario
    @PostMapping(value = "/createAccount", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> createAccount(@RequestBody AccountPostRequest accountPostRequest) {
        return new ResponseEntity<>(service.createAccount(accountPostRequest), HttpStatus.CREATED);
    }

    //Depósito Bancario
    @PatchMapping(value = "/updateBalanceAccount/{accountNumber}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> updateBalanceAccount(@PathVariable Long accountNumber,
                                                       @RequestBody AccountUpdateRequest balance) {
        return new ResponseEntity<>(service.updateBalanceAccount(accountNumber, balance), HttpStatus.OK);
    }

    //HARD DELETE da conta pelo numero da conta
    @DeleteMapping(value = "/deleteAccount/{accountNumber}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountNumber) {
        return new ResponseEntity<>(service.deleteAccount(accountNumber), HttpStatus.NO_CONTENT);
    }

}
