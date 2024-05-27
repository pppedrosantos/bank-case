package com.br.api.casebank.service.impl.serviceTest;

import com.br.api.casebank.dto.AccountPostRequest;
import com.br.api.casebank.dto.AccountUpdateRequest;
import com.br.api.casebank.dto.PersonPostRequest;
import com.br.api.casebank.exception.DateHandlerException;
import com.br.api.casebank.mapper.AccountPostMapper;
import com.br.api.casebank.mapper.AccountUpdateMapper;
import com.br.api.casebank.mapper.PersonPostMapper;
import com.br.api.casebank.model.Account;
import com.br.api.casebank.repository.AccountRepository;
import com.br.api.casebank.repository.PersonRespository;
import com.br.api.casebank.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountUpdateMapper accountUpdateMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private PersonRespository personRepository;

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
        verify(accountRepository, times(1)).existsByAccountNumber(id);
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
    void testUpdateBalanceAccount_Successfully() {
        // Arrange
        Long accountNumber = 123L;
        AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
        Account account = new Account();

        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account));
        when(accountUpdateMapper.convertToAccount(accountUpdateRequest, account)).thenReturn(account);

        // Act
        String result = accountService.updateBalanceAccount(accountNumber, accountUpdateRequest);

        // Assert
        assertEquals("Update Successfully", result);
        verify(accountRepository, times(1)).findById(accountNumber);
        verify(accountUpdateMapper, times(1)).convertToAccount(accountUpdateRequest, account);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testUpdateBalanceAccount_IdNotFound() {
        // Arrange
        Long accountNumber = 123L;
        AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest(/* provide necessary data */);

        // Configurando o comportamento do mock para lançar NoSuchElementException
        when(accountRepository.findById(accountNumber)).thenThrow(new NoSuchElementException("Id not found."));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> accountService.updateBalanceAccount(accountNumber, accountUpdateRequest));
    }


}