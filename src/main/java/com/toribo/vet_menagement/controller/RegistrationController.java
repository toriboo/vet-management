package com.toribo.vet_menagement.controller;

import com.toribo.vet_menagement.dto.ClientDTO;
import com.toribo.vet_menagement.dto.CreateClientDTO;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.service.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/api/reg")
public class RegistrationController {
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<ClientDTO> createUser(@Valid @RequestBody
                                                    CreateClientDTO request) {
        try {
            Client client = createClient(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(client));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public Client createClient(CreateClientDTO createClientDTO) throws Exception {
        if (clientRepository.findByEmail(createClientDTO.getEmail()).isPresent()) {
            throw new Exception("Пользователь с таким email уже существует");
        }

        Client client = new Client();
        client.setEmail(createClientDTO.getEmail());
        client.setPassword(passwordEncoder.encode(createClientDTO.getPassword())); // Хэширование BCrypt
        client.setFirstName(createClientDTO.getFirstName());
        client.setLastName(createClientDTO.getLastName());
        client.setCreatedAt(LocalDateTime.now());

        return clientRepository.save(client);
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setEmail(client.getEmail());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        return dto;
    }


}
