package br.com.itau.utils;

import java.util.Optional;

import br.com.itau.dto.AddressDTO;
import br.com.itau.dto.ClientDTO;
import br.com.itau.model.Address;
import br.com.itau.model.Client;

public class ClientPersistence {

	public static Optional<Client> convertDtoToModel(ClientDTO dto) {
		if(!Optional.ofNullable(dto).isEmpty()) {
			var client = new Client();
			client.setCpf(dto.getCpf());
			client.setEmail(dto.getEmail());
			client.setName(dto.getName());
			client.setPhone(dto.getPhone());
			client.setAge(dto.getAge());
			client.setClientId(dto.getClientId());
			
			if(!Optional.ofNullable(dto.getAddress()).isEmpty()) {
				var address = new Address();
				address.setCity(dto.getAddress().getCity());
				address.setHouseNumber(dto.getAddress().getHouseNumber());
				address.setPinCode(dto.getAddress().getPinCode());
				client.setAddress(address);
			} else {
				return Optional.empty();
			}
			
			return Optional.of(client);
		} else {
			return Optional.empty();
		}
	}

	public static Optional<ClientDTO> convertModelToDto(Client client) {
		if(!Optional.ofNullable(client).isEmpty()) {
			var dto = new ClientDTO();
			dto.setCpf(client.getCpf());
			dto.setEmail(client.getEmail());
			dto.setName(client.getName());
			dto.setPhone(client.getPhone());
			dto.setAge(client.getAge());
			dto.setClientId(client.getClientId());
			
			if(!Optional.ofNullable(client.getAddress()).isEmpty()) {
				var address = new AddressDTO();
				address.setCity(client.getAddress().getCity());
				address.setHouseNumber(client.getAddress().getHouseNumber());
				address.setPinCode(client.getAddress().getPinCode());
				dto.setAddress(address);
			} else {
				return Optional.empty();
			}
			
			return Optional.of(dto);
		} else {
			return Optional.empty();
		}
	}
}
