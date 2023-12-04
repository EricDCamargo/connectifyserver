package br.com.connectify.connectifyserver.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.connectify.connectifyserver.model.Client;
import br.com.connectify.connectifyserver.services.ClientService;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class ClientController {

  @Autowired
  private ClientService service;

  @GetMapping()
  public ResponseEntity<List<Client>> getClientsByUser() {
    List<Client> clients = service.getClients();
    return ResponseEntity.ok(clients);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteClient(@PathVariable int id) {
    this.service.deleteClientById(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping()
  public ResponseEntity<Client> save(@RequestBody Client client) {

    Client newClient = this.service.save(client);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newClient.getId())
        .toUri();

    return ResponseEntity.created(location).body(newClient);
  }

  @PutMapping("{id}")
  public ResponseEntity<Client> update(@PathVariable int id, @RequestBody Client client) {

    Client updatedClient = this.service.update(id, client);

    return ResponseEntity.ok().body(updatedClient);
  }

}
