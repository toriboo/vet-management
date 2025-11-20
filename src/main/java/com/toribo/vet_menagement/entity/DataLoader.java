package com.toribo.vet_menagement.entity;

import com.toribo.vet_menagement.repository.AnimalRepository;
import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.repository.VetRepository;
import com.toribo.vet_menagement.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.toribo.vet_menagement.service.AnimalGender.*;
import static com.toribo.vet_menagement.service.VisitStatus.*;

@Component
public class DataLoader implements CommandLineRunner {

        @Autowired
        private VisitRepository visitRepository;
        @Autowired
        private VetRepository vetRepository;
        @Autowired
        private ClientRepository clientRepository;
        @Autowired
        private AnimalRepository animalRepository;

        @Override
        public void run (String...args) throws Exception {
            Animal dog1 = new Animal(123123, "Jack", "dog", LocalDateTime.now());
            Animal dog2 = new Animal(123321, "Mikey", "dog", LocalDateTime.now());
            dog2.setBreed("labrador");
            dog2.setAge(8);
            dog2.setGender(MALE);
            dog1.setGender(FEMALE);
            dog1.setAge(1);

            Animal cat1 = new Animal(111222, "Murka", "cat", LocalDateTime.now());
            cat1.setGender(FEMALE);


            Vet vet1 = new Vet("IIIvanov@mail.ru", "vet1password", "Иван", "Иванов", LocalDateTime.now(), "therapist");
            Vet vet2 = new Vet("AVGrushev@gmail.com", "vet2password", "Alexander", "Grushev", LocalDateTime.now(), "radiographer");

            Client client1 = new Client("MSSergeev@mail.ru", "client1password", "Michail", "Sergeev", LocalDateTime.now());
            Client client2 = new Client("AAAlexeeva@gmail.com", "client2password", "Anna", "Alexeeva", LocalDateTime.now());
            List<Animal> dogs = List.of();
            dogs.add(dog1);
            dogs.add(dog2);

            client1.setAnimals(dogs);

            cat1.setClient(client2);

            Visit visit1 = new Visit(LocalDateTime.parse("2025-12-14T10:15:00"), LocalDateTime.now(), SCHEDULED, client1, vet2, dog1);

            Visit visit2 = new Visit(LocalDateTime.parse("2025-10-10T12:30:30"), LocalDateTime.now(), COMPLETED, client2, vet1, cat1);
            System.out.println("Тестовые данные загружены успешно!");

        }


    }
