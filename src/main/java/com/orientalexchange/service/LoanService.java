package com.orientalexchange.service;

import com.orientalexchange.exception.LoanNotFoundException;
import com.orientalexchange.model.*;

import java.util.List;

public interface LoanService {
    List<LoanTO> findAll() throws LoanNotFoundException;
    LoanTO findById(String id) throws LoanNotFoundException;
    LoanTO findByLoanType(String type) throws LoanNotFoundException;

    LoanTO save(LoanRequest loanRequest) throws LoanNotFoundException;



    LoanTO update(LoanUpdateRequest loanRequest) throws LoanNotFoundException;

    String delete(String id) throws LoanNotFoundException;


}
