package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.repository.AnimalRepository;
import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.repository.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class VetServiceTest {
    @Mock
    private VetRepository vetRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private VetServiceImpl vetService;

    private Vet testVet;

    @BeforeEach
    void setUp() {
        testVet = new Vet();
        testVet.setId(1L);
        testVet.setEmail("test@vet.com");
        testVet.setPassword("password123");
        testVet.setFirstName("Тест");
        testVet.setLastName("Vet");
        testVet.setCreatedAt(LocalDateTime.now());
        testVet.setSpecialization("Test");
    }

    @Test
    void findByEmail_WhenUserExists_ShouldReturnUser() {
// Given
        when(vetRepository.findByEmail("test@vet.com")).thenReturn(Optional.of(testVet));
// When
        Optional<Vet> result = vetService.findByEmail("test@vet.com");
// Then
        assertTrue(result.isPresent());
        assertEquals("test@vet.com", result.get().getEmail());
        verify(vetRepository).findByEmail("test@vet.com");
    }

    @Test
    void findByEmail_WhenUserNotExists_ShouldReturnEmpty() {
// Given
        when(vetRepository.findByEmail("nonexistent@vet.com")).thenReturn(Optional.empty());
// When
        Optional<Vet> result = vetService.findByEmail("nonexistent@vet.com");
// Then
        assertFalse(result.isPresent());
        verify(vetRepository).findByEmail("nonexistent@vet.com");
    }

    @Test
    void findBySpecialization_ShouldReturnVetsBySepecialization(){
        List<Vet> expectedVets = Arrays.asList(testVet);
        when(vetRepository.findBySpecialization("Test")).thenReturn(expectedVets);
// When
        List<Vet> result = vetService.findBySpecialization("Test");
// Then
        assertEquals(1, result.size());
        assertEquals("Тест", result.get(0).getFirstName());
        verify(vetRepository).findBySpecialization("Test");
    }

    @Test
    void save_ShouldReturnSaveVet() {
        // Given
        when(vetRepository.save(any(Vet.class))).thenReturn(testVet);

        // When
        Vet result = vetService.save(testVet);

        // Then
        assertEquals("Тест", result.getFirstName());
        verify(vetRepository).save(testVet);
    }
    @Test
    void deleteById_ShouldCallRepository() {
// Given
        doNothing().when(vetRepository).deleteById(1L);
// When
        vetService.deleteById(1L);
// Then
        verify(vetRepository).deleteById(1L);
    }
}