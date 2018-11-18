package com.andrei.petclinic.service;

import com.andrei.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findBySurname(String lastName);

    List<Owner> findAllBySurnameLike(String lastName);
}
