package com.example.BankApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankApi.service.BankService;
import com.example.BankApi.model.Bank;
import com.example.BankApi.repos.BankRepository;

@RestController
@RequestMapping("/banks")
public class BankController {
    BankService bankService;

    public BankController(BankService bankService)
    {
        this.bankService = bankService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBankById(@PathVariable Long id, @RequestBody Bank bankDetails)
    {
        return bankService.updateBank(id, bankDetails);
    }

    @PostMapping
    public Bank createNewBank(@RequestBody Bank bankDetails)
    {
        return bankService.createBank(bankDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bank> deleteBankById (@PathVariable Long id)
    {
        return bankService.deleteBank(id);
    }

    @GetMapping()
    public List<Bank> getBanks()
    {
        return bankService.getAllBanks();
    }
}
