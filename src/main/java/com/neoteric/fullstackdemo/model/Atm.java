package com.neoteric.fullstackdemo.model;

import lombok.Data;

public class Atm {
    private String cardNumber;
    private String pin;
    private String accountNumber;
    private String cvv;
    private Data cardExpiry;
}
