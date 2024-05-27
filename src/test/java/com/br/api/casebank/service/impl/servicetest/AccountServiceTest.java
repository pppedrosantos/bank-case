package com.br.api.casebank.service.impl.servicetest;

import com.br.api.casebank.dto.AccountPostRequest;
import com.br.api.casebank.dto.AccountUpdateRequest;
import com.br.api.casebank.mapper.AccountPostMapper;
import com.br.api.casebank.model.Account;
import com.br.api.casebank.repository.AccountRepository;
import com.br.api.casebank.repository.PersonRepository;
import com.br.api.casebank.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AccountPostMapper accountMapper;



    @Test
    void testCreateAccount_Successfully() {

        // Arrange
        AccountPostRequest accountRequest = new AccountPostRequest();
        Account account = new Account();

        when(personRepository.existsById(accountRequest.getIdPerson())).thenReturn(true);
        when(accountMapper.convertToPerson(accountRequest)).thenReturn(account);

        // Act
        Account result = accountService.createAccount(accountRequest);

        // Assert
        assertEquals(account, result);
        verify(personRepository, times(1)).existsById(accountRequest.getIdPerson());
        verify(accountMapper, times(1)).convertToPerson(accountRequest);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testCreateAccount_IdNotFound() {
        // Arrange
        AccountPostRequest accountRequest = new AccountPostRequest();

        when(personRepository.existsById(accountRequest.getIdPerson())).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> accountService.createAccount(accountRequest));
    }

    @Test
    void testDeleteAccount_Successfully() {
        // Arrange
        Long id = 123L;

        when(accountRepository.existsByAccountNumber(id)).thenReturn(true);

        // Act
        accountService.deleteAccount(id);

        // Assert
        // Verificar se o método se o repository foi chamado.
        verify(accountRepository, times(1)).existsByAccountNumber(id);
        // Verificar se o método se o repository foi chamado.
        verify(accountRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteAccount_IdNotFound() {
        // Arrange
        Long id = 123L;

        when(accountRepository.existsByAccountNumber(id)).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> accountService.deleteAccount(id));
    }


    @Test
    void testUpdateBalanceAccount_IdNotFound() {

        // Dados de teste
        Long accountNumber = 123456L;
        AccountUpdateRequest newBalance = new AccountUpdateRequest(100.0); // Supondo que o saldo seja incrementado em 100

        // Mock do repositório que retorna Optional.empty() para simular o cenário de conta não encontrada
        when(accountRepository.findById(accountNumber)).thenReturn(Optional.empty());

        // Verificar se a exceção é lançada quando a conta não é encontrada
        assertThrows(NoSuchElementException.class, () -> accountService.updateBalanceAccount(accountNumber, newBalance));

        // Verificar se o método findById foi chamado com o número de conta correto
        verify(accountRepository).findById(accountNumber);

        // Verificar se o método save não foi chamado
        verify(accountRepository, never()).save(any());

    }

    @Test
    public void testUpdateBalanceAccount() {
        // Dados de teste

        Long accountNumber = 123456L;
        AccountUpdateRequest newBalance = new AccountUpdateRequest(100.0);

        // Mock do repositório
        Account existingAccount = new Account();
        existingAccount.setAccountNumber(accountNumber);
        existingAccount.setBalance(500.0); // saldo inicial
        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Mock do método save para retornar o objeto salvo

        // Chamar o método sob teste
        Account updatedAccount = accountService.updateBalanceAccount(accountNumber, newBalance);

        // Verificar se o saldo foi atualizado corretamente
        assertEquals(600.0, updatedAccount.getBalance()); // saldo esperado após o incremento

        assertNotEquals(601, updatedAccount.getBalance());

        // Verificar se o método findById foi chamado com o número de conta correto
        verify(accountRepository).findById(accountNumber);

        // Verificar se o método save foi chamado
        verify(accountRepository).save(existingAccount);

    }


}