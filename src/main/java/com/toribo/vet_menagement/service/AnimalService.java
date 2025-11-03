package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
    List<Animal> findAll();
    Optional<Animal> findById(Long id);
    Animal save(Animal animal);
    void deleteById(Long id);
    List<Animal> findByNickname(String nickname);
    List<Animal> findByClientId(Long clientID);
}
