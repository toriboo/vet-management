package com.toribo.vet_menagement.entity;

import com.toribo.vet_menagement.repository.AnimalRepository;
import com.toribo.vet_menagement.repository.ClientRepository;
import com.toribo.vet_menagement.repository.VetRepository;
import com.toribo.vet_menagement.repository.VisitRepository;
import com.toribo.vet_menagement.service.AnimalGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (isDatabaseEmpty()) {

            loadTestData();

            System.out.println("Тестовые данные загружены успешно!");
        }
    }

    private boolean isDatabaseEmpty() {
        return visitRepository.count() == 0 &&
                animalRepository.count() == 0 &&
                clientRepository.count() == 0 &&
                vetRepository.count()==0;
    }

    private void loadTestData() {

        visitRepository.deleteAll();
        clientRepository.deleteAll();
        animalRepository.deleteAll();
        vetRepository.deleteAll();
        System.out.println("Тестовые данные загружены успешно!");
        // Создаем ветеринаров
        Vet vet1 = new Vet("IIIvanov@mail.ru", "vet1password", "Иван", "Иванов", LocalDateTime.now(), "therapist");
        Vet vet2 = new Vet("AVGrushev@gmail.com", "vet2password", "Alexander", "Grushev", LocalDateTime.now(), "radiographer");

        // Сохраняем ветеринаров
        vetRepository.save(vet1);
        vetRepository.save(vet2);
        System.out.println("Тестовые данные загружены успешно!");
        // Создаем клиентов
        Client client1 = new Client("MSSergeev@mail.ru", "client1password", "Michail", "Sergeev", LocalDateTime.now());
        Client client2 = new Client("AAAlexeeva@gmail.com", "client2password", "Anna", "Alexeeva", LocalDateTime.now());

        // Сохраняем клиентов
        clientRepository.save(client1);
        clientRepository.save(client2);

        // Создаем животных с уже установленными клиентами
        Animal dog1 = new Animal(123123L,
                "Buddy",
                "Dog",
                "Golden Retriever",
                5,
                MALE,
                "Friendly dog",
                LocalDateTime.now(),
                client1);

        Animal dog2 = new Animal(222222L,
                "Mikey",
                "Dog",
                "Labrador",
                8,
                FEMALE,
                "Fun dog",
                LocalDateTime.now(),
                client1);

        Animal cat1 = new Animal(444444L,
                "Murka",
                "Cat",
                "Domestic shorthair",
                3,
                FEMALE,
                "Calm cat",
                LocalDateTime.now(),
                client2);

        // Сохраняем животных
        animalRepository.save(dog1);
        animalRepository.save(dog2);
        animalRepository.save(cat1);

        // Обновляем список животных у клиентов
        List<Animal> client1Animals = new ArrayList<>();
        client1Animals.add(dog1);
        client1Animals.add(dog2);
        client1.setAnimals(client1Animals);

        List<Animal> client2Animals = new ArrayList<>();
        client2Animals.add(cat1);
        client2.setAnimals(client2Animals);

        // Сохраняем обновленных клиентов
        clientRepository.save(client1);
        clientRepository.save(client2);

        // Создаем визиты
        Visit visit1 = new Visit(LocalDateTime.of(2025,12,25,12,30), LocalDateTime.now(), SCHEDULED, clientRepository.findById(18L).get(), vetRepository.findById(27L).get(), animalRepository.findByClientId(18L).get(0));
        Visit visit2 = new Visit(LocalDateTime.of(2025,3,15,13,45), LocalDateTime.now(), COMPLETED,clientRepository.findById(19L).get(),  vetRepository.findById(28L).get(), animalRepository.findByNickname("Murka").get(0));

        // Сохраняем визиты
        visitRepository.save(visit1);
        visitRepository.save(visit2);
    }
}
