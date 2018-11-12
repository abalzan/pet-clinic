package com.andrei.petclinic.bootstrap;

import com.andrei.petclinic.model.Owner;
import com.andrei.petclinic.model.Pet;
import com.andrei.petclinic.model.PetType;
import com.andrei.petclinic.model.Vet;
import com.andrei.petclinic.service.OwnerService;
import com.andrei.petclinic.service.PetTypeService;
import com.andrei.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {

        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);


        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);


        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("John");
        owner.setSurname("Doe");
        owner.setAddress("123 Kingsroad");
        owner.setCity("London");
        owner.setTelephone("123456789");
        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Michael");
        owner2.setSurname("Jackson");
        owner2.setAddress("123 Brady Street");
        owner2.setCity("London");
        owner2.setTelephone("987654321");
        ownerService.save(owner2);

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner2);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Charlie");

        Pet johnCat = new Pet();
        johnCat.setPetType(savedCatPetType);
        johnCat.setOwner(owner);
        johnCat.setBirthDate(LocalDate.now());
        johnCat.setName("Moly");

        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("Sam");
        vet.setSurname("Axe");

        vetService.save(vet);

        vet = new Vet();
        vet.setId(2L);
        vet.setFirstName("Tito");
        vet.setSurname("Smith");

        vetService.save(vet);
    }
}
