package com.br.api.casebank.service.impl;

import com.br.api.casebank.dto.PersonPostRequest;
import com.br.api.casebank.dto.PersonUpdateRequest;
import com.br.api.casebank.exception.DataHandlerException;
import com.br.api.casebank.mapper.PersonPostMapper;
import com.br.api.casebank.mapper.PersonUpdateMapper;
import com.br.api.casebank.model.Person;
import com.br.api.casebank.repository.PersonRepository;
import com.br.api.casebank.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonPostMapper personPostMapper;

    @Autowired
    PersonUpdateMapper personUpdateMapper;

    @Autowired
    PersonRepository personRepository;

    @Transactional
    public Person createUser(PersonPostRequest personPostRequest) {

        log.info("Attempt to create user.");
        try {
            Person person = personPostMapper.convertTo(personPostRequest);

            personRepository.save(person);

            return person;
        } catch (Exception e){
            throw new DataHandlerException("Error when trying to save user.");
        }


    }


    public Person getUser(Long id) {

        log.info("User search by id: {}", id);
        Optional<Person> person = personRepository.findById(id);

        if (!person.isPresent()) {
            throw new NoSuchElementException("Id not found.");
        }

        return person.get();
    }

    @Transactional
    public Person updateUser(Long id, PersonUpdateRequest personRequest){

        log.info("Updating user status.");
        Person person = personRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Id not found.")
        );
        person = personUpdateMapper.convertToPersonUpdate(personRequest, person);
        personRepository.save(person);
        log.info("User updated.");

        return person;

    }




}
