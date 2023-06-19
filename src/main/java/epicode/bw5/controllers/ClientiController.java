package epicode.bw5.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epicode.bw5.entities.Cliente;
import epicode.bw5.entities.payloads.ModificaClientePayload;
import epicode.bw5.services.ClientiService;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
	@Autowired
	ClientiService clientiService;

	@GetMapping("")
	public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return clientiService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente createCliente(@RequestBody ModificaClientePayload body) {
		return clientiService.create(body);
	}

	@PutMapping("/{id}")
	public Cliente getByIdAndUpdate(@PathVariable UUID id, @RequestBody ModificaClientePayload body) {
		return clientiService.findByIdAndUpdate(id, body);
	}

	@GetMapping("/{id}")
	public Cliente getById(@PathVariable UUID id) throws Exception {
		return clientiService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable UUID id) {
		clientiService.findByIdAndDelete(id);
	}
}
