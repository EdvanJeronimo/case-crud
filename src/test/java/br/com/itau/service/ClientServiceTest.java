package br.com.itau.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.itau.dto.AddressDTO;
import br.com.itau.dto.ClientDTO;
import br.com.itau.repository.ClientRepository;
import br.com.itau.utils.ClientPersistence;

public class ClientServiceTest {

	@Mock
	private ClientRepository repository;
	@InjectMocks
	private ClientService service;
	
	@BeforeEach
	public void setUp() {
		this.repository = mock(ClientRepository.class);
		this.service = new ClientService(repository);
	}
	
	private final ClientDTO dto = new ClientDTO("9e25e1e3-0f40-4f27-9647-bfcafbbab5ca", new BigDecimal("98249187873"), 
			"Edvan Jeronimo", "edvan.j@gmail.com", "(11)98935-6469", 28, new AddressDTO("13212-833", "128", "Jundiaí"));
	
	@Test
	public void saveClientTest() {
		when(repository.save(any())).thenReturn(dto.getClientId());
		assertEquals(dto.getClientId(), "9e25e1e3-0f40-4f27-9647-bfcafbbab5ca");
		service.saveClient(dto);
	}
	
	@Test
	public void searchClientTest() {
		when(repository.findClient(anyString())).thenReturn(ClientPersistence.convertDtoToModel(dto).get());
		service.searchClient(dto.getClientId());
	}
	
	@Test
	public void searchAllTest() {
		when(repository.findAll()).thenReturn(List.of(ClientPersistence.convertDtoToModel(dto).get()));
		service.searchAll();
	}
	
	@Test
	public void partialEditClientTest() {
		doNothing().when(repository).partialEdit(any());
		service.partialEditClient(dto);
	}
	

	@Test
	public void deleteClientTest() {
		doNothing().when(repository).delete(any());
		service.deleteClient(dto);
	}
	
}
