package com.toribo.vet_menagement.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    private Long vetPassport;

    @Column(name = "nickname",nullable = false)
    private String nickname;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "breed")
    private String breed;

    @Column( name = "age")
    private int age;

    @Column (name = "gender")
    private String gender;

    @Column (name = "details")
    private String details;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Animal(){
    }

    public Animal(Long vetPassport, String nickname,String species, LocalDateTime createdAt, Client client){
        this.vetPassport = vetPassport;
        this.nickname = nickname;
        this.species = species;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
