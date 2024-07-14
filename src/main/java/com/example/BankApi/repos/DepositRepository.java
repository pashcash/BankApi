package com.example.BankApi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BankApi.models.Deposit;

public interface DepositRepository extends JpaRepository<Deposit,Long> {}