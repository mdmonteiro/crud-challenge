package com.crud.crudchallenge.services;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crud.crudchallenge.dto.ClientDTO;
import com.crud.crudchallenge.entities.Client;
import com.crud.crudchallenge.repositories.ClientRepository;
import com.crud.crudchallenge.services.exceptions.DataBaseException;
import com.crud.crudchallenge.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	private ModelMapper mapper;

	public ClientService(ClientRepository clientRepository, ModelMapper mapper) {
		this.clientRepository = clientRepository;
		this.mapper = mapper;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

		return mapper.map(client, ClientDTO.class);
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> clients = clientRepository.findAll(pageable);

		return convertToDtoList(clients);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = convertDtoToObject(dto);
		clientRepository.save(client);

		return mapper.map(client, ClientDTO.class);
		
	}
	
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client product = clientRepository.getReferenceById(id);
			convertDtoToEntity(dto, product);
			clientRepository.save(product);

			return mapper.map(product, ClientDTO.class);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Produto não encontrado.");
		}

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Produto não encontrado.");
		}
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial.");
		}
	}

	public Page<ClientDTO> convertToDtoList(Page<Client> clients) {
		return clients.map(p -> mapper.map(p, ClientDTO.class));
	}

	public Client convertDtoToObject(ClientDTO dto) {
		return mapper.map(dto, Client.class);
	}

	private void convertDtoToEntity(ClientDTO dto, Client client) {
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setName(dto.getName());
	}

}
