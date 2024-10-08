package com.devsu.hackerearth.backend.account.service;

import java.util.List;

import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;
import com.devsu.hackerearth.backend.account.model.dto.ClienteDto;
import com.devsu.hackerearth.backend.account.model.dto.ClienteResponseDto;

public interface ClienteService {

    public List<ClienteResponseDto> getAll() throws BadRequestExceptions;

    public ClienteResponseDto getById(Long id) throws BadRequestExceptions;

    public ClienteResponseDto create(ClienteDto clienteDto) throws BadRequestExceptions;

    public ClienteResponseDto update(ClienteDto clienteDto,Long id) throws BadRequestExceptions;

    public boolean deleteById(Long id) throws BadRequestExceptions;

}
