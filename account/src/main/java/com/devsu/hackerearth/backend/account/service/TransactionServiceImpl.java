package com.devsu.hackerearth.backend.account.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;
import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.repository.BankStatementRepository;
import com.devsu.hackerearth.backend.account.repository.TransactionRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    private BankStatementRepository bankStatementRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDto> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto getById(Long id) throws BadRequestExceptions {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(t -> modelMapper.map(t, TransactionDto.class))
                .orElseThrow(() -> new BadRequestExceptions("1005", "transacción no encontrado",
                        "TRANSACCION RECHAZADA ", "ERROR", 400));

    }

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return modelMapper.map(savedTransaction, TransactionDto.class);
    }

    @Override
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
            Date dateTransactionEnd) {
        List<BankStatementDto> bankStatements = bankStatementRepository.findAllByAccountClientIdAndDateBetween(clientId,
                dateTransactionStart, dateTransactionEnd);
                return bankStatements.stream()
                .map(bankStatement -> modelMapper.map(bankStatement, BankStatementDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto getLastByAccountId(Long accountId) throws BadRequestExceptions {
        Optional<Transaction> transaction = transactionRepository.findTopByAccountIdOrderByDateDesc(accountId);
        return transaction.map(t -> modelMapper.map(t, TransactionDto.class))
                .orElseThrow(() -> new BadRequestExceptions("1005", "transacciónes no encontradas para esta cuenta",
                        "TRANSACCION RECHAZADA ", "ERROR", 400));

    }

}
