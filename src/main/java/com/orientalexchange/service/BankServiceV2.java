package com.orientalexchange.service;


import com.orientalexchange.exception.BankDetailsNotFound;
import com.orientalexchange.model.BankRequest;
import com.orientalexchange.model.BankTO;
import com.orientalexchange.model.BankUpdateRequest;

import java.util.List;

public interface BankServiceV2 {
    List<BankTO> findAll() throws BankDetailsNotFound;
    BankTO findById(String id) throws BankDetailsNotFound;
    BankTO findByBankName(String name) throws BankDetailsNotFound;

    BankTO save(BankRequest bankRequest) throws BankDetailsNotFound;



    BankTO update(BankUpdateRequest bankRequest) throws BankDetailsNotFound;

    String delete(String id) throws BankDetailsNotFound;
}

