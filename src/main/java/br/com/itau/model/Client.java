package br.com.itau.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@DynamoDBHashKey(attributeName = "cpf")
	private BigDecimal cpf;
	@DynamoDBAttribute(attributeName = "name")
	private String name;
	@DynamoDBAttribute(attributeName = "email")
	private String email;
	@DynamoDBAttribute(attributeName = "phone")
	private String phone;
	@DynamoDBAttribute(attributeName = "age")
	private int age;
	@DynamoDBAttribute(attributeName = "address")
	private Address address;

	
}
