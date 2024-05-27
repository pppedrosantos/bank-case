package com.br.api.casebank.mapper;

import com.br.api.casebank.dto.PersonPostRequest;
import com.br.api.casebank.dto.enums.ClientStatus;
import com.br.api.casebank.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonPostMapper {

    public static Person convertTo(PersonPostRequest personPostRequest){
        return Person.builder()
                .name(personPostRequest.getName())
                .cpf(personPostRequest.getCpf())
                .email(personPostRequest.getEmail())
                .status(ClientStatus.ACTIVE)
                .build();
    }
}
