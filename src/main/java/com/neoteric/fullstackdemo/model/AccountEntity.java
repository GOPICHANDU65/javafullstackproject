package com.neoteric.fullstackdemo.model;


import com.neoteric.fullstackdemo.model.AccountAddrerssEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="account",schema = "bank")
@Data

public class AccountEntity {
    public AccountEntity(){

    }

    @Column(name="name",nullable = false)
    private String  name;
    @Id
    @Column(name="accountNumber")
    private String  accountNumber;
    @Column(name = "pan",nullable = false)
    private String pan;
    @Column(name = "mobileNumber",nullable = false)
    private String mobileNUmber;
    @Column(name = "balance",nullable = false)
    private double  balance;


    @OneToMany(mappedBy = "accountEntity",
            cascade = CascadeType.PERSIST,fetch =FetchType.EAGER )
    public List<AccountAddrerssEntity>accountAddrerssEntityList;
}