package com.toribo.vet_menagement.service;


import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }
    private PasswordEncoder passwordEncoder;

    @Override
    public  Client save(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);

    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<Client> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName)
    {
        return clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName,lastName);
    }
    @Override
    public Optional<Client> findByAnimalVetPassport(Long vetPassport){
        return clientRepository.findByAnimalVetPassport(vetPassport);

    }




}
