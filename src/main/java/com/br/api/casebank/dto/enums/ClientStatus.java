package com.br.api.casebank.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientStatus {
    ACTIVE("0"),
    DISABLED("1");

    private final String code;
}
