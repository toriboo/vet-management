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
    public List<Visit> findByVetId(Long vetId) {
        return visitRepository.findByVetId(vetId);
    }

    @Override
    public List<Visit> findByAnimalVetPassport(Long vetPassport) {
        return visitRepository.findByAnimalVetPassport(vetPassport);
    }

    @Override
    public List<Visit> findByClientId(Long clientId) {
        return visitRepository.findByClientId(clientId);
    }

    @Override
    public List<Visit> findByDateTime(LocalDateTime dateTime) {
        return visitRepository.findByVisitDateTime(dateTime);
    }
    public List<Visit> findByStatus(String status){
        return visitRepository.findByStatus(status);
    }

}
