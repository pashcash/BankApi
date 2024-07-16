package com.example.BankApi.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BankApi.exceptions.types.BadRequestException;
import com.example.BankApi.exceptions.types.EntityNotFoundException;
import com.example.BankApi.models.Deposit;
import com.example.BankApi.repos.DepositRepository;

@Service
public class DepositService {

    private DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public ResponseEntity<Deposit> updateDeposit(Long depositId, Deposit depositDetails)
    {
        Optional<Deposit> depositData = depositRepository.findById(depositId);
        if (!depositData.isPresent())
            throw new EntityNotFoundException("Deposit with id = " + depositId + " doesn`t exist");
        Deposit deposit = depositData.get();
        deposit.setClientId(depositDetails.getClientId());
        deposit.setBankId(depositDetails.getBankId());
        deposit.setOpenDate(depositDetails.getOpenDate());
        deposit.setPercentage(depositDetails.getPercentage());
        deposit.setTerm(depositDetails.getTerm());
        depositRepository.save(deposit);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    public Deposit addDeposit(Deposit deposit)
    {
        return depositRepository.save(deposit);
    }

    public ResponseEntity<Deposit> deleteDeposit(Long depositId)
    {
        Optional<Deposit> depositData = depositRepository.findById(depositId);
        if (!depositData.isPresent())
            throw new EntityNotFoundException("Deposit with id = " + depositId + " doesn`t exist");
        depositRepository.deleteById(depositId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Deposit>> getAllDeposits(String sortColumn)
    {
        if (sortColumn == null)
            return new ResponseEntity<>(depositRepository.findAll(), HttpStatus.OK);
        List<String> columns = Arrays.asList("id", "clientId", "bankId", "openDate", "percentage", "term");
        if (!columns.contains(sortColumn))
            throw new BadRequestException("Sorting param is incorrect");
        return new ResponseEntity<>(depositRepository.findAll(Sort.by(sortColumn)), HttpStatus.OK);
    }

    public Deposit getDepostById(Long depositId) {
        Optional<Deposit> depositData = depositRepository.findById(depositId);
        if (!depositData.isPresent())
            throw new EntityNotFoundException("Deposit with id = " + depositId + " doesn`t exist");
        return depositData.get();
    }
}