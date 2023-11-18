package br.com.connectify.connectifyserver.repository;

import br.com.connectify.connectifyserver.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
