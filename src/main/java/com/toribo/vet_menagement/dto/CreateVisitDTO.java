package com.toribo.vet_menagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateVisitDTO {
    @NotNull(message = "Дата приема обязатльна")
    private LocalDate visitDate;
    @NotNull(message = "Время приема обязательно")
    private LocalTime visitTime;
    @NotNull(message = "Выбор врача обязателен")
    private Long vetId;
    @NotNull(message = "Выбор животного обязателен")
    private Long vetPassport;

    public CreateVisitDTO() {}

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public LocalTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }
    public Long getVetId() {
        return vetId;
    }

    public void setVetId(Long vetId) {
        this.vetId = vetId;
    }

    public Long getVetPassport() {
        return vetPassport;
    }

    public void setVetPassport(Long vetPassport) {
        this.vetPassport = vetPassport;
    }

}
