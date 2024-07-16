package com.example.BankApi.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "client_id")
    private Long clientId;
    
    @Column(name = "bank_id")
    private Long bankId;
    
    @Column(name = "open_date")
    private LocalDate openDate;
    
    @Column(name = "percentage")
    private Double percentage;
    
    @Column(name = "term")
    private Integer term;

    public Deposit(Long id, Long clientId, Long bankId, LocalDate openDate, Double percentage, Integer term) {
        this.id = id;
        this.clientId = clientId;
        this.bankId = bankId;
        this.openDate = openDate;
        this.percentage = percentage;
        this.term = term;
    }

    public Deposit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
        result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
        result = prime * result + ((openDate == null) ? 0 : openDate.hashCode());
        result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
        result = prime * result + ((term == null) ? 0 : term.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Deposit other = (Deposit) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (bankId == null) {
            if (other.bankId != null)
                return false;
        } else if (!bankId.equals(other.bankId))
            return false;
        if (openDate == null) {
            if (other.openDate != null)
                return false;
        } else if (!openDate.equals(other.openDate))
            return false;
        if (percentage == null) {
            if (other.percentage != null)
                return false;
        } else if (!percentage.equals(other.percentage))
            return false;
        if (term == null) {
            if (other.term != null)
                return false;
        } else if (!term.equals(other.term))
            return false;
        return true;
    }
}