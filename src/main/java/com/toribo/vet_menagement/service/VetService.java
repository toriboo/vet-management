package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Vet;

import java.util.List;
import java.util.Optional;

public interface VetService {
    List<Vet> findAll();
    Optional<Vet> findById(Long id);
    Vet save(Vet vet);
    void deleteById(Long id);
    Optional<Vet> findByEmail(String email);
    Optional<Vet> findBySpecialization(String specialization);
}
