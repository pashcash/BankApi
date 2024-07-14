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

import com.example.BankApi.models.Client;
import com.example.BankApi.services.ClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    
    private ClientService clientService;

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClientById(@PathVariable Long id, @RequestBody Client clientDetails)
    {
        return clientService.updateClient(id, clientDetails);
    }

    @PostMapping
    public Client addNewClient(@RequestBody Client clientDetails)
    {
        return clientService.addClient(clientDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClientById (@PathVariable Long id)
    {
        return clientService.deleteClient(id);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getClients(@RequestParam(required = false, name="sort-by") String sortColumn)
    {
        return clientService.getAllClients(sortColumn);
    }
}
