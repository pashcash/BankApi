package com.example.BankApi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BankApi.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {}