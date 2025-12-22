package com.toribo.vet_menagement.controller;


import com.toribo.vet_menagement.dto.ClientDTO;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Получить всех пользователей
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> client = clientService.findAll();
        List<ClientDTO> clientDTOs = client.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOs);
    }

    // Получить пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(convertToDTO(client.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Получить пользователя по email
    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDTO> getClientByEmail(@PathVariable String email) {
        Optional<Client> client = clientService.findByEmail(email);
        if (client.isPresent()) {
            return ResponseEntity.ok(convertToDTO(client.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Конвертация Entity в DTO
    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setEmail(client.getEmail());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        return dto;
    }
}

