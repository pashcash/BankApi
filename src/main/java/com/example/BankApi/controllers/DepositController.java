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

import com.example.BankApi.models.Deposit;
import com.example.BankApi.services.DepositService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/deposits")
@AllArgsConstructor
public class DepositController {
    
    private DepositService depositService;

    @PutMapping("/{id}")
    public ResponseEntity<Deposit> updateDepositById(@PathVariable Long id, @RequestBody Deposit depositDetails)
    {
        return depositService.updateDeposit(id, depositDetails);
    }

    @PostMapping
    public Deposit createNewDeposit(@RequestBody Deposit depositDetails)
    {
        return depositService.addDeposit(depositDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Deposit> deleteDepositById(@PathVariable Long id)
    {
        return depositService.deleteDeposit(id);
    }

    @GetMapping()
    public ResponseEntity<List<Deposit>> getDeposits(@RequestParam(required = false, name = "sort-by") String sortColumn)
    {
        return depositService.getAllDeposits(sortColumn);
    }
}