package br.com.itau.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import br.com.itau.model.Client;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ClientRepository {

	@Autowired
	private DynamoDBMapper mapper;
	
	public ClientRepository(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}

	public String save(Client client) {
		log.info("Saving client {}", client.getName());
		mapper.save(client);
		return client.getClientId();
	}

	public Client findClient(String clientId) {
		log.info("Search client {}", clientId);
		return mapper.load(Client.class, clientId);
	}
	
	public void delete(Client client) {
		log.info("Delete client {}", client.getName());
		mapper.delete(client);
	}
	
	public void partialEdit(Client client) {
		log.info("Update client {}", client.getName());
		mapper.save(client, buildExpression(client));
	}
	
	private DynamoDBSaveExpression buildExpression(Client client) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("clientId", new ExpectedAttributeValue(new AttributeValue().withS(client.getClientId())));
		dynamoDBSaveExpression.setExpected(expectedMap);
		
		return dynamoDBSaveExpression;
	}
	
	public List<Client> findAll() {
		PaginatedScanList<Client> list = mapper.scan(Client.class, new DynamoDBScanExpression());
		list.loadAllResults();
		log.info( "{} clients were found", list.size());
		return list;
	}
	
}
