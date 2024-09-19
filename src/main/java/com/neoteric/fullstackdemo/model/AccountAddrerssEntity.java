package com.neoteric.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccountAddrerssEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "accountNumber",
            referencedColumnName = "accountNumber")
    private AccountEntity accountEntity;

}