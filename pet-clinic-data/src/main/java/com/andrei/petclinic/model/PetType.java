package com.andrei.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "types")
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;
}
