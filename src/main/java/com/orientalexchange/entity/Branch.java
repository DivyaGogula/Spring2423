/*
package com.orientalexchange.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "T_Branch")
public class Branch implements Serializable {
    public static final long serialVersionUID= 1878678757l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Branch_ID")
    private  int branchId;

    @Column(name = "Branch_Name")
    private String branchName;


    @Column(name = "Branch_Address")
    private String branchAddress;

    @ManyToOne
    @JoinColumn(name = "Bank_Code")
    private Bank bank;
}
*/
