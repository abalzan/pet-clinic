package com.andrei.petclinic.service.jpa;

import com.andrei.petclinic.model.Pet;
import com.andrei.petclinic.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetJpaServiceTest {

    @Mock
    PetRepository repository;

    @InjectMocks
    PetJpaService service;

    @Test
    void findAll() {
        Set<Pet> returnSet = new HashSet<>();
        returnSet.add(Pet.builder().name("Charlie").build());
        returnSet.add(Pet.builder().name("Moly").build());

        Mockito.when(repository.findAll()).thenReturn(returnSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    void findById() {
        Pet returnPet = Pet.builder().name("Charlie").build();
        returnPet.setId(1L);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(1L);

        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Pet pet = service.findById(1L);
        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().name("Fake").build();
        Mockito.when(repository.save(Mockito.any())).thenReturn(petToSave);

        Pet pet = service.save(petToSave);

        assertNotNull(pet);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void delete() {
        Pet petToDelete = Pet.builder().name("Fake").build();

        repository.delete(petToDelete);
        Mockito.verify(repository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        Pet returnPet = Pet.builder().name("Moly").build();
        returnPet.setId(1L);

        service.deleteById(1L);
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }
}