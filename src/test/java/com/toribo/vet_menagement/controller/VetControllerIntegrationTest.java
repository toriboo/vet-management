package com.toribo.vet_menagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.entity.Visit;
import com.toribo.vet_menagement.repository.AnimalRepository;
import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.repository.VetRepository;
import com.toribo.vet_menagement.repository.VisitRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class VetControllerIntegrationTest {
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
    void getAllVets_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/vets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }


    @Test
    void getVetById_WhenVetExists_ShouldReturnVet() throws Exception {
        vet = new Vet("clnt@test.com","password123","TestName", "TestLast",LocalDateTime.now(),"Spec");
        vetRepository.save(vet);
        mockMvc.perform(get("/api/vets/{id}", vet.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("TestName"))
                .andExpect(jsonPath("$.email").value("clnt@test.com"));
    }
    @Test
    void getVetById_WhenVetNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void searchVetByEmail_ShouldReturnMatchingVets() throws Exception {
        vet = new Vet("clnt@test.com", "password123", "TestName", "TestLast", LocalDateTime.now(), "Spec");
        vetRepository.save(vet);

        mockMvc.perform(get("/api/vets/search/email/Email")
                        .param("email", vet.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())  // Expect a JSON array (list of vets)
                .andExpect(jsonPath("$.length()").value(1))  // Verify the array has 1 vet (adjust if multiple expected)
                .andExpect(jsonPath("$[0].id").exists())  // Check the first vet's ID exists (note: $[0] for array indexing)
                .andExpect(jsonPath("$[0].email").value(vet.getEmail()));
    }
    

        @Test
    void searchVetBySpec_ShouldReturnMatchingVets() throws Exception {
        vet = new Vet("clnt@test.com", "password123", "TestName", "TestLast", LocalDateTime.now(), "Spec");
        vetRepository.save(vet);

        mockMvc.perform(get("/api/vets/search/spec/Spec")
                        .param("specialization", vet.getSpecialization()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].specialization").value(vet.getSpecialization()));
    }
}
