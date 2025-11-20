package com.toribo.vet_menagement.repository;


import com.toribo.vet_menagement.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    List<Visit> findBYStatus(String status);

    Optional<Visit> findByVetId(Long vetId);
    Optional<Visit> findByVetPassport(Long vetPassport);
    Optional <Visit> findByClientId(Long clientId);

    List<Visit> findByDateTime(LocalDateTime dateTime);


}
