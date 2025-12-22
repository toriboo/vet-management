package com.toribo.vet_menagement.dto;

import com.toribo.vet_menagement.service.AnimalGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateAnimalDTO {
    @NotNull(message = "Номер ветпаспорта обязателен")
    private int vetPassport;
    @NotBlank(message = "Кличка животного обязательна")
    private String nickname;
    @NotBlank(message = "Вид животного обязателен")
    private String species;
    private String breed;
    @Positive(message = "Возраст животного должен быть положительным")
    private int age;
    @NotNull(message = "Пол животного обязателен")
    private AnimalGender gender;
    private String details;

    public long getVetPassport() {
        return vetPassport;
    }

    public void setVetPassport(int vetPassport) {
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
        this.gender = gender;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
