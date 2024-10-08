package com.devsu.hackerearth.backend.account.Utils;

import org.springframework.stereotype.Component;

import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.Cliente;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.ClienteResponseDto;

@Component
public class ClienteMapperUtil {
    public ClienteResponseDto toResponseDTO(Cliente user) {
        ClienteResponseDto responseDTO = new ClienteResponseDto();
        responseDTO.setNombre(user.getNombre());
        responseDTO.setDni(user.getDni());
        responseDTO.setTelefono(user.getTelefono());
        return responseDTO;
    }

    public AccountDto toResponseDTOAccount(Account account) {
        AccountDto responseDTO = new AccountDto();
        responseDTO.setType(account.getType());
        responseDTO.setNumber(account.getNumber());
        responseDTO.setClientId(account.getClientId());
        return responseDTO;
    }
}
