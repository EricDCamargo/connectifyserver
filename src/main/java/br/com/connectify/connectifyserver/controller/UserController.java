package br.com.connectify.connectifyserver.controller;

import java.net.URI;

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

import br.com.connectify.connectifyserver.model.User;
import br.com.connectify.connectifyserver.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping("{id}")
  public ResponseEntity<User> getUserById(@PathVariable int id) {
    return ResponseEntity.ok(service.getUserById(id));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    this.service.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping()
  public ResponseEntity<User> save(@RequestBody User user) {

    User newUser = this.service.save(user);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newUser.getId())
        .toUri();

    return ResponseEntity.created(location).body(newUser);

  }

  @PutMapping("{id}")
  public ResponseEntity<Void> update(@PathVariable int id, @RequestBody User user) {
    this.service.update(id, user);
    return ResponseEntity.ok().build();
  }

}
