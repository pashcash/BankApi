package com.example.BankApi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BankApi.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
    
}
