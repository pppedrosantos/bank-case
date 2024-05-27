package com.br.api.casebank.repository;

import com.br.api.casebank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(Long id);

    @Query("SELECT a.balance FROM Account a WHERE a.accountNumber = :accountNumber")
    Double findBalanceByAccountNumber(Long accountNumber);
}
