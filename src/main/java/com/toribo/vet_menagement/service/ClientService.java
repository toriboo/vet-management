package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Client save(Client client);
    void deleteById(Long id);
    Optional<Client> findByEmail(String email);
    Optional<Client> findByAnimalVetPassport(Long vetPassport);

}
