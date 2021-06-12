package br.com.itau.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import br.com.itau.dto.AddressDTO;
import br.com.itau.dto.ClientDTO;
import br.com.itau.service.ClientService;

public class ClientControllerTest {

	@Mock
	private ClientService service;
	@InjectMocks
	private ClientController controller;
	
	private final ClientDTO dto = new ClientDTO("9e25e1e3-0f40-4f27-9647-bfcafbbab5ca", new BigDecimal("98249187873"), 
			"Edvan Jeronimo", "edvan.j@gmail.com", "(11)98935-6469", 28, new AddressDTO("13212-833", "128", "Jundiaí"));

	@BeforeEach
	public void setUp() {
		this.service = mock(ClientService.class);
		this.controller = new ClientController(service);
	}
	
	@Test
	public void searchTest() {
		when(service.searchClient(anyString())).thenReturn(ResponseEntity.ok(dto));
		assertEquals(dto.getClientId(), "9e25e1e3-0f40-4f27-9647-bfcafbbab5ca");
		controller.search(dto.getClientId());
	}
	
	@Test
	public void searchAllTest() {
		when(service.searchAll()).thenReturn(ResponseEntity.ok(List.of(dto)));
		controller.searchAll();
	}
	
	@Test
	public void saveTest() {
		when(service.saveClient(any())).thenReturn(ResponseEntity.noContent().build());
		controller.save(dto);
	}
	
	@Test
	public void updateTest() {
		when(service.partialEditClient(any())).thenReturn(ResponseEntity.ok().build());
		controller.update(dto);
	}
	
	@Test
	public void deleteTest() {
		when(service.deleteClient(any())).thenReturn(ResponseEntity.noContent().build());
		controller.delete(dto);
	}
}
