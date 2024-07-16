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
import com.example.BankApi.models.Bank;
import com.example.BankApi.repos.BankRepository;

@Service
public class BankService {

    private BankRepository bankRepository;
    
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public ResponseEntity<Bank> updateBank(Long bankId, Bank bankDetails) {
        Optional<Bank> bankData = bankRepository.findById(bankId);
        if (!bankData.isPresent())
            throw new EntityNotFoundException("Bank with id = " + bankId + " doesn`t exist");
        Bank bank = bankData.get();
        bank.setName(bankDetails.getName());
        bank.setBic(bankDetails.getBic());
        bankRepository.save(bank);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    public Bank addBank(Bank bank)
    {
        return bankRepository.save(bank);
    }

    public ResponseEntity<Bank> deleteBank(Long bankId)
    {
        Optional<Bank> bankData = bankRepository.findById(bankId);
        if (!bankData.isPresent())
            throw new EntityNotFoundException("Bank with id = " + bankId + " doesn`t exist");
        bankRepository.deleteById(bankId);
        return new ResponseEntity<>(HttpStatus.OK);    
    }

    public ResponseEntity<List<Bank>> getAllBanks(String sortColumn)
    {
        if (sortColumn == null)
            return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);
        List<String> columns = Arrays.asList("id", "name", "bic"); 
        if (!columns.contains(sortColumn))
            throw new BadRequestException("Sorting params incorrect");
        return new ResponseEntity<>(bankRepository.findAll(Sort.by(sortColumn)), HttpStatus.OK);
    }

    public Bank getBankById(Long bankId)
    {
        Optional<Bank> bankData = bankRepository.findById(bankId);
        if (!bankData.isPresent())
            throw new EntityNotFoundException("Bank with id = " + bankId + " doesn`t exist");
        return bankData.get();
    }
}