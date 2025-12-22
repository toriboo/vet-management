package com.toribo.vet_menagement.dto;

import com.toribo.vet_menagement.service.AnimalGender;


public class AnimalDTO {
    private Long vetPassport;
    private String nickname;
    private String species;
    private String breed;
    private int age;
    private AnimalGender gender;
    private String details;
    private Long clientId;
    private String clientName;

    public AnimalDTO(){
    }

    public AnimalDTO(Long vetPassport,
        String nickname,
        String species,
        String breed,
        int age,
        AnimalGender gender,
        String details,
        Long clientId,
        String clientName){

        this.vetPassport = vetPassport;
        this.nickname = nickname;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.details = details;
        this.clientId = clientId;
        this.clientName = clientName;


    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Long getVetPassport() {
        return vetPassport;
    }

    public void setVetPassport(Long vetPassport) {
        this.vetPassport = vetPassport;
    }


    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public AnimalGender getGender() {
        return gender;
    }

    public void setGender(AnimalGender gender) {
        this.gender = gender;
    }


}
