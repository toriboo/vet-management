package com.toribo.vet_menagement.repository;

import com.toribo.vet_menagement.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByClientId(Long clientId);
    @Query("SELECT a FROM Animal a WHERE a.nickname LIKE %:partNickname%")
    List<Animal> findByNickname(@Param("partNickname") String partNickname);
    List<Animal> findBySpecies(String species);
    @Query("SELECT a FROM Animal a WHERE a.nickname = :nickname AND a.client = :client_id")
    Optional<Animal> findByClientAndNickname(@Param("nickname") String nickname, @Param("client_id") String client_id);

}
