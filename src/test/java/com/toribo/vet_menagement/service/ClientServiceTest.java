package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client testClient;

    @BeforeEach
    void setUp() {
        testClient = new Client();
        testClient.setId(1L);
        testClient.setEmail("test@vet.com");
        testClient.setPassword("password123");
        testClient.setFirstName("Тест");
        testClient.setLastName("Пользователь");
        testClient.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void save_NewUser_ShouldEncodePassword() {
        // Given
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(clientRepository.save(any(Client.class))).thenReturn(testClient);
// When
        Client result = clientService.save(testClient);
// Then
        verify(passwordEncoder).encode("password123");
        verify(clientRepository).save(testClient);
        assertEquals("test@vet.com", result.getEmail());
    }
    @Test
    void findByEmail_WhenUserExists_ShouldReturnUser() {
// Given
        when(clientRepository.findByEmail("test@vet.com")).thenReturn(Optional.of(testClient));
// When
        Optional<Client> result = clientService.findByEmail("test@vet.com");
// Then
        assertTrue(result.isPresent());
        assertEquals("test@vet.com", result.get().getEmail());
        verify(clientRepository).findByEmail("test@vet.com");
    }
    @Test
    void findByEmail_WhenUserNotExists_ShouldReturnEmpty() {
// Given
        when(clientRepository.findByEmail("nonexistent@vet.com")).thenReturn(Optional.empty());
// When
        Optional<Client> result = clientService.findByEmail("nonexistent@vet.com");
// Then
        assertFalse(result.isPresent());
        verify(clientRepository).findByEmail("nonexistent@vet.com");
    }
}

