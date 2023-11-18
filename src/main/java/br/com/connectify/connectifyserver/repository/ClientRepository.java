package br.com.connectify.connectifyserver.repository;

import br.com.connectify.connectifyserver.model.Client;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {

  @Query("SELECT c FROM Client c WHERE c.user.id = :userId")
  List<Client> findByUserId(@Param("userId") int userId);

  @Modifying
  @Query("DELETE FROM Client c WHERE c.user.id = :userId")
  void deleteByUserId(@Param("userId") int userId);
}
