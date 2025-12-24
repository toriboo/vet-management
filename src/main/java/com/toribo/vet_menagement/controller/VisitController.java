package com.toribo.vet_menagement.controller;


import com.toribo.vet_menagement.dto.CreateVisitDTO;
import com.toribo.vet_menagement.dto.VisitDTO;
import com.toribo.vet_menagement.entity.Animal;
import com.toribo.vet_menagement.entity.Client;
import com.toribo.vet_menagement.entity.Vet;
import com.toribo.vet_menagement.entity.Visit;
import com.toribo.vet_menagement.repository.AnimalRepository;
import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.repository.VetRepository;
import com.toribo.vet_menagement.repository.VisitRepository;
import com.toribo.vet_menagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.toribo.vet_menagement.service.VisitStatus.SCHEDULED;


@RestController
@RequestMapping("/api/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AnimalRepository animalRepository;


    // Получить cписок всех приемов
    @GetMapping
    public ResponseEntity<List<VisitDTO>> getAllVisits() {
        List<Visit> visits = visitService.findAll();
        List<VisitDTO> visitDTOs = visits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(visitDTOs);
    }

    // Получить visit по ID
    @GetMapping("/{id}")
    public ResponseEntity<VisitDTO> getVisitById(@PathVariable Long id) {
        Optional<Visit> visit = visitService.findById(id);
        if (visit.isPresent()) {
            return ResponseEntity.ok(convertToDTO(visit.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить прием по id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisits(@PathVariable Long id) {
        Optional<Visit> visit = visitService.findById(id);
        if (visit.isPresent()) {
            visitService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<VisitDTO> createVisit(@Valid @RequestBody CreateVisitDTO
                                                      createVisitDTO) {
        try {
            Visit visit = convertToEntity(createVisitDTO);
            Visit savedVisit = visitService.save(visit);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedVisit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Обновить книгу
    @PutMapping("/{id}")
    public ResponseEntity<VisitDTO> updateBook(@PathVariable Long id,
                                              @Valid @RequestBody CreateVisitDTO createVisitDTO)
    {
        Optional<Visit> existingVisit = visitService.findById(id);
        if (existingVisit.isPresent()) {
            try {
                Visit visit = convertToEntity(createVisitDTO);
                visit.setId(id);
                Visit updatedVisit = visitService.save(visit);
                return ResponseEntity.ok(convertToDTO(updatedVisit));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Поиск приема по вет пасспорту животного
    @GetMapping("/search/animal")
    public ResponseEntity<List<VisitDTO>> searchVisitByVetPassport(@RequestParam Long vetPassport) {
        List<Visit> visits = visitService.findByAnimalVetPassport(vetPassport);
        List<VisitDTO> visitDTOs = visits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(visitDTOs);
    }
    // поиск приема по id клиента
    @GetMapping("/search/client")
    public ResponseEntity<List<VisitDTO>> searchVisitsByClientId(@RequestParam Long clientId) {
        List<Visit> visits = visitService.findByClientId(clientId);
        List<VisitDTO> visitDTOs = visits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(visitDTOs);
    }

    @GetMapping("/search/vet")
    public ResponseEntity<List<VisitDTO>> searchVisitsByVetId(@RequestParam Long vetId) {
        List<Visit> visits = visitService.findByVetId(vetId);
        List<VisitDTO> visitDTOs = visits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(visitDTOs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VisitDTO>> searchVisitsByVisitDateTime(@RequestParam LocalDateTime visitDateTime) {
        List<Visit> visits = visitService.findByDateTime(visitDateTime);
        List<VisitDTO> visitDTOs = visits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(visitDTOs);
    }

    // Конвертация Entity в DTO
    private VisitDTO convertToDTO(Visit visit) {
        VisitDTO dto = new VisitDTO();
        dto.setId(visit.getId());
        dto.setVisitDate(visit.getVisitDate());
        Animal animal = visit.getAnimal();
        dto.setAnimalId(animal.getVetPassport());
        dto.setAnimalNickname(animal.getNickname());
        Client client  = visit.getClient();
        dto.setClientId(client.getId());
        dto.setClientName(client.getLastName()+ " " +client.getFirstName());
        dto.setStatus(visit.getStatus());
        Vet vet = visit.getVet();
        dto.setVetId(vet.getId());
        dto.setVetName(vet.getLastName()+ " " +vet.getFirstName());
        return dto;
    }

    // Конвертация DTO в Entity
    private Visit convertToEntity(CreateVisitDTO dto) {
        Visit visit = new Visit();
        System.out.println("AAA");
        visit.setVisitDate(LocalDateTime.of(dto.getVisitDate(), dto.getVisitTime()));
        System.out.println(visit.getVisitDate());
        visit.setCreatedAt(LocalDateTime.now());
        System.out.println(visit.getCreatedAt());
        Optional<Animal> animal = animalRepository.findById(dto.getVetPassport());
        if (animal.isPresent()) {
            visit.setAnimal(animal.get());
            System.out.println(visit.getAnimal().getAge());

            Optional<Client> client = clientRepository.findByAnimalVetPassport(dto.getVetPassport());
            System.out.println(client);
            if (client.isPresent()) {
                visit.setClient(client.get());
                System.out.println(visit.getClient().getFirstName());
            }

            Optional<Vet> vet = vetRepository.findById(dto.getVetId());
            if (vet.isPresent()) {
                visit.setVet(vet.get());
                System.out.println(visit.getVet().getFirstName());
            }
            visit.setStatus(SCHEDULED);

        }
        return visit;
    }
}