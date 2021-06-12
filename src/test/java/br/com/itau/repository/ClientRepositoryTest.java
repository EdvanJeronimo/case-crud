package br.com.itau.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

import br.com.itau.model.Address;
import br.com.itau.model.Client;

public class ClientRepositoryTest {

	@Mock
	private PaginatedScanList<Client> scanList;
	
	@Mock
	private DynamoDBMapper mapper;
	
	@InjectMocks
	private ClientRepository repository;
	
	@BeforeEach
	public void setUp() {
		this.mapper = mock(DynamoDBMapper.class);
		this.repository = new ClientRepository(mapper);
	}
	
	private final Client client = new Client("9e25e1e3-0f40-4f27-9647-bfcafbbab5ca", new BigDecimal("98249187873"), 
			"Edvan Jeronimo", "edvan.j@gmail.com", "(11)98935-6469", 28, new Address("13212-833", "128", "Jundiaí"));

	@Test
	public void saveTest() {
		doNothing().when(mapper).save(any());
		repository.save(client);
	}
	
	@Test
	public void findClientTest() {
		when(mapper.load(any(), anyString())).thenReturn(client);
		repository.findClient(client.getClientId());
	}
	
	@Test
	public void deleteTest() {
		doNothing().when(mapper).delete(any());
		repository.delete(client);
	}
	
	@Test
	public void partialEditTest() {
		doNothing().when(mapper).save(any(), any(DynamoDBSaveExpression.class));
		repository.partialEdit(client);
	}

	@Test
	public void findAllTest() {
		when(mapper.scan(eq(Client.class), any(DynamoDBScanExpression.class))).thenReturn(scanList);
		repository.partialEdit(client);
	}
	
}
