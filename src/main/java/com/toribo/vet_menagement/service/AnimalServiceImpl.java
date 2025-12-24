package com.toribo.vet_menagement.service;

import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class AnimalServiceImpl implements AnimalService{

    @Autowired
    private AnimalRepository animalRepository;


    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public void deleteById(Long id) {
        animalRepository.deleteById(id);

    }

    @Override
    public List<Animal> findByNickname(String nickname) {
        return animalRepository.findByNickname(nickname);
    }

    @Override
    public List<Animal> findByClientId(Long clientId) {
        return animalRepository.findByClientId(clientId);

    }

    public List<Animal> findBySpecies(String species){
        return animalRepository.findBySpecies(species);
    }

    public Optional<Animal> findByClientAndNickname(String nickname,Long clientId){
        return animalRepository.findByClientAndNickname(nickname,clientId);
    }

}
