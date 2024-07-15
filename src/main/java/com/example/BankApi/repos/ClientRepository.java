package com.example.BankApi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankApi.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}