package com.devsu.hackerearth.backend.account.service;

import java.util.Collection;
import java.util.List;

import com.devsu.hackerearth.backend.account.model.Cliente;
import com.devsu.hackerearth.backend.account.model.dto.ClienteDto;
import com.devsu.hackerearth.backend.account.model.dto.ClienteResponseDto;
import com.devsu.hackerearth.backend.account.repository.ClienteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.devsu.hackerearth.backend.account.Utils.ClienteMapperUtil;
import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;
import org.springframework.dao.DataAccessException;

import com.devsu.hackerearth.enums.EnumError.*;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRespository clienteRepository;

    @Autowired
    private ClienteMapperUtil userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";

    @Override
    public List<ClienteResponseDto> getAll() throws BadRequestExceptions {
        try {
            List<Cliente> users = clienteRepository.findAll();
            log.info("finish Consume service {findAll};1;CON", "OUT");
            return users.stream().map(userMapper::toResponseDTO).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Database error in findAll: {}", ex.getMessage());

            throw new BadRequestExceptions("1007", "Error BD " + ex.getMessage(), "TRANSACCION RECHAZADA ", "ERROR",
                    500);
        }
    }

    @Override
    public ClienteResponseDto getById(Long id) throws BadRequestExceptions {
        log.info("Init Consume service {getById};1;CON", "IN");
        try {
            Cliente user = clienteRepository.findById(id)

                    .orElseThrow(() -> new BadRequestExceptions("1005", "Usuario no encontrado",
                            "TRANSACCION RECHAZADA ", "ERROR", 400));

            log.info("finish Consume service {getById};1;CON", "OUT");
            return userMapper.toResponseDTO(user);
        } catch (DataAccessException ex) {
            log.error("Database error in getById: {}", ex.getMessage());
            throw new BadRequestExceptions("1007", "Error BD " + ex.getMessage(),
                    "TRANSACCION RECHAZADA ", "ERROR", 500);
        }
    }

    @Override
    public ClienteResponseDto create(ClienteDto clienteDto) throws BadRequestExceptions {
        log.info("Init Consume service {create};1;MAN", "IN");

        try {
            Cliente newUser = new Cliente();
            newUser.setDni(clienteDto.getDni());
            newUser.setSexo(clienteDto.getSexo());
            newUser.setActiva(true);
            newUser.setNombre(clienteDto.getNombre());
            newUser.setDireccion(clienteDto.getDireccion());
            newUser.setEdad(clienteDto.getEdad());
            newUser.setTelefono(clienteDto.getTelefono());
            newUser.setContrasena(passwordEncoder.encode(clienteDto.getPassword()));

            Cliente savedUser = clienteRepository.save(newUser);
            log.info("finish Consume service {create};1;MAN", "OUT");
            return userMapper.toResponseDTO(savedUser);

        } catch (DataAccessException ex) {
            log.error("Database error in create: {}", ex.getMessage());
            throw new BadRequestExceptions("1007", "Error BD " + ex.getMessage(),
                    "TRANSACCION RECHAZADA ", "ERROR", 500);
        }
    }

    @Override
    public ClienteResponseDto update(ClienteDto clienteDto, Long id) throws BadRequestExceptions {
        log.info("Init Consume service {update};1;MAN", "IN");
        try {
            Cliente existingUser = clienteRepository.findById(id)
                    .orElseThrow(() -> new BadRequestExceptions("1005", "usuario no encontrado",
                            "TRANSACCION RECHAZADA ", "ERROR", 400));

            if (clienteDto.getPassword() != null && !clienteDto.getPassword().isEmpty()) {
                if (!clienteDto.getPassword().matches(passwordPattern)) {
                    throw new BadRequestExceptions("1004",
                            "Esta clave debe contener al menos un número (12345), al menos una letra (abc), y una longitud de 8 caracteres",
                            "TRANSACCION RECHAZADA ", "ERROR", 400);
                }
                existingUser.setContrasena(passwordEncoder.encode(clienteDto.getPassword()));
            }

            existingUser.setNombre(clienteDto.getNombre());
            existingUser.setDni(clienteDto.getDni());
            existingUser.setEdad(clienteDto.getEdad());
            log.info("finish Consume service {update};1;MAN", "OUT");
            return userMapper.toResponseDTO(clienteRepository.save(existingUser));
        } catch (DataAccessException ex) {
            log.error("Database error in update: {}", ex.getMessage());
            throw new BadRequestExceptions("1007", "Error BD " + ex.getMessage(),
                    "TRANSACCION RECHAZADA ", "ERROR", 500);
        }
    }

    @Override
    public boolean deleteById(Long id) throws BadRequestExceptions {
        log.info("Init Consume service {delete};1;MAN", "IN");
        try {
            Cliente user = clienteRepository.findById(id)
                    .orElseThrow(() -> new BadRequestExceptions("1005", "Usuario no encontrado",
                            "TRANSACCION RECHAZADA ", "ERROR", 400));
            log.info("finish Consume service {deleteById};1;MAN", "OUT");
            clienteRepository.delete(user);
            return true;
        } catch (DataAccessException ex) {
            log.error("Database error in deleteById: {}", ex.getMessage());
            throw new BadRequestExceptions("1007", "Error BD " + ex.getMessage(),
                    "TRANSACCION RECHAZADA ", "ERROR", 500);
        }

    }
}
