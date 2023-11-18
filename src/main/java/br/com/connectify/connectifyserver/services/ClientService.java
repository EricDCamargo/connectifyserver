package br.com.connectify.connectifyserver.services;

import java.util.List;

import br.com.connectify.connectifyserver.model.Client;
import br.com.connectify.connectifyserver.model.User;
import br.com.connectify.connectifyserver.repository.ClientRepository;
import br.com.connectify.connectifyserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private UserRepository userRepository;

  public Client getClientById(int id) {
    return clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
  }

  public List<Client> getClientsByUserId(int userId) {
    List<Client> clients = clientRepository.findByUserId(userId);

    return clients;
  }

  public void deleteClientById(int id) {
    if (clientRepository.existsById(id)) {
      clientRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException("Cliente não encontrado");
    }
  }

  public Client save(int userId, Client client) {
    User user = getUserById(userId);
    client.setUser(user);
    return clientRepository.save(client);
  }

  public Client update(int clientId, Client updatedClient) {
    Client existingClient = getClientById(clientId);

    updateClientData(existingClient, updatedClient);

    return clientRepository.save(existingClient);
  }

  private User getUserById(int userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
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
