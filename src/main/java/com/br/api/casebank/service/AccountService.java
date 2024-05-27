package com.br.api.casebank.service;

import com.br.api.casebank.dto.AccountPostRequest;
import com.br.api.casebank.dto.AccountUpdateRequest;
import com.br.api.casebank.model.Account;

public interface AccountService {

    Account createAccount(AccountPostRequest accountPostRequest);

    Void deleteAccount(Long id);

    Account updateBalanceAccount(Long accountNumber, AccountUpdateRequest balance);
}
