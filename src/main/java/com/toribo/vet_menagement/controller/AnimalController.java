package com.toribo.vet_menagement.controller;


import com.toribo.vet_menagement.dto.AnimalDTO;
import com.toribo.vet_menagement.dto.CreateAnimalDTO;

import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.entity.Client;

import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private ClientRepository clientRepository;
    // Получить писок всех животных
    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        List<Animal> animals = animalService.findAll();
        List<AnimalDTO> animalDTOs = animals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(animalDTOs);
    }

    // Получить animal по ID
    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable Long id) {
        Optional<Animal> animal = animalService.findById(id);
        if (animal.isPresent()) {
            return ResponseEntity.ok(convertToDTO(animal.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить прием по id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimals(@PathVariable Long id) {
        Optional<Animal> animal = animalService.findById(id);
        if (animal.isPresent()) {
            animalService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> createAnimal(@Valid @RequestBody CreateAnimalDTO
                                                        createAnimalDTO) {
        try {
            Animal animal = convertToEntity(createAnimalDTO);
            Animal savedAnimal = animalService.save(animal);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedAnimal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Обновить животное
    @PutMapping("/{id}")
    public ResponseEntity<AnimalDTO> updateAnimal(@PathVariable Long id,
                                               @Valid @RequestBody CreateAnimalDTO createAnimalDTO)
    {
        Optional<Animal> existingAnimal = animalService.findById(id);
        if (existingAnimal.isPresent()) {
            try {
                Animal animal = convertToEntity(createAnimalDTO);
                animal.setVetPassport(id);
                Animal updatedAnimal = animalService.save(animal);
                return ResponseEntity.ok(convertToDTO(updatedAnimal));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // поиск приема по id клиента
    @GetMapping("/search/clientId")
    public ResponseEntity<List<AnimalDTO>> searchVAnimalsByClientId(@RequestParam Long clientId) {
        List<Animal> animals = animalService.findByClientId(clientId);
        List<AnimalDTO> animalDTOs = animals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(animalDTOs);
    }


    @GetMapping("/search/Nickname")
    public ResponseEntity<List<AnimalDTO>> searchAnimalByNickname(@RequestParam String nickname) {
        List<Animal> animals = animalService.findByNickname(nickname);
        List<AnimalDTO> animalDTOs = animals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(animalDTOs);
    }

    // Конвертация Entity в DTO
    private AnimalDTO convertToDTO(Animal animal) {
        AnimalDTO dto = new AnimalDTO();
        dto.setVetPassport(animal.getVetPassport());
        dto.setNickname(animal.getNickname());
        dto.setSpecies(animal.getSpecies());
        dto.setBreed(animal.getBreed());
        dto.setAge(animal.getAge());
        dto.setDetails(animal.getDetails());
        if (animal.getClient()!= null){
            dto.setClientId((animal.getClient()).getId());
            dto.setClientName(animal.getClient().getLastName()+ " " +
                    animal.getClient().getFirstName());
        }
        dto.setGender(animal.getGender());
        return dto;
    }

    // Конвертация DTO в Entity
    private Animal convertToEntity(CreateAnimalDTO dto) {
        Animal animal = new Animal();
        animal.setGender(dto.getGender());
        animal.setSpecies(dto.getSpecies());
        animal.setAge(dto.getAge());
        animal.setBreed(dto.getBreed());
        animal.setDetails(dto.getDetails());
        animal.setVetPassport(dto.getVetPassport());
        animal.setCreatedAt(LocalDateTime.now());
        animal.setNickname(dto.getNickname());
        animal.setClient(clientRepository.findById(19L).get());
        return animal;
    }
}