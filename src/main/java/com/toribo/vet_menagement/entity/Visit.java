package com.toribo.vet_menagement.entity;


import com.toribo.vet_menagement.service.VisitStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "visits")

public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_date",nullable = false)
    private LocalDateTime visitDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "vetPassport", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "vet_id", nullable = false)
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    public Visit() {}

    public Visit(LocalDateTime visitDate, LocalDateTime createdAt, VisitStatus status, Client client, Vet vet, Animal animal){
        this.visitDate = visitDate;
        this.createdAt = createdAt;
        this.status = status;
        this.client = client;
        this.animal = animal;
        this.vet = vet;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }
    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Vet getVet() {
        return vet;
    }
    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public VisitStatus getStatus() {
        return status;
    }
    public void setStatus(VisitStatus status) {
        this.status = status;
    }
}
