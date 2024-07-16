package com.example.BankApi.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.BankApi.exceptions.types.EntityNotFoundException;
import com.example.BankApi.models.Bank;
import com.example.BankApi.services.BankService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BankController.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BankService bankService;

    private Bank bankTest1;
    private Bank bankTest2;

    @BeforeEach
    void setUp()
    {
        bankTest1 = new Bank((long) 1, "Alpha", "482321584");
        bankTest2 = new Bank((long) 2, "Otkritie", "217547203");
    }

    @Test
    void testAddNewBank_success() throws Exception {
        Mockito.when(bankService.addBank(bankTest1)).thenReturn(bankTest1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/banks")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(bankTest1));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Alpha")))
            .andExpect(jsonPath("$.bic", is("482321584")));
        }

    @Test
    void testDeleteBankById_success() throws Exception {
        Mockito.when(bankService.addBank(bankTest1)).thenReturn(bankTest1);

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/banks/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void testDeleteBankById_notFound() throws Exception {
        Mockito.when(bankService.addBank(bankTest2)).thenReturn(bankTest2);
        Mockito.when(bankService.deleteBank(1l)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/banks/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testGetBanks_success() throws Exception {
        ResponseEntity<List<Bank>> banks = new ResponseEntity<>(new ArrayList<>(Arrays.asList(bankTest1, bankTest2)), HttpStatus.OK);
    
        Mockito.when(bankService.getAllBanks(null)).thenReturn(banks);
    
        mockMvc.perform(MockMvcRequestBuilders
            .get("/banks")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].name", is("Alpha")))
            .andExpect(jsonPath("$[0].bic", is("482321584")))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].name", is("Otkritie")))
            .andExpect(jsonPath("$[1].bic", is("217547203")));
    }

    @Test
    void testGetBanksSorted_success() throws Exception {
        ResponseEntity<List<Bank>> banks = new ResponseEntity<>(new ArrayList<>(Arrays.asList(bankTest2, bankTest1)), HttpStatus.OK);
    
        Mockito.when(bankService.getAllBanks("bic")).thenReturn(banks);
    
        mockMvc.perform(MockMvcRequestBuilders
            .get("/banks?sort-by=bic")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(2)))
            .andExpect(jsonPath("$[0].name", is("Otkritie")))
            .andExpect(jsonPath("$[0].bic", is("217547203")))
            .andExpect(jsonPath("$[1].id", is(1)))
            .andExpect(jsonPath("$[1].name", is("Alpha")))
            .andExpect(jsonPath("$[1].bic", is("482321584")));
    }

    @Test
    void testUpdateBankById_success() throws Exception {
        Bank updatedBank = new Bank(1l,"Sberbank","428912324");

        Mockito.when(bankService.getBankById(1l)).thenReturn(updatedBank);
        Mockito.when(bankService.updateBank(1l, bankTest1)).thenReturn(new ResponseEntity<>(bankTest1, HttpStatus.OK));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/banks/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(bankTest1));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Alpha")))
            .andExpect(jsonPath("$.bic", is("482321584")));
    }

    @Test
    void testUpdateBankById_notFound() throws Exception {
        Mockito.when(bankService.updateBank(2l, bankTest1)).thenThrow(EntityNotFoundException.class);
        
        mockMvc.perform(MockMvcRequestBuilders
            .put("/banks/2")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(bankTest1)))
            .andExpect(status().isNotFound());
    }
}