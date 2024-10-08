package com.devsu.hackerearth.backend.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private String nombre;
    private String dni;
    private String sexo;
    private int edad;
    private String direccion;
    private String telefono;
    private String password;

}
