package com.toribo.vet_menagement.dto;


import com.toribo.vet_menagement.service.VisitStatus;


import java.time.LocalDateTime;

public class VisitDTO {

    private Long id;
    private LocalDateTime visitDate;
    private  Long animalId;
    private String animalNickname;
    private Long vetId;
    private String vetName;
    private Long clientId;
    private String clientName;
    private VisitStatus status;

    public VisitDTO() {}

    public VisitDTO(Long id,
        LocalDateTime visitDate,
        Long animalId,
        String animalNickname,
        Long vetId,
        String vetName,
        Long clientId,
        String clientName,
        VisitStatus status)
    {
        this.id = id;
        this.visitDate = visitDate;
        this.animalId = animalId;
        this.animalNickname = animalNickname;
        this.vetId = vetId;
        this.vetName = vetName;
        this.clientId = clientId;
        this.clientName = clientName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getVetId() {
        return vetId;
    }

    public void setVetId(Long vetId) {
        this.vetId = vetId;
    }

    public String getAnimalNickname() {
        return animalNickname;
    }

    public void setAnimalNickname(String animalNickname) {
        this.animalNickname = animalNickname;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

}
