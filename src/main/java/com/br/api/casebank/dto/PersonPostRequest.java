package com.br.api.casebank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonPostRequest {

    @JsonIgnore
    private Long id;

    private String name;

    private String cpf;

    @Email(message = "invalid format")
    private String email;

}
