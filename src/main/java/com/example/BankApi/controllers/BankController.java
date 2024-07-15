package com.example.BankApi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankApi.models.Bank;
import com.example.BankApi.services.BankService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/banks")
@AllArgsConstructor
public class BankController {

    private BankService bankService;

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBankById(@PathVariable Long id, @RequestBody Bank bankDetails)
    {
        return bankService.updateBank(id, bankDetails);
    }

    @PostMapping
    public Bank addNewBank(@RequestBody Bank bankDetails)
    {
        return bankService.addBank(bankDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bank> deleteBankById(@PathVariable Long id)
    {
        return bankService.deleteBank(id);
    }

    @GetMapping()
    public ResponseEntity<List<Bank>> getBanks(@RequestParam(required = false, name="sort-by") String sortColumn)
    {
        return bankService.getAllBanks(sortColumn);
    }
}