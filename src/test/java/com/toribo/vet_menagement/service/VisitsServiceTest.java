package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.entity.Visit;
import com.toribo.vet_menagement.repository.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VisitsServiceTest {
    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitServiceImpl visitService;

    private Animal testAnimal;
    private Client testClient;
    private Vet testVet;
    private Visit testVisit;

    @BeforeEach
    void setUp() {
        testClient = new Client("client1@test.com", "password123", "Ivan", "Ivanov", LocalDateTime.now());
        testClient.setId(1L);

        testAnimal = new Animal(1L, "TestAnimal", "DOG", "TestBreed",
                12, null, "", LocalDateTime.now(), testClient);
        testVet = new Vet("vet1@test.com", "password123", "FirstVet",
                "LastVet", LocalDateTime.now(),"Spec");
        testVet.setId(1L);
        testVisit = new Visit(LocalDateTime.of(2025,12,12,14,30),LocalDateTime.now(),
                VisitStatus.COMPLETED,testClient,testVet,testAnimal);
    }

    @Test
    void findAll_ShouldReturnAllVisits() {
        // Given
        List<Visit> expectedVisits = Arrays.asList(testVisit);
        when(visitRepository.findAll()).thenReturn(expectedVisits);

        // When
        List<Visit> actualVisits = visitService.findAll();

        // Then
        assertEquals(1, actualVisits.size());
        assertEquals(LocalDateTime.of(2025,12,12,14,30), actualVisits.get(0).getVisitDate());
        verify(visitRepository).findAll();
    }

    @Test
    void findById_WhenAnimalExists_ShouldReturnAnimal() {
        // Given
        when(visitRepository.findById(1L)).thenReturn(Optional.of(testVisit));

        // When
        Optional<Visit> result = visitService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(LocalDateTime.of(2025,12,12,14,30), result.get().getVisitDate());
        verify(visitRepository).findById(1L);
    }

    @Test
    void findById_WhenVisitNotExists_ShouldReturnEmpty() {
        // Given
        when(visitRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<Visit> result = visitService.findById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(visitRepository).findById(999L);
    }

    @Test
    void save_ShouldReturnSavedAnimal() {
        // Given
        when(visitRepository.save(any(Visit.class))).thenReturn(testVisit);

        // When
        Visit result = visitService.save(testVisit);

        // Then
        assertEquals(LocalDateTime.of(2025,12,12,14,30), result.getVisitDate());
        verify(visitRepository).save(testVisit);
    }
    @Test
    void deleteById_ShouldCallRepository() {
// Given
        doNothing().when(visitRepository).deleteById(1L);
// When
        visitService.deleteById(1L);
// Then
        verify(visitRepository).deleteById(1L);
    }

    @Test
    void findByDateTime_ShouldReturnMatchingVisits() {
// Given
        List<Visit> expectedVisits = Arrays.asList(testVisit);
        when(visitRepository.findByVisitDateTime(LocalDateTime.of(2025,12,12,14,30))).
                thenReturn(expectedVisits);
// When
        List<Visit> result = visitService.findByDateTime(LocalDateTime.of(2025,12,12,14,30));
// Then

        assertEquals(1, result.size());
        assertEquals(LocalDateTime.of(2025,12,12,14,30), result.get(0).getVisitDate());
        verify(visitRepository).findByVisitDateTime(LocalDateTime.of(2025,12,12,14,30));
    }
    @Test
    void findByClientId_ShouldReturnVisitsByClient() {
// Given
        List<Visit> expectedVisits = Arrays.asList(testVisit);
        when(visitRepository.findByClientId(1L)).thenReturn(expectedVisits);
// When
        List<Visit> result = visitService.findByClientId(1L);
// Then
        assertEquals(1, result.size());
        assertEquals(LocalDateTime.of(2025,12,12,14,30), result.get(0).getVisitDate());
        verify(visitRepository).findByClientId(1L);
    }

    @Test
    void findByAnimalVetPassport_ShouldReturnVisitsByAnimal() {
// Given
        List<Visit> expectedVisits = Arrays.asList(testVisit);
        when(visitRepository.findByAnimalVetPassport(1L)).thenReturn(expectedVisits);
// When
        List<Visit> result = visitService.findByAnimalVetPassport(1L);
// Then
        assertEquals(1, result.size());
        assertEquals(LocalDateTime.of(2025,12,12,14,30), result.get(0).getVisitDate());
        verify(visitRepository).findByAnimalVetPassport(1L);
    }

    @Test
    void findByVetId_ShouldReturnVisitsByVet() {
// Given
        List<Visit> expectedVisits = Arrays.asList(testVisit);
        when(visitRepository.findByVetId(1L)).thenReturn(expectedVisits);
// When
        List<Visit> result = visitService.findByVetId(1L);
// Then
        assertEquals(1, result.size());
        assertEquals(LocalDateTime.of(2025,12,12,14,30), result.get(0).getVisitDate());
        verify(visitRepository).findByVetId(1L);
    }



}
