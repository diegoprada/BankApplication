package com.devsu.hackerearth.backend.account.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.hackerearth.backend.account.exceptions.BadRequestExceptions;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.service.TransactionService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	public ResponseEntity<List<TransactionDto>> getAll() {
		List<TransactionDto> transactions = transactionService.getAll();
		return ResponseEntity.ok(transactions);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> get(@PathVariable Long id) throws BadRequestExceptions {
		TransactionDto transaction = transactionService.getById(id);
		return ResponseEntity.ok(transaction);
	}

	@PostMapping
	public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto transactionDto) {
		TransactionDto createdTransaction = transactionService.create(transactionDto);
		return ResponseEntity.ok(createdTransaction);
	}

	@GetMapping("/client/{clientId}")
	public ResponseEntity<List<BankStatementDto>> getStatementsByClientAndDateRange(@PathVariable Long clientId,
			@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) {
		List<BankStatementDto> statements = transactionService.getAllByAccountClientIdAndDateBetween(clientId,
				startDate, endDate);
		return ResponseEntity.ok(statements);
	}

	@GetMapping("/account/{accountId}/last")
	public ResponseEntity<TransactionDto> getLastTransactionByAccount(@PathVariable Long accountId) throws BadRequestExceptions {
		TransactionDto lastTransaction = transactionService.getLastByAccountId(accountId);
		return ResponseEntity.ok(lastTransaction);
	}
}