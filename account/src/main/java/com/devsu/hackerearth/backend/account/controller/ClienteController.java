package com.devsu.hackerearth.backend.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;
import com.devsu.hackerearth.backend.account.model.Cliente;
import com.devsu.hackerearth.backend.account.model.dto.ClienteDto;
import com.devsu.hackerearth.backend.account.model.dto.ClienteResponseDto;
import com.devsu.hackerearth.backend.account.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteResponseDto> create(@RequestBody ClienteDto cliente)
            throws BadRequestExceptions {
        ClienteResponseDto nuevoCliente = clienteService.create(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> getAll() throws BadRequestExceptions {
        List<ClienteResponseDto> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable Long id) throws BadRequestExceptions {
        ClienteResponseDto cliente = clienteService.getById(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> update(@PathVariable Long id, @RequestBody ClienteDto clienteActualizado)
            throws BadRequestExceptions {
        ClienteResponseDto cliente = clienteService.update(clienteActualizado, id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) throws BadRequestExceptions {
        boolean eliminado = clienteService.deleteById(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
