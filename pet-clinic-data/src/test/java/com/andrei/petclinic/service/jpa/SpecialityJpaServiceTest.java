package com.andrei.petclinic.service.jpa;

import com.andrei.petclinic.model.Speciality;
import com.andrei.petclinic.repositories.SpecialityRepository;
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
class SpecialityJpaServiceTest {

    @Mock
    SpecialityRepository repository;

    @InjectMocks
    SpecialityJpaService service;

    @Test
    void findAll() {
        Set<Speciality> returnSet = new HashSet<>();
        returnSet.add(Speciality.builder().description("Surgery").build());
        returnSet.add(Speciality.builder().description("Radiology").build());

        Mockito.when(repository.findAll()).thenReturn(returnSet);

        Set<Speciality> specialities = service.findAll();

        assertNotNull(specialities);
        assertEquals(2, specialities.size());
    }

    @Test
    void findById() {
        Speciality returnSpeciality = Speciality.builder().description("Surgery").build();
        returnSpeciality.setId(1L);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnSpeciality));

        Speciality speciality = service.findById(1L);

        assertNotNull(speciality);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Speciality speciality = service.findById(1L);
        assertNull(speciality);
    }

    @Test
    void save() {
        Speciality specialityToSave = Speciality.builder().description("Surgery").build();
        Mockito.when(repository.save(Mockito.any())).thenReturn(specialityToSave);

        Speciality speciality = service.save(specialityToSave);

        assertNotNull(speciality);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void delete() {
        Speciality specialityToDelete = Speciality.builder().description("Surgery").build();

        repository.delete(specialityToDelete);
        Mockito.verify(repository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        Speciality returnSpeciality = Speciality.builder().description("Surgery").build();
        returnSpeciality.setId(1L);

        service.deleteById(1L);
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }
}