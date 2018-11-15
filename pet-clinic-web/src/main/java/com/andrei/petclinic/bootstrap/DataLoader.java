package com.andrei.petclinic.bootstrap;

import com.andrei.petclinic.model.*;
import com.andrei.petclinic.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        specialityService.save(dentistry);

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("John");
        owner.setSurname("Doe");
        owner.setAddress("123 Kingsroad");
        owner.setCity("London");
        owner.setTelephone("123456789");

        Pet johnCat = new Pet();
        johnCat.setPetType(savedCatPetType);
        johnCat.setOwner(owner);
        johnCat.setBirthDate(LocalDate.now());
        johnCat.setName("Moly");

        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Michael");
        owner2.setSurname("Jackson");
        owner2.setAddress("123 Brady Street");
        owner2.setCity("London");

        owner2.setTelephone("987654321");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner2);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Charlie");

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(johnCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneeze Kity");
        visitService.save(catVisit);

        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("Sam");
        vet.setSurname("Axe");
        vet.getSpecialities().add(radiology);
        vetService.save(vet);

        vet = new Vet();
        vet.setId(2L);
        vet.setFirstName("Tito");
        vet.setSurname("Smith");
        vet.getSpecialities().add(radiology);
        vetService.save(vet);
    }
}
