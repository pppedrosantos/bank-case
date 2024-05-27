package com.br.api.casebank.model;

import com.br.api.casebank.dto.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    private String cardNumber;

    private Double balance;

    private AccountType accountType;

    @Column(name = "ID_PERSON")
    private Long idPerson;

    @JsonIgnore
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "ID_PERSON", nullable = false)
    private Person person;
}
