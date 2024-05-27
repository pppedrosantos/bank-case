package com.br.api.casebank.mapper;

import com.br.api.casebank.dto.PersonUpdateRequest;
import com.br.api.casebank.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonUpdateMapper {

    public Person convertToPersonUpdate(PersonUpdateRequest personUpdateRequest, Person person){

        return Person
                .builder()
                .id(person.getId())
                .email(person.getEmail())
                .cpf(person.getCpf())
                .name(person.getName())
                .status(personUpdateRequest.getStatus())
                .build();
    }
}
