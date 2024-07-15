package com.example.BankApi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankApi.models.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {}