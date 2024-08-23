package com.orientalexchange.service;


import com.orientalexchange.exception.BranchDetailsNotFound;
import com.orientalexchange.model.BranchRequest;
import com.orientalexchange.model.BranchTO;
import com.orientalexchange.model.BranchUpdateRequest;

import java.util.List;

public interface BranchService {

    BranchTO findById(String id) throws BranchDetailsNotFound;
    BranchTO findByBranchName(String name) throws BranchDetailsNotFound;

    BranchTO save(BranchRequest BranchRequest) throws BranchDetailsNotFound;



    BranchTO update(BranchUpdateRequest BranchRequest) throws BranchDetailsNotFound;

    String delete(String id) throws BranchDetailsNotFound;
}
