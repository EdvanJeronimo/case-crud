package br.com.itau.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {

	@JsonProperty("pinCode")
	private String pinCode;
	@JsonProperty("houseNumber")
	private String houseNumber;
	@JsonProperty("city")
	private String city;
}
