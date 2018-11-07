package com.andrei.petclinic.bootstrap;

import com.andrei.petclinic.model.Owner;
import com.andrei.petclinic.model.Vet;
import com.andrei.petclinic.service.OwnerService;
import com.andrei.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {

        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("John");
        owner.setSurname("Doe");

        ownerService.save(owner);

        owner = new Owner();
        owner.setId(2L);
        owner.setFirstName("Michael");
        owner.setSurname("Jackson");

        ownerService.save(owner);

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
