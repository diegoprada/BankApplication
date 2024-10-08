package com.devsu.hackerearth.backend.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.hackerearth.backend.account.model.Cliente;

@Repository
public interface ClienteRespository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDni(String dni);
}
