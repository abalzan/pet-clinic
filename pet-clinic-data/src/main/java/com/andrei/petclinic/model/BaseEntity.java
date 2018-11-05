package com.andrei.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class BaseEntity implements Serializable {

    private Long id;

    private LocalDate createDate;

    private LocalDate updateDate;

}
