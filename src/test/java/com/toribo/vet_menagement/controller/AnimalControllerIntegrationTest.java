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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.toribo.vet_menagement.service.AnimalGender.MALE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AnimalControllerIntegrationTest {
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

        //vet = new Vet("vet@test.com","password123","TestVet","TestVet2", LocalDateTime.now(),"TestSpec");
        //vet.setId(1L);
        //vetRepository.save(vet);

        client = new Client("client@test.com", "password123", "TestClient","TestClient2",LocalDateTime.now());
        //client.setId(1L);
        clientRepository.save(client);

       // animal = new Animal(1L,"TestAnimal","TestSpecies","",13, AnimalGender.MALE, "",LocalDateTime.now(),client);
        //animalRepository.save(animal);


    }

    @Test
    void getAllAnimals_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

//    @Test
//    void createAnimal_ShouldReturnCreatedAnimal() throws Exception {
//        CreateAnimalDTO createAnimalDTO = new CreateAnimalDTO();
//        createAnimalDTO.setAge(18);
//        createAnimalDTO.setBreed("TestBreed");
//        createAnimalDTO.setGender(MALE);
//        createAnimalDTO.setNickname("Testik");
//        createAnimalDTO.setDetails("TestDetails");
//        createAnimalDTO.setVetPassport((int) (333211+client.getId()));
//        createAnimalDTO.setSpecies("Cat");
//
//        mockMvc.perform(post("/api/animals")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(createAnimalDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.nickname").value("Testik"))
//                .andExpect(jsonPath("$.species").value("Cat"));
//    }

    @Test
    void getAnimalById_WhenAnimalExists_ShouldReturnAnimal() throws Exception {
        animal = new Animal(client.getId(), "Test", "Frog", "", 6, MALE, "", LocalDateTime.now(), client);
        animalRepository.save(animal);
        mockMvc.perform(get("/api/animals/{id}", animal.getVetPassport()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname").value("Test"))
                .andExpect(jsonPath("$.species").value("Frog"));
    }
    @Test
    void getAnimalById_WhenAnimalNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/animals/999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void searchAnimalByNickname_ShouldReturnMatchingAnimals() throws Exception {
        animal = new Animal(123123L, "Test", "Frog", "", 6, MALE, "", LocalDateTime.now(), client);
        animalRepository.save(animal);

        mockMvc.perform(get("/api/animals/search/nickname")
                        .param( "nickname",String.valueOf(animal.getNickname())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nickname").value(String.valueOf(animal.getNickname())));
    }

    @Test
    void searchAnimalsByClient_ShouldReturnMatchingAnimal() throws Exception {
        animal = new Animal(123123L, "Test", "Frog", "", 6, MALE, "", LocalDateTime.now(), client);
        animalRepository.save(animal);
        mockMvc.perform(get("/api/animals/search/client")
                        .param( "clientId",String.valueOf(animal.getClient().getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nickname").value(String.valueOf(animal.getNickname())));
    }
}
