package com.andrei.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "specialities")
public class Speciality extends BaseEntity {

    @Column(name = "description")
    private String description;
}
