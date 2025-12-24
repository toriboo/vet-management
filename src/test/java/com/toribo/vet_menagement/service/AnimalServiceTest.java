package com.toribo.vet_menagement.service;
import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {
    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalServiceImpl animalService;

    private Animal testAnimal;
    private Client testClient;

    @BeforeEach
    void setUp() {
        testClient = new Client("client1@test.com", "password123", "Ivan", "Ivanov", LocalDateTime.now());
        testClient.setId(1L);

        testAnimal = new Animal(1L, "TestAnimal", "DOG", "TestBreed",
                12, null, "", LocalDateTime.now(), testClient);


    }


    @Test
    void findAll_ShouldReturnAllAnimals() {
        // Given
        List<Animal> expectedAnimals = Arrays.asList(testAnimal);
        when(animalRepository.findAll()).thenReturn(expectedAnimals);

        // When
        List<Animal> actualAnimals = animalService.findAll();

        // Then
        assertEquals(1, actualAnimals.size());
        assertEquals("TestAnimal", actualAnimals.get(0).getNickname());
        verify(animalRepository).findAll();
    }

    @Test
    void findById_WhenAnimalExists_ShouldReturnAnimal() {
        // Given
        when(animalRepository.findById(1L)).thenReturn(Optional.of(testAnimal));

        // When
        Optional<Animal> result = animalService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("TestAnimal", result.get().getNickname());
        verify(animalRepository).findById(1L);
    }

    @Test
    void findById_WhenAnimalNotExists_ShouldReturnEmpty() {
        // Given
        when(animalRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<Animal> result = animalService.findById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(animalRepository).findById(999L);
    }

    @Test
    void save_ShouldReturnSavedAnimal() {
        // Given
        when(animalRepository.save(any(Animal.class))).thenReturn(testAnimal);

        // When
        Animal result = animalService.save(testAnimal);

        // Then
        assertEquals("TestAnimal", result.getNickname());
        verify(animalRepository).save(testAnimal);
    }
    @Test
    void deleteById_ShouldCallRepository() {
// Given
        doNothing().when(animalRepository).deleteById(1L);
// When
        animalService.deleteById(1L);
// Then
        verify(animalRepository).deleteById(1L);
    }
    @Test
    void findByNickname_ShouldReturnMatchingAnimals() {
// Given
        List<Animal> expectedAnimals = Arrays.asList(testAnimal);
        when(animalRepository.findByNickname("Test")).thenReturn(expectedAnimals);
// When
        List<Animal> result = animalService.findByNickname("Test");
// Then
        assertEquals(1, result.size());
        assertEquals("TestAnimal", result.get(0).getNickname());
        verify(animalRepository).findByNickname("Test");
    }
    @Test
    void findByClientId_ShouldReturnAnimalsByClient() {
// Given
        List<Animal> expectedAnimals = Arrays.asList(testAnimal);
        when(animalRepository.findByClientId(1L)).thenReturn(expectedAnimals);
// When
        List<Animal> result = animalService.findByClientId(1L);
// Then
        assertEquals(1, result.size());
        assertEquals("TestAnimal", result.get(0).getNickname());
        verify(animalRepository).findByClientId(1L);
    }

    @Test
    void findBySpecies_ShouldReturnAnimalsBySepcies(){
        List<Animal> expectedAnimals = Arrays.asList(testAnimal);
        when(animalRepository.findBySpecies("DOG")).thenReturn(expectedAnimals);
// When
        List<Animal> result = animalService.findBySpecies("DOG");
// Then
        assertEquals(1, result.size());
        assertEquals("TestAnimal", result.get(0).getNickname());
        verify(animalRepository).findBySpecies("DOG");
    }

    @Test
    void findByClientAndNickname_ShouldReturnAnimalsByClientAndNickname(){

        when(animalRepository.findByClientAndNickname("TestAnimal",1L)).thenReturn(Optional.of(testAnimal));
// When
        Optional<Animal> result = animalService.findByClientAndNickname("TestAnimal",1L);
// Then
        assertTrue(result.isPresent());
        assertEquals("TestAnimal", result.get().getNickname());
        verify(animalRepository).findByClientAndNickname("TestAnimal",1L);
    }


    @Test
    void findByClientAndNickname_WhenAnimalNotExists_ShouldReturnEmpty() {
        // Given
        when(animalRepository.findByClientAndNickname("MM",999L)).thenReturn(Optional.empty());

        // When
        Optional<Animal> result = animalService.findByClientAndNickname("MM",999L);

        // Then
        assertFalse(result.isPresent());
        verify(animalRepository).findByClientAndNickname("MM",999L);
    }

}

