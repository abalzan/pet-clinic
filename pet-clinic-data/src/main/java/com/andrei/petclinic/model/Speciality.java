package com.andrei.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "specialities")
public class Speciality extends BaseEntity {

    @Column(name = "description")
    private String description;
}
