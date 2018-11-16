package com.andrei.petclinic.service.jpa;

import com.andrei.petclinic.model.Visit;
import com.andrei.petclinic.repositories.VisitRepository;
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
class VisitJpaServiceTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitJpaService service;

    @Test
    void findAll() {
        Set<Visit> returnSet = new HashSet<>();
        returnSet.add(Visit.builder().description("Surgery").build());
        returnSet.add(Visit.builder().description("Radiology").build());

        Mockito.when(repository.findAll()).thenReturn(returnSet);

        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void findById() {
        Visit returnVisit = Visit.builder().description("Surgery").build();
        returnVisit.setId(1L);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnVisit));

        Visit visit = service.findById(1L);

        assertNotNull(visit);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Visit visit = service.findById(1L);
        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().description("Surgery").build();
        Mockito.when(repository.save(Mockito.any())).thenReturn(visitToSave);

        Visit visit = service.save(visitToSave);

        assertNotNull(visit);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void delete() {
        Visit visitToDelete = Visit.builder().description("Surgery").build();

        repository.delete(visitToDelete);
        Mockito.verify(repository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        Visit returnVisit = Visit.builder().description("Surgery").build();
        returnVisit.setId(1L);

        service.deleteById(1L);
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }
}