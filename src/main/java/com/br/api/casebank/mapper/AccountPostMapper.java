package com.br.api.casebank.mapper;

import com.br.api.casebank.dto.AccountPostRequest;
import com.br.api.casebank.model.Account;
import com.br.api.casebank.util.Utils;
import org.springframework.stereotype.Component;

@Component
public class AccountPostMapper {

    public Account convertToPerson(AccountPostRequest accountPostRequest){

        return Account.builder()
                .idPerson(accountPostRequest.getIdPerson())
                .accountType(accountPostRequest.getStatus())
                .cardNumber(Utils.generateRandomNumber())
                .balance(accountPostRequest.getBalance())
                .build();
    }
}
