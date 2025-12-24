package com.toribo.vet_menagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class VisitControllerIntegrationTest {
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

        vet = new Vet("vet@test.com","password123","TestVet","TestVet2", LocalDateTime.now(),"TestSpec");
        //vet.setId(1L);
        vetRepository.save(vet);

        client = new Client("client@test.com", "password123", "TestClient","TestClient2",LocalDateTime.now());
        //client.setId(1L);
        clientRepository.save(client);

        animal = new Animal(1L,"TestAnimal","TestSpecies","",13, AnimalGender.MALE, "",LocalDateTime.now(),client);
        animalRepository.save(animal);


    }

    @Test
    void getAllVisits_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/visits"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void createVisit_ShouldReturnCreatedVisit() throws Exception {
        CreateVisitDTO createVisitDTO = new CreateVisitDTO();
        createVisitDTO.setVisitDate(LocalDate.of(2000,10,10));
        createVisitDTO.setVisitTime(LocalTime.of(15,15,15));
        createVisitDTO.setVetId(vet.getId());
        createVisitDTO.setVetPassport(animal.getVetPassport());

        mockMvc.perform(post("/api/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createVisitDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.visitDate").value("2000-10-10T15:15:15"))
                .andExpect(jsonPath("$.status").value("SCHEDULED"));
    }

    @Test
    void getVisitById_WhenVisitExists_ShouldReturnVisit() throws Exception {
        visit = new Visit(LocalDateTime.of(2024,10,10,10,10),LocalDateTime.now(), VisitStatus.SCHEDULED,client,vet,animal);
        visitRepository.save(visit);

        mockMvc.perform(get("/api/visits/{id}", visit.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(visit.getId()))
                .andExpect(jsonPath("$.status").value("SCHEDULED"));
    }
    @Test
    void getVisitById_WhenVisitNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/visits/999"))
                .andExpect(status().isNotFound());
    }
    @Test
    void searchVisit_ShouldReturnMatchingVisits() throws Exception {
        visit = new Visit(LocalDateTime.of(2025,10,10,10,10),LocalDateTime.now(), VisitStatus.CANCELLED,client,vet,animal);


        visitRepository.save(visit);
        mockMvc.perform(get("/api/visits/search")
                        .param( "visitDateTime", "2025-10-10T10:10:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].status").value("CANCELLED"));
    }

    @Test
    void searchVisitByVet_ShouldReturnMatchingVisits() throws Exception {
        visit = new Visit(LocalDateTime.of(2025,10,10,10,10),LocalDateTime.now(), VisitStatus.CANCELLED,client,vet,animal);
        visitRepository.save(visit);
        mockMvc.perform(get("/api/visits/search/vet")
                        .param( "vetId",String.valueOf(vet.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].vetId").value(String.valueOf(vet.getId())));
    }
    @Test
    void searchVisitByAnimal_ShouldReturnMatchingVisits() throws Exception {
        visit = new Visit(LocalDateTime.of(2025,10,10,10,10),LocalDateTime.now(), VisitStatus.CANCELLED,client,vet,animal);
        visitRepository.save(visit);
        mockMvc.perform(get("/api/visits/search/animal")
                        .param( "vetPassport",String.valueOf(animal.getVetPassport())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].animalId").value(String.valueOf(animal.getVetPassport())));
    }
    @Test
    void searchVisitByClient_ShouldReturnMatchingVisits() throws Exception {
        visit = new Visit(LocalDateTime.of(2025,10,10,10,10),LocalDateTime.now(), VisitStatus.CANCELLED,client,vet,animal);
        visitRepository.save(visit);
        mockMvc.perform(get("/api/visits/search/client")
                        .param( "clientId",String.valueOf(client.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].clientId").value(String.valueOf(client.getId())));
    }
}
