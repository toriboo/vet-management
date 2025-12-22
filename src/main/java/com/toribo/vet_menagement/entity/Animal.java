package com.toribo.vet_menagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toribo.vet_menagement.service.AnimalGender;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "animals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Animal {
    @Id
    @Column(name = "vet_passport", nullable = false, unique = true)
    private Long vetPassport;

    @Column(nullable = false)
    private String nickname;

    @Column( nullable = false)
    private String species;

    @Column()
    private String breed;

    @Column()
    private int age;

    @Enumerated(EnumType.STRING)
    private AnimalGender gender;

    @Column ()
    private String details;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    public Animal(){
    }

    public Animal(Long vetPassport, String nickname, String species, String breed,
                  int age, AnimalGender gender, String details, LocalDateTime createdAt, Client client) {
        this.vetPassport = vetPassport;
        this.nickname = nickname;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.gender = gender != null ? gender : AnimalGender.UNDEFINED;
        this.details = details;
        this.createdAt = createdAt;
        this.client = client;
    }
    public Long getVetPassport() {
        return vetPassport;
    }
    public void setVetPassport(Long vetPassport) {
        this.vetPassport = vetPassport;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AnimalGender getGender() {
        return gender;
    }

    public void setGender(AnimalGender gender) {
        this.gender = gender != null ? gender : AnimalGender.UNDEFINED;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
