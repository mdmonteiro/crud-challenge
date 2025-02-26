package com.crud.crudchallenge.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClientDTO {	
	
	private Long id;
	
	private String name;
	
	private String cpf;
	
	private Double income;
	
	private LocalDate birthDate;
	
	private Integer children;
}
