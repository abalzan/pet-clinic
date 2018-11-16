package com.andrei.petclinic.service.jpa;

import com.andrei.petclinic.model.Vet;
import com.andrei.petclinic.repositories.VetRepository;
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
class VetJpaServiceTest {


    @Mock
    VetRepository repository;

    @InjectMocks
    VetJpaService service;

    @Test
    void findAll() {
        Set<Vet> returnSet = new HashSet<>();
        returnSet.add(Vet.builder().firstName("John").build());
        returnSet.add(Vet.builder().firstName("Johana").build());

        Mockito.when(repository.findAll()).thenReturn(returnSet);

        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
    }

    @Test
    void findById() {
        Vet returnVet = Vet.builder().firstName("John").build();
        returnVet.setId(1L);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnVet));

        Vet vet = service.findById(1L);

        assertNotNull(vet);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Vet vet = service.findById(1L);
        assertNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().firstName("John").build();
        Mockito.when(repository.save(Mockito.any())).thenReturn(vetToSave);

        Vet vet = service.save(vetToSave);

        assertNotNull(vet);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void delete() {
        Vet vetToDelete = Vet.builder().firstName("John").build();

        repository.delete(vetToDelete);
        Mockito.verify(repository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        Vet returnVet = Vet.builder().firstName("John").build();
        returnVet.setId(1L);

        service.deleteById(1L);
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }
}