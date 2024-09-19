package com.neoteric.fullstackdemo.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Account {
    private String name;
    private String accountNumber;
    private String pan;
    private String mobileNumber;
    private double balance;
    private Address address;

}
