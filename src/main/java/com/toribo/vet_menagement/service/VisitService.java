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
    List<Visit> findByVetId(Long vetId);
    List<Visit> findByAnimalVetPassport(Long vetPassport);
    List<Visit> findByClientId(Long clientId);
    List<Visit> findByDateTime(LocalDateTime dateTime);
}
