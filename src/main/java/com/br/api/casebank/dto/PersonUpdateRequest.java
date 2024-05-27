package com.br.api.casebank.dto;

import com.br.api.casebank.dto.enums.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonUpdateRequest {

    private ClientStatus status;
}
