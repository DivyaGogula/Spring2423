/*
package com.orientalexchange.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="T_Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_ID")
    private int customerID;

    @Column(name = "Customer_Name")
    private String customerName;

    @Column(name = "Customer_Address")
    private String customerAddress;

    @Column(name = "Customer_Phone")
    private String customerPhone;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accountSet;
}
*/
