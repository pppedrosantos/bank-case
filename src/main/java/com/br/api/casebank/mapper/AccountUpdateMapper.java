package com.br.api.casebank.mapper;

import com.br.api.casebank.dto.AccountUpdateRequest;
import com.br.api.casebank.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountUpdateMapper {

    public Account convertToAccount(AccountUpdateRequest accountUpdateRequest, Account account){

        return Account.builder()
                .accountNumber(account.getAccountNumber())
                .cardNumber(account.getCardNumber())
                .accountType(account.getAccountType())
                .idPerson(account.getIdPerson())
                .balance(accountUpdateRequest.getBalance() + account.getBalance())
                .build();

    }
}
