package br.com.connectify.connectifyserver.services;

import br.com.connectify.connectifyserver.model.User;
import br.com.connectify.connectifyserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public User getUserById(int id) {
    return userRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException("Usere não existe"));
  }

  public void deleteUserById(int id) {
    if (this.userRepository.existsById(id)) {
      this.userRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException("Usuario não existe");
    }
  }

  public User save(User user) {
    return this.userRepository.save(user);
  }

  public void update(int id, User user) {

    try {
      User aux = userRepository.getReferenceById(id);
      aux.setName(user.getName());
      aux.setEmail(user.getEmail());
      aux.setPassword(user.getPassword());

      this.userRepository.save(aux);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException("Usuario não cadastrado");
    } catch (Exception e) {
      throw new EntityNotFoundException("Nao sei qual é o erro!");
    }

  }
}
