package com.andrei.petclinic.service.jpa;

import com.andrei.petclinic.model.Owner;
import com.andrei.petclinic.repositories.OwnerRepository;
import com.andrei.petclinic.repositories.PetRepository;
import com.andrei.petclinic.repositories.PetTypeRepository;
import com.andrei.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Profile({"default"})
public class OwnerJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public Owner findBySurname(String lastName) {
        return ownerRepository.findBySurname(lastName);
    }

    @Override
    public List<Owner> findAllBySurnameLike(String lastName) {
        return ownerRepository.findAllBySurnameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
