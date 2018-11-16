package com.andrei.petclinic.service.jpa;

import com.andrei.petclinic.model.PetType;
import com.andrei.petclinic.repositories.PetTypeRepository;
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
class PetTypeJpaServiceTest {
    @Mock
    PetTypeRepository repository;

    @InjectMocks
    PetTypeJpaService service;

    @Test
    void findAll() {
        Set<PetType> returnSet = new HashSet<>();
        returnSet.add(PetType.builder().name("Dog").build());
        returnSet.add(PetType.builder().name("Cat").build());

        Mockito.when(repository.findAll()).thenReturn(returnSet);

        Set<PetType> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    void findById() {
        PetType returnPetType = PetType.builder().name("Cat").build();
        returnPetType.setId(1L);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnPetType));

        PetType pet = service.findById(1L);

        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        PetType pet = service.findById(1L);
        assertNull(pet);
    }

    @Test
    void save() {
        PetType petToSave = PetType.builder().name("Dog").build();
        Mockito.when(repository.save(Mockito.any())).thenReturn(petToSave);

        PetType pet = service.save(petToSave);

        assertNotNull(pet);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void delete() {
        PetType petToDelete = PetType.builder().name("Dog").build();

        repository.delete(petToDelete);
        Mockito.verify(repository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        PetType returnPetType = PetType.builder().name("Cat").build();
        returnPetType.setId(1L);

        service.deleteById(1L);
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }
}