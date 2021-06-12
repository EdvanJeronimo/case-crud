package br.com.itau.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.itau.dto.ClientDTO;
import br.com.itau.model.Client;
import br.com.itau.repository.ClientRepository;
import br.com.itau.utils.ClientPersistence;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public ClientService (ClientRepository repository) {
		this.repository = repository;
	}

	public ResponseEntity<String> saveClient(ClientDTO dto) {
		Optional<Client> client = ClientPersistence.convertDtoToModel(dto);
		
		if(client.isPresent()) {
			try {
				String clientId = repository.save(client.get());
				return ResponseEntity.status(HttpStatus.CREATED).body(clientId);
			} catch (Exception e) {
				log.error(e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	public ResponseEntity<ClientDTO> searchClient(String clientId) {
		try {
			Client client = repository.findClient(clientId);
			if(Objects.nonNull(client)) {
				Optional<ClientDTO> dto = ClientPersistence.convertModelToDto(client);
				return ResponseEntity.ok(dto.get());
			} else {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	public ResponseEntity<HttpStatus> partialEditClient(ClientDTO dto) {
		try {
			Optional<Client> client = ClientPersistence.convertDtoToModel(dto);
			
			if(Optional.of(client).isPresent()) {
				repository.partialEdit(client.get());
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	public ResponseEntity<HttpStatus> deleteClient(ClientDTO dto) {
		Optional<Client> client = ClientPersistence.convertDtoToModel(dto);
		
		if(client.isPresent()) {
			try {
				repository.delete(client.get());
				return ResponseEntity.noContent().build();
			} catch (Exception e) {
				log.error(e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	public ResponseEntity<List<ClientDTO>> searchAll() {
		List<ClientDTO> listReturn = new ArrayList<>();
		try {
			for(Client client : repository.findAll()) {
				listReturn.add(ClientPersistence.convertModelToDto(client).get());
			}
			
			if (listReturn.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listReturn);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
