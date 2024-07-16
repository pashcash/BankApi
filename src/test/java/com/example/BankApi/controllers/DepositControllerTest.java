package com.example.BankApi.controllers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
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

import com.example.BankApi.models.Deposit;
import com.example.BankApi.services.DepositService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepositController.class)
public class DepositControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DepositService depositService;

    private Deposit depositTest1;
    private Deposit depositTest2;

    @BeforeEach
    void SetUp() throws Exception
    {
        depositTest1 = new Deposit(1l, 1l, 1l, LocalDate.of(2023, 12, 1), 6.5, 12);
        depositTest2 = new Deposit(2l, 1l, 2l, LocalDate.of(2023, 12, 7), 10.5, 12);
    }

    @Test
    void testCreateNewDeposit() throws Exception {
        Mockito.when(depositService.addDeposit(depositTest1)).thenReturn(depositTest1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/deposits")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(depositTest1));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.clientId", is(1)))
            .andExpect(jsonPath("$.bankId", is(1)))
            .andExpect(jsonPath("$.openDate", is("2023-12-01")))
            .andExpect(jsonPath("$.percentage", is(6.5)))
            .andExpect(jsonPath("$.term", is(12)));
    }

    @Test
    void testDeleteDepositById() throws Exception {
        Mockito.when(depositService.getDepostById(1l)).thenReturn(depositTest1);

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/deposits/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void testGetDeposits() throws Exception {
        ResponseEntity<List<Deposit>> deposits = new ResponseEntity<>(new ArrayList<>(Arrays.asList(depositTest1, depositTest2)), HttpStatus.OK);
    
        Mockito.when(depositService.getAllDeposits(null)).thenReturn(deposits);
    
        mockMvc.perform(MockMvcRequestBuilders
            .get("/deposits")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].clientId", is(1)))
            .andExpect(jsonPath("$[0].bankId", is(1)))
            .andExpect(jsonPath("$[0].openDate", is("2023-12-01")))
            .andExpect(jsonPath("$[0].percentage", is(6.5)))
            .andExpect(jsonPath("$[0].term", is(12)))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].clientId", is(1)))
            .andExpect(jsonPath("$[1].bankId", is(2)))
            .andExpect(jsonPath("$[1].openDate", is("2023-12-07")))
            .andExpect(jsonPath("$[1].percentage", is(10.5)))
            .andExpect(jsonPath("$[1].term", is(12)));
    }

    @Test
    void testUpdateDepositById() throws Exception {
        Deposit updatedDeposit = Deposit.builder()
            .id(1l)
            .clientId(1l)
            .bankId(2l)
            .openDate(LocalDate.of(2023, 12, 1))
            .percentage(10.5)
            .term(12)
            .build();

        Mockito.when(depositService.getDepostById(1l)).thenReturn(updatedDeposit);
        Mockito.when(depositService.updateDeposit(1l, depositTest1)).thenReturn(new ResponseEntity<>(depositTest1, HttpStatus.OK));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/deposits/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(depositTest1));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.clientId", is(1)))
            .andExpect(jsonPath("$.bankId", is(1)))
            .andExpect(jsonPath("$.openDate", is("2023-12-01")))
            .andExpect(jsonPath("$.percentage", is(6.5)))
            .andExpect(jsonPath("$.term", is(12)));
    }
}
