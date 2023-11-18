package br.com.connectify.connectifyserver.dto;

import br.com.connectify.connectifyserver.model.User;
import br.com.connectify.connectifyserver.model.Client;

import java.util.List;

public class UserDetailsDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Client> clients;

    public UserDetailsDTO(User user, List<Client> clients) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.clients = clients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
