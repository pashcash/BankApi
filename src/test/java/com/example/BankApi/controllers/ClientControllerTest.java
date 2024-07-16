package com.example.BankApi.controllers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.BankApi.enums.LegalForm;
import com.example.BankApi.models.Client;
import com.example.BankApi.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ClientService clientService;

    private Client clientTest1;
    private Client clientTest2;

    @BeforeEach
    void setUp()
    {
        clientTest1 = new Client(1l, "Gazprom", "GProm", "Perm, Petropavlovskaya 54", LegalForm.OOO);
        clientTest2 = new Client(2l, "Auchan", "Achn", "Mytishchi, Ostashkovskoe 1", LegalForm.OOO);
    }

    @Test
    void testAddNewClient() throws Exception {
        Mockito.when(clientService.addClient(clientTest1)).thenReturn(clientTest1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(clientTest1));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Gazprom")))
            .andExpect(jsonPath("$.shortName", is("GProm")))
            .andExpect(jsonPath("$.address", is("Perm, Petropavlovskaya 54")))
            .andExpect(jsonPath("$.legalForm", is("OOO")));
    }
    

    @Test
    void testDeleteClientById() throws Exception {
        Mockito.when(clientService.getClientById(1l)).thenReturn(clientTest1);

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/clients/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void testGetClients() throws Exception {
        ResponseEntity<List<Client>> clients = new ResponseEntity<>(new ArrayList<>(Arrays.asList(clientTest1, clientTest2)), HttpStatus.OK);
    
        Mockito.when(clientService.getAllClients(null)).thenReturn(clients);
    
        mockMvc.perform(MockMvcRequestBuilders
            .get("/clients")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].name", is("Gazprom")))
            .andExpect(jsonPath("$[0].shortName", is("GProm")))
            .andExpect(jsonPath("$[0].address", is("Perm, Petropavlovskaya 54")))
            .andExpect(jsonPath("$[0].legalForm", is("OOO")))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].name", is("Auchan")))
            .andExpect(jsonPath("$[1].shortName", is("Achn")))
            .andExpect(jsonPath("$[1].address", is("Mytishchi, Ostashkovskoe 1")))
            .andExpect(jsonPath("$[1].legalForm", is("OOO")));
    }

    @Test
    void testUpdateClientById() throws Exception {
        Client updatedClient = Client.builder()
            .id(1l)
            .name("Gazprom")
            .shortName("GProm")
            .address("Perm, Petropavlovskaya 54")
            .legalForm(LegalForm.OOO)
            .build();

        Mockito.when(clientService.getClientById(1l)).thenReturn(updatedClient);
        Mockito.when(clientService.updateClient(1l, clientTest1)).thenReturn(new ResponseEntity<>(clientTest1, HttpStatus.OK));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/clients/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(clientTest1));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Gazprom")))
            .andExpect(jsonPath("$.shortName", is("GProm")))
            .andExpect(jsonPath("$.address", is("Perm, Petropavlovskaya 54")))
            .andExpect(jsonPath("$.legalForm", is("OOO")));
    }
}