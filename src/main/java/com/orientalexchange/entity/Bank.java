/*
package com.orientalexchange.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.Set;

@Getter
    @Setter
//    @Entity
//    @Table(name = "T_Bank")
    public class Bank implements Serializable {

        public static final long serialVersionUID = 4543545l;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Bank_Code")
        private int bankCode;

        @Column(name = "Bank_Name")
        private String bankName;

        @Column(name = "Bank_Address")
        private String bankAddress;

        @OneToMany(mappedBy = "bank")
        private Set<Branch> branchSet;
    }

*/
