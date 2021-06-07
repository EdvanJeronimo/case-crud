package br.com.itau.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {

	@JsonProperty("pinCode")
	private String pinCode;
	@JsonProperty("houseNumber")
	private String houseNumber;
	@JsonProperty("city")
	private String city;
}
