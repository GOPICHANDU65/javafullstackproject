package com.neoteric.fullstackdemo.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Address {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String pincode;

}