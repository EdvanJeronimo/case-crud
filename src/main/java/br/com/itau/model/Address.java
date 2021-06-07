package br.com.itau.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Address {

	@DynamoDBAttribute
	private String pinCode;
	@DynamoDBAttribute
	private String houseNumber;
	@DynamoDBAttribute
	private String city;
	
}
