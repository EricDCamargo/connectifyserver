package br.com.connectify.connectifyserver.services;

import java.util.List;

import br.com.connectify.connectifyserver.model.Client;
import br.com.connectify.connectifyserver.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  public List<Client> getClients() {
    return clientRepository.findAll();
  }

  public Client getClientById(int id) {
    return clientRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException("Cliente não existe"));
  }

  public void deleteClientById(int id) {
    if (this.clientRepository.existsById(id)) {
      this.clientRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException("Cliente não existe");
    }
  }

  public Client save(Client client) {
    return this.clientRepository.save(client);
  }

  public void update(int id, Client client) {

    try {
      Client aux = clientRepository.getReferenceById(id);
      aux.setName(client.getName());
      aux.setEmail(client.getEmail());
      aux.setPhone(client.getPhone());
      aux.setCity(client.getCity());
      aux.setCep(client.getCep());
      aux.setProvince(client.getProvince());
      this.clientRepository.save(aux);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException("Cliente não cadastrado");
    } catch (Exception e) {
      throw new EntityNotFoundException("Nao sei qual é o erro!");
    }

  }
}
