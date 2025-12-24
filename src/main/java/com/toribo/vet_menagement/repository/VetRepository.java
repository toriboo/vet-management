package com.toribo.vet_menagement.repository;


import com.toribo.vet_menagement.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<Vet,Long> {
    Optional<Vet> findByEmail(String email);

    List<Vet> findBySpecialization(String specialization);
}