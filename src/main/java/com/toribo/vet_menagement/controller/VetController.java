package com.toribo.vet_menagement.controller;


import com.toribo.vet_menagement.dto.VetDTO;
import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/vets")
public class VetController {

    @Autowired
    private VetService vetService;

    // Получить писок всех ветеринаров
    @GetMapping
    public ResponseEntity<List<VetDTO>> getAllVets() {
        List<Vet> vets = vetService.findAll();
        List<VetDTO> vetDTOs = vets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vetDTOs);
    }

    // Получить ветеринара  по ID
    @GetMapping("/{id}")
    public ResponseEntity<VetDTO> getVetById(@PathVariable Long id) {
        Optional<Vet> vet = vetService.findById(id);
        if (vet.isPresent()) {
            return ResponseEntity.ok(convertToDTO(vet.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Поиск
    @GetMapping("/search/spec/{spec}")
    public ResponseEntity<List<VetDTO>> searchVetsBySpec(@RequestParam String specialization) {
        List<Vet> vets = vetService.findBySpecialization(specialization);
        List<VetDTO> vetDTOs = vets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vetDTOs);
    }
    @GetMapping("/search/email/{email}")
    public ResponseEntity<List<VetDTO>> searchVetsByEamil(@RequestParam String email) {
        Optional<Vet> vets = vetService.findByEmail(email);
        List<VetDTO> vetDTOs = vets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vetDTOs);
    }

    // Конвертация Entity в DTO
    private VetDTO convertToDTO(Vet vet) {
        VetDTO dto = new VetDTO();
        dto.setId(vet.getId());
        dto.setEmail(vet.getEmail());
        dto.setFirstName(vet.getFirstName());
        dto.setLastName(vet.getLastName());
        dto.setSpecialization(vet.getSpecialization());
        return dto;
    }
}