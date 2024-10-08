package com.devsu.hackerearth.backend.account;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;
import com.devsu.hackerearth.backend.account.model.Cliente;
import com.devsu.hackerearth.backend.account.model.dto.ClienteDto;
import com.devsu.hackerearth.backend.account.model.dto.ClienteResponseDto;
import com.devsu.hackerearth.backend.account.repository.ClienteRespository;
import com.devsu.hackerearth.backend.account.service.ClienteServiceImpl;
import com.devsu.hackerearth.backend.account.Utils.ClienteMapperUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class sampleTest {

	@Mock
    private ClienteRespository clienteRepository;

    @Mock
    private ClienteMapperUtil userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Iniciar los mocks
    }

    @Test
    void getAll_ShouldReturnListOfClientes() throws BadRequestExceptions {
        // Mockear respuesta del repositorio
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        // Mockear mapeo de DTO
        ClienteResponseDto dto1 = new ClienteResponseDto();
        ClienteResponseDto dto2 = new ClienteResponseDto();
        when(userMapper.toResponseDTO(cliente1)).thenReturn(dto1);
        when(userMapper.toResponseDTO(cliente2)).thenReturn(dto2);

        // Llamar al método
        List<ClienteResponseDto> result = clienteService.getAll();

        // Verificar el comportamiento y las aserciones
        assertEquals(2, result.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void getById_ShouldReturnCliente_WhenIdIsFound() throws BadRequestExceptions {
        // Mockear respuesta del repositorio
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Mockear mapeo de DTO
        ClienteResponseDto dto = new ClienteResponseDto();
        when(userMapper.toResponseDTO(cliente)).thenReturn(dto);

        // Llamar al método
        ClienteResponseDto result = clienteService.getById(1L);

        // Verificar el comportamiento y las aserciones
        assertNotNull(result);
        verify(clienteRepository).findById(1L);
    }

    @Test
    void create_ShouldSaveCliente() throws BadRequestExceptions {
        // Mockear datos de entrada y de respuesta
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setPassword("password123");
        
        Cliente cliente = new Cliente();
        when(passwordEncoder.encode(clienteDto.getPassword())).thenReturn("encodedPassword");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDto dto = new ClienteResponseDto();
        when(userMapper.toResponseDTO(cliente)).thenReturn(dto);

        // Llamar al método
        ClienteResponseDto result = clienteService.create(clienteDto);

        // Verificar el comportamiento y las aserciones
        assertNotNull(result);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void update_ShouldUpdateCliente_WhenIdIsFound() throws BadRequestExceptions {
        // Mockear datos de entrada y de respuesta
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setPassword("newPassword123");

        Cliente existingCliente = new Cliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(existingCliente));
        when(passwordEncoder.encode(clienteDto.getPassword())).thenReturn("encodedNewPassword");

        ClienteResponseDto dto = new ClienteResponseDto();
        when(userMapper.toResponseDTO(any(Cliente.class))).thenReturn(dto);

        // Llamar al método
        ClienteResponseDto result = clienteService.update(clienteDto, 1L);

        // Verificar el comportamiento y las aserciones
        assertNotNull(result);
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void deleteById_ShouldDeleteCliente_WhenIdIsFound() throws BadRequestExceptions {
        // Mockear respuesta del repositorio
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Llamar al método
        boolean result = clienteService.deleteById(1L);

        // Verificar el comportamiento
        assertTrue(result);
        verify(clienteRepository).delete(cliente);
    }


}

