/*
package com.orientalexchange.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "T_Loan")
public class Loan implements Serializable {
    public static final long serialVersionUID = 343243545l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Loan_ID")
    private int loanId;


    @Column(name = "Loan_Type")
    private String loanType;

    @Column(name = "Loan_Amount")
    private int loanAmount;

    @ManyToOne
    @JoinColumn(name = "Branch_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "Cust_ID")
    private Customer customer;
}
*/
