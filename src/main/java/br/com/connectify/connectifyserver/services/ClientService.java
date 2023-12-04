package br.com.connectify.connectifyserver.services;

import java.util.List;

import br.com.connectify.connectifyserver.model.Client;
import br.com.connectify.connectifyserver.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  public Client getClientById(int id) {
    return clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
  }

  public List<Client> getClients() {
    List<Client> clients = clientRepository.findAll();

    return clients;
  }

  public void deleteClientById(int id) {
    if (clientRepository.existsById(id)) {
      clientRepository.deleteById(id);
    } else {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Cliente não encontrado");
    }
  }

  public Client save(Client client) {

    return clientRepository.save(client);
  }

  public Client update(int clientId, Client updatedClient) {
    Client existingClient = getClientById(clientId);

    updateClientData(existingClient, updatedClient);

    return clientRepository.save(existingClient);
  }

  private void updateClientData(Client existingClient, Client updatedClient) {
    if (updatedClient.getName() != null) {
      existingClient.setName(updatedClient.getName());
    }

    if (updatedClient.getEmail() != null) {
      existingClient.setEmail(updatedClient.getEmail());
    }

    if (updatedClient.getPhone() != null) {
      existingClient.setPhone(updatedClient.getPhone());
    }
    if (updatedClient.getAddres() != null) {
      existingClient.setAddres(updatedClient.getAddres());
    }

    if (updatedClient.getCity() != null) {
      existingClient.setCity(updatedClient.getCity());
    }

    if (updatedClient.getCep() != null) {
      existingClient.setCep(updatedClient.getCep());
    }

    if (updatedClient.getProvince() != null) {
      existingClient.setProvince(updatedClient.getProvince());
    }
  }

}
