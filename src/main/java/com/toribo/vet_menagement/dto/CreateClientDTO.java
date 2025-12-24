package com.toribo.vet_menagement.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateClientDTO {
    @NotBlank(message = "Email обязателен")
    private String email;
    @NotBlank(message = "пароль обязателен")
    private String password;
    @NotBlank(message = "Имя обязательно")
    private String firstName;
    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    // Геттеры/сеттеры
}
