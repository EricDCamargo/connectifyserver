package br.com.connectify.connectifyserver.services;

import br.com.connectify.connectifyserver.dto.UserDetailsDTO;
import br.com.connectify.connectifyserver.model.Client;
import br.com.connectify.connectifyserver.model.User;
import br.com.connectify.connectifyserver.repository.ClientRepository;
import br.com.connectify.connectifyserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientService clientService;

  public UserDetailsDTO getUserById(int userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

    List<Client> clients = clientService.getClientsByUserId(userId);

    return new UserDetailsDTO(user, clients);
  }

  @Transactional
  public void deleteUserById(int id) {
    try {
      if (userRepository.existsById(id)) {
        userRepository.deleteById(id);
        clientRepository.deleteByUserId(id);
      } else {
        throw new EntityNotFoundException("Usuário não encontrado");
      }
    } catch (EmptyResultDataAccessException e) {
      // Logue a exceção para análise
      e.printStackTrace();
      throw new EntityNotFoundException("Usuário não encontrado");
    } catch (DataAccessException e) {
      // Logue a exceção para análise
      e.printStackTrace();
      throw new RuntimeException("Erro durante a exclusão do usuário", e);
    }
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public User update(int id, User updatedUser) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

    updateUserData(existingUser, updatedUser);

    return userRepository.save(existingUser);
  }

  private void updateUserData(User existingUser, User updatedUser) {
    if (updatedUser.getName() != null) {
      existingUser.setName(updatedUser.getName());
    }

    if (updatedUser.getEmail() != null) {
      existingUser.setEmail(updatedUser.getEmail());
    }

    if (updatedUser.getPassword() != null) {
      existingUser.setPassword(updatedUser.getPassword());
    }
  }
}
