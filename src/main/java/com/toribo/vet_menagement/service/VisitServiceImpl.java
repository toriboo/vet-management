package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Visit;
import com.toribo.vet_menagement.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class VisitServiceImpl implements VisitService{
    @Autowired
    VisitRepository visitRepository;

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);

    }

    @Override
    public Optional<Visit> findByVetId(Long vetId) {
        return visitRepository.findByVetId(vetId);
    }

    @Override
    public Optional<Visit> findByVetPassport(Long vetPassport) {
        return visitRepository.findByVetPassport(vetPassport);
    }

    @Override
    public Optional<Visit> findByClientId(Long clientId) {
        return visitRepository.findByClientId(clientId);
    }

    @Override
    public List<Visit> findByDateTime(LocalDateTime dateTime) {
        return visitRepository.findByDateTime(dateTime);
    }
    public List<Visit> findByStatus(String status){
        return visitRepository.findBYStatus(status);
    }

}
