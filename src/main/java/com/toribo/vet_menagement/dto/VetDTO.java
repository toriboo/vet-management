package com.toribo.vet_menagement.dto;


public class VetDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String specialization;

    public VetDTO() {}

    public VetDTO(Long id, String email, String firstName, String lastName, String specialization) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }
    public Long getId(){
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }


}
