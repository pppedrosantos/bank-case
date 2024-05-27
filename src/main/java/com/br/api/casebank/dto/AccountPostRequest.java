package com.br.api.casebank.dto;

import com.br.api.casebank.dto.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountPostRequest {

    private Long idPerson;

    private Double balance;

    private AccountType status;

}
