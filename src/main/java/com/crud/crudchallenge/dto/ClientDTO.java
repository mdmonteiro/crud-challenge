package com.crud.crudchallenge.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDTO {

	private Long id;

	@NotBlank(message = "Campo não pode ser vazio.")
	@Size(min = 3, max = 80, message = "Campo deve ter entre 3 e 80 caracteres.")
	private String name;

	@NotBlank(message = "Campo não pode ser vazio.")
	@Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
	private String cpf;

	@NotNull(message = "Campo não pode ser nulo.")
	@Positive(message = "O preço deve ser maior que zero")
	private Double income;

	@NotNull(message = "Campo não pode ser nulo.")
	@PastOrPresent(message = "A data de nascimento deve estar no passado ou ser hoje")
	private LocalDate birthDate;

	@NotNull(message = "Campo não pode ser nulo.")
	private Integer children;
}
