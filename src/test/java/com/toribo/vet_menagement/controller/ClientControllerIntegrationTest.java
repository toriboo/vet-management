package com.toribo.vet_menagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toribo.vet_menagement.dto.CreateAnimalDTO;
import com.toribo.vet_menagement.dto.CreateVisitDTO;
import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.entity.Visit;
import com.toribo.vet_menagement.repository.AnimalRepository;
import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.repository.VetRepository;
import com.toribo.vet_menagement.repository.VisitRepository;
import com.toribo.vet_menagement.service.AnimalGender;
import com.toribo.vet_menagement.service.VisitStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClientControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private AnimalRepository animalRepository;

    private Client client;
    private Vet vet;
    private Animal animal;
    private Visit visit;

    @BeforeEach
    void setUp() {
        visitRepository.deleteAll();
        vetRepository.deleteAll();
        clientRepository.deleteAll();
        animalRepository.deleteAll();
    }

    @Test
    void getAllClients_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }


    @Test
    void getClientById_WhenClientExists_ShouldReturnClient() throws Exception {
        client = new Client("clnt@test.com","password123","TestName", "TestLast",LocalDateTime.now());
        clientRepository.save(client);
        mockMvc.perform(get("/api/clients/{id}", client.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("TestName"))
                .andExpect(jsonPath("$.email").value("clnt@test.com"));
    }
    @Test
    void getClientById_WhenClientNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void searchClientByEmail_ShouldReturnMatchingClients() throws Exception {
        client = new Client("clnt@test.com", "password123", "TestName", "TestLast", LocalDateTime.now());
        clientRepository.save(client);

        mockMvc.perform(get("/api/clients/email/{email}", client.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())  // <-- Expect a JSON object (Map), not an array
                .andExpect(jsonPath("$.id").exists())  // Verify ID exists (likely auto-generated)
                .andExpect(jsonPath("$.email").value(client.getEmail()));

    }
}
