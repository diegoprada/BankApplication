package com.devsu.hackerearth.backend.account.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "dni"))
public class Cliente extends Persona {
    private String contrasena;
    private boolean activa;
}
