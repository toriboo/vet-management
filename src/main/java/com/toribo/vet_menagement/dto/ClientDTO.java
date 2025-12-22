package com.toribo.vet_menagement.dto;


import java.util.List;

public class ClientDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer animalCount;
    private List<Long> animalsId;

    public ClientDTO() {
    }

    public ClientDTO(Long id,
        String email,
        String firstName,
        String lastName)
    {
        this.email = email;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
