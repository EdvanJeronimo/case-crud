package br.com.itau.service;

import java.math.BigDecimal;
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

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	public ResponseEntity<BigDecimal> saveClient(ClientDTO dto) {
		Optional<Client> client = ClientPersistence.convertDtoToModel(dto);
		
		if(client.isPresent()) {
			try {
				if(notExistsClientInDB(client.get())) {
					BigDecimal cpf = repository.save(client.get());
					return ResponseEntity.status(HttpStatus.CREATED).body(cpf);
				} else {
					return ResponseEntity.badRequest().body(client.get().getCpf());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	private boolean notExistsClientInDB(Client client) {
		ResponseEntity<ClientDTO> response = searchClient(client.getCpf());
		
		if(response.getStatusCodeValue() == 204) {
			return true;
		} else {
			return false;
		}
	}

	public ResponseEntity<ClientDTO> searchClient(BigDecimal cpf) {
		try {
			Client client = repository.findClient(cpf);
			if(Objects.nonNull(client)) {
				Optional<ClientDTO> dto = ClientPersistence.convertModelToDto(client);
				return ResponseEntity.ok(dto.get());
			} else {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	public ResponseEntity<List<ClientDTO>> searchAll() {
		List<ClientDTO> listReturn = new ArrayList<>();
		
		for(Client client : repository.findAll()) {
			listReturn.add(ClientPersistence.convertModelToDto(client).get());
		}

		if (listReturn.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(listReturn);
		}
	}
}
