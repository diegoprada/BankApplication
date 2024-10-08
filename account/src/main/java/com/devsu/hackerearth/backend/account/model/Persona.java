package com.devsu.hackerearth.backend.account.model;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Persona extends Base {
    private String nombre;
    private String dni;
    private String sexo;
    private int edad;
    private String direccion;
    private String telefono;
}
