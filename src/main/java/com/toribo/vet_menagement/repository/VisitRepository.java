package com.toribo.vet_menagement.repository;


import com.toribo.vet_menagement.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    List<Visit> findByStatus(String status);

    List<Visit> findByVetId(Long vetId);
    List<Visit> findByAnimalVetPassport(Long vetPassport);
    List <Visit> findByClientId(Long clientId);

    List<Visit> findByVisitDateTime(LocalDateTime dateTime);


}
