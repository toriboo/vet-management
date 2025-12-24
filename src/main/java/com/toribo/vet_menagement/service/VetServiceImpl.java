package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VetServiceImpl implements VetService{
    @Autowired
    private VetRepository vetRepository;


    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    @Override
    public Optional<Vet> findById(Long id) {
        return vetRepository.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);

    }

    @Override
    public Optional<Vet> findByEmail(String email) {
        return vetRepository.findByEmail(email);
    }

    @Override
    public List<Vet> findBySpecialization(String specialization) {
        return vetRepository.findBySpecialization(specialization);

    }


}
