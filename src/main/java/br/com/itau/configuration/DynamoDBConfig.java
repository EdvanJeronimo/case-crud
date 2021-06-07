package br.com.itau.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {

	@Value("${aws.service-endpoint}")
	private String SERVICE_ENDPOINT;
	@Value("${aws.region}")
	private String REGION;
	@Value("${aws.access.key}")
	private String ACCESS_KEY;
	@Value("${aws.access.pwd}")
	private String PWD_ACCESS;

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(awsDynamoDBConfig());
	}

	private AmazonDynamoDB awsDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, PWD_ACCESS))).build();
	}
}
