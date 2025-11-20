package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.entity.Visit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitService {
    List<Visit> findAll();
    Optional<Visit> findById(Long id);
    Visit save(Visit visit);
    void deleteById(Long id);
    Optional<Visit> findByVetId(Long vetId);
    Optional<Visit> findByVetPassport(Long vetPassport);
    Optional <Visit> findByClientId(Long clientId);
    List<Visit> findByDateTime(LocalDateTime dateTime);
}
