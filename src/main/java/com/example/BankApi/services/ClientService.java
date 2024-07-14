package com.example.BankApi.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BankApi.exceptions.types.BadRequestException;
import com.example.BankApi.exceptions.types.EntityNotFoundException;
import com.example.BankApi.models.Client;
import com.example.BankApi.repos.ClientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {
     
    private ClientRepository clientRepository;

    public ResponseEntity<Client> updateClient(Long clientId, Client clientDetails) 
    {
        Optional<Client> clientData = clientRepository.findById(clientId);
        if (!clientData.isPresent())
			throw new EntityNotFoundException("Client with id = " + clientId + " doesn`t exist");
        Client client = clientData.get();
        client.setName(clientDetails.getName());
        client.setShortName(clientDetails.getShortName());
        client.setAddress(clientDetails.getAddress());
        client.setLegalForm(clientDetails.getLegalForm());
        clientRepository.save(client);
        return new ResponseEntity<>(client, HttpStatus.OK);    
    }

    public Client addClient(Client clientDetails)
    {
        return clientRepository.save(clientDetails);
    }

    public ResponseEntity<Client> deleteClient(Long clientId)
    {
        Optional<Client> clientData = clientRepository.findById(clientId);
        if (!clientData.isPresent())
			throw new EntityNotFoundException("Client with id = " + clientId + " doesn`t exist");
        clientRepository.deleteById(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Client>> getAllClients(String sortColumn)
    {
        if (sortColumn == null)
            return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
        List<String> columns = Arrays.asList("id", "name", "shortName", "address", "legalForm"); 
        if (!columns.contains(sortColumn))
            throw new BadRequestException("Sorting param is incorrect");
        return new ResponseEntity<>(clientRepository.findAll(Sort.by(sortColumn)), HttpStatus.OK);
    }
}
