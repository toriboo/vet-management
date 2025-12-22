package com.toribo.vet_menagement.repository;

import com.toribo.vet_menagement.entity.Client;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    List<Client> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    @Query("SELECT c FROM Client c JOIN c.animals a WHERE a.vetPassport = :vetPassport")
    Optional<Client> findByAnimalVetPassport(@Param("vetPassport") Long vetPassport);

}

