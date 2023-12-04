package br.com.connectify.connectifyserver.repository;

import br.com.connectify.connectifyserver.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
