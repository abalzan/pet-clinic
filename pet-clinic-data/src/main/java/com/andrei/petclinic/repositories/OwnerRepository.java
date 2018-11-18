package com.andrei.petclinic.repositories;

import com.andrei.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findBySurname(String lastName);

    List<Owner> findAllBySurnameLike(String lastName);
}
