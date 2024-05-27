package com.br.api.casebank.controller;

import com.br.api.casebank.dto.PersonPostRequest;
import com.br.api.casebank.dto.PersonUpdateRequest;
import com.br.api.casebank.model.Person;
import com.br.api.casebank.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/caseBank")
public class PersonController {

	@Autowired
	private PersonService service;

	@PostMapping(value = "/createPerson", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Person> createUser(@Valid @RequestBody PersonPostRequest person){
		return new  ResponseEntity<>(service.createUser(person), HttpStatus.CREATED);
	}

	@GetMapping(value = "/getPerson/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Person> getPerson(@PathVariable Long id){
		return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
	}

	@PutMapping(value = "/updatePerson/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Person> updateStatus(@PathVariable Long id,
											   @RequestBody PersonUpdateRequest personRequest){
		return new ResponseEntity<>(service.updateUser(id, personRequest), HttpStatus.OK);
	}

}