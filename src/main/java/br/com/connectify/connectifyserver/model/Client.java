package br.com.connectify.connectifyserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "TBL_CLIENT")
@Data
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotEmpty(message = "O nome não pode estar vazio")
  private String name;

  @NotEmpty(message = "O email não pode estar vazio")
  @Email(message = "Informe um email válido")
  private String email;

  @NotEmpty(message = "O telefone não pode estar vazio")
  private String phone;

  @NotEmpty(message = "A cidade não pode estar vazio")
  private String city;

  @NotEmpty(message = "O CEP não pode estar vazio")
  private String cep;

  @NotEmpty(message = "O estado não pode estar vazio")
  private String province;

}
