package br.com.connectify.connectifyserver.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.connectify.connectifyserver.dto.UserDetailsDTO;
import br.com.connectify.connectifyserver.model.User;
import br.com.connectify.connectifyserver.services.UserService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("{userId}")
  public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable int userId) {
    UserDetailsDTO userDetails = userService.getUserById(userId);
    return ResponseEntity.ok(userDetails);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteUser(@PathVariable int id) {

    try {
      this.userService.deleteUserById(id);
      return ResponseEntity.ok("Usuário excluído com sucesso");
    } catch (EntityNotFoundException e) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o usuário");
    }

  }

  @PostMapping()
  public ResponseEntity<User> save(@RequestBody User user) {

    User newUser = this.userService.save(user);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newUser.getId())
        .toUri();

    return ResponseEntity.created(location).body(newUser);

  }

  @PutMapping("{id}")
  public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
    User updatedUser = this.userService.update(id, user);
    return ResponseEntity.ok().body(updatedUser);
  }

}
