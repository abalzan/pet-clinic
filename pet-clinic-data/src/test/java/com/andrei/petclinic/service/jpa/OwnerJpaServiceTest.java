package com.andrei.petclinic.service.jpa;

import com.andrei.petclinic.model.Owner;
import com.andrei.petclinic.repositories.OwnerRepository;
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
class OwnerJpaServiceTest {

    public static final String SURNAME = "surnameFake";
    @Mock
    OwnerRepository repository;

    @InjectMocks
    OwnerJpaService service;

    @Test
    void findBySurname() {
        Owner returnOwner = Owner.builder().surname(SURNAME).build();

        Mockito.when(repository.findBySurname(Mockito.any())).thenReturn(returnOwner);

        Owner owner = service.findBySurname(SURNAME);

        assertEquals(SURNAME, owner.getSurname());

        Mockito.verify(repository).findBySurname(Mockito.anyString());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(Owner.builder().firstName("John").build());
        returnOwners.add(Owner.builder().firstName("Joana").build());

        Mockito.when(repository.findAll()).thenReturn(returnOwners);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Owner returnOwner = Owner.builder().surname(SURNAME).build();
        returnOwner.setId(1L);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().firstName("Fake").build();
        Mockito.when(repository.save(Mockito.any())).thenReturn(ownerToSave);

        Owner owner = service.save(ownerToSave);

        assertNotNull(owner);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void delete() {
        Owner ownerToDelete = Owner.builder().firstName("Fake").build();

        repository.delete(ownerToDelete);
        Mockito.verify(repository).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        Owner returnOwner = Owner.builder().surname(SURNAME).build();
        returnOwner.setId(1L);

        service.deleteById(1L);
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }
}