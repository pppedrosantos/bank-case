package com.br.api.casebank.service.impl.serviceTest;

import com.br.api.casebank.dto.PersonPostRequest;
import com.br.api.casebank.dto.PersonUpdateRequest;
import com.br.api.casebank.exception.DateHandlerException;
import com.br.api.casebank.mapper.PersonPostMapper;
import com.br.api.casebank.mapper.PersonUpdateMapper;
import com.br.api.casebank.model.Person;
import com.br.api.casebank.repository.PersonRespository;
import com.br.api.casebank.service.impl.PersonServiceImpl;
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
public class PersonServiceTest {

    @Mock
    private PersonRespository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonUpdateMapper personUpdateMapper;

    @Mock
    private PersonPostMapper personPostMapper;

    @Test
    void testGetUser_Successfully() {
        // Arrange
        Long id = 123L;
        Person person = new Person();

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        // Act
        Person result = personService.getUser(id);

        // Assert
        assertEquals(person, result);
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    void testGetUser_IdNotFound() {
        // Arrange
        Long id = 123L;

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> personService.getUser(id));
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    void testUpdateUser_Successfully() {
        // Arrange
        Long id = 123L;
        PersonUpdateRequest personRequest = new PersonUpdateRequest();
        Person person = new Person();

        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        when(personUpdateMapper.convertToPersonUpdate(personRequest, person)).thenReturn(person);

        // Act
        Person result = personService.updateUser(id, personRequest);

        // Assert
        assertEquals(person, result);
        verify(personRepository, times(1)).findById(id);
        verify(personUpdateMapper, times(1)).convertToPersonUpdate(personRequest, person);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    void testUpdateUser_IdNotFound() {
        // Arrange
        Long id = 123L;
        PersonUpdateRequest personRequest = new PersonUpdateRequest(/* provide necessary data */);

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> personService.updateUser(id, personRequest));


    }



}
