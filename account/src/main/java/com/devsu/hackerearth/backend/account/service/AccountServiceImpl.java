package com.devsu.hackerearth.backend.account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;

import org.modelmapper.ModelMapper;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream()
                .map(account -> modelMapper.map(account, AccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) throws BadRequestExceptions {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BadRequestExceptions("1005", "cuenta no encontrado",
                        "TRANSACCION RECHAZADA ", "ERROR", 400));
        return modelMapper.map(account, AccountDto.class);

    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountDto.class);
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        Account updatedAccount = accountRepository.save(account);
        return modelMapper.map(updatedAccount, AccountDto.class);
    }

    @Override
    public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto) throws BadRequestExceptions {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BadRequestExceptions("1005", "cuenta no encontrado",
                "TRANSACCION RECHAZADA ", "ERROR", 400));

        Account updatedAccount = accountRepository.save(account);
        return modelMapper.map(updatedAccount, AccountDto.class);
    }

        
    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

}


        