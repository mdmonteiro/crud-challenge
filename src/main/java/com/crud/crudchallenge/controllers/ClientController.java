package com.crud.crudchallenge.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudchallenge.dto.ClientDTO;
import com.crud.crudchallenge.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	 private final ClientService clientService;

	    public ClientController(ClientService clientService) {
	        this.clientService = clientService;
	    }

	    @GetMapping(value = "/{id}")
	    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
	        ClientDTO dto = clientService.findById(id);
	        return ResponseEntity.ok(dto);
	    }

}
