package com.br.api.casebank.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    CHECKING_ACCOUNT("0"),
    SAVINGS_ACCOUNT("1");

    private final String code;
}
