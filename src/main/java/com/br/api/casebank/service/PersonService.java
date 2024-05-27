package com.br.api.casebank.service;

import com.br.api.casebank.dto.PersonPostRequest;
import com.br.api.casebank.dto.PersonUpdateRequest;
import com.br.api.casebank.model.Person;

public interface PersonService {

    Person createUser(PersonPostRequest person);

    Person getUser(Long id);

    Person updateUser(Long id, PersonUpdateRequest personRequest);

}
