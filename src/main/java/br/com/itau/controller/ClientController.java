package br.com.itau.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.dto.ClientDTO;
import br.com.itau.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Client Controller CRUD")
public class ClientController {

	@Autowired
	private ClientService service;
	
	public ClientController(ClientService service) {
		this.service = service;
	}

	@ApiOperation("Search client with cpf")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Invalid Sintax"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/search/{clientId}")
	public ResponseEntity<ClientDTO> search(@PathVariable(name = "clientId") String clientId) {
		return service.searchClient(clientId);
	}

	@ApiOperation("Search all clients")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Invalid Sintax"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/searchAll")
	public ResponseEntity<List<ClientDTO>> searchAll() {
		return service.searchAll();
	}

	@ApiOperation("Create a new client")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Invalid Sintax"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ClientDTO client) {
		return service.saveClient(client);
	}
	
	@ApiOperation("Update")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Invalid Sintax"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping("/update")
	public ResponseEntity<HttpStatus> update(@RequestBody ClientDTO client) {
		return service.partialEditClient(client);
	}

	@ApiOperation("Delete")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Invalid Sintax"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> delete(@RequestBody ClientDTO client) {
		return service.deleteClient(client);
	}
}
