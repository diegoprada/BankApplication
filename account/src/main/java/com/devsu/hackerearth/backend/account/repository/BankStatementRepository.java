package com.devsu.hackerearth.backend.account.repository;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;

@Repository
public interface BankStatementRepository extends JpaRepository<BankStatementDto, Long> {
    List<BankStatementDto> findAllByAccountClientIdAndDateBetween(Long clientId, Date startDate, Date endDate);

}