package com.example.BankApi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Array;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BankApi.model.Bank;
import com.example.BankApi.repos.BankRepository;

@Service    
public class BankService {
    BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    
    public ResponseEntity<Bank> updateBank(Long bankId, Bank bankDetails) {
        Optional<Bank> bankData = bankRepository.findById(bankId);
        if (bankData.isPresent()) {
			Bank bank = bankData.get();
            bank.setName(bankDetails.getName());
            bank.setBic(bankDetails.getBic());
            bankRepository.save(bank);
            return new ResponseEntity<>(bank, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Bank createBank(Bank bank)
    {
        return bankRepository.save(bank);
    }

    public ResponseEntity<Bank> deleteBank(Long bankId)
    {
        Optional<Bank> bankData = bankRepository.findById(bankId);
        if (bankData.isPresent()) {
            bankRepository.deleteById(bankId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Bank>> getAllBanks(String sortColumn)
    {
        if (sortColumn == null)
        {
            return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);
        }
        List<String> columns = Arrays.asList("id", "name", "bic"); 
        if (columns.contains(sortColumn))
        {
            return new ResponseEntity<>(bankRepository.findAll(Sort.by(sortColumn)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}