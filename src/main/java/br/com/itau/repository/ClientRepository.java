package br.com.itau.repository;

import java.math.BigDecimal;
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

@Repository
public class ClientRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public BigDecimal save(Client client) {
		mapper.save(client);
		return client.getCpf();
	}

	public Client findClient(BigDecimal cpf) {
		return mapper.load(Client.class, cpf);
	}
	
	public void delete(Client client) {
		mapper.delete(client);
	}
	
	public void partialEdit(Client client) {
		mapper.save(client, buildExpression(client));
	}
	
	private DynamoDBSaveExpression buildExpression(Client client) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("cpf", new ExpectedAttributeValue(new AttributeValue().withS(client.getCpf().toString())));
		dynamoDBSaveExpression.setExpected(expectedMap);
		
		return dynamoDBSaveExpression;
	}
	
	public List<Client> findAll() {
		PaginatedScanList<Client> list = mapper.scan(Client.class, new DynamoDBScanExpression());
		list.loadAllResults();
		return list;
	}
	
}
