package com.orientalexchange.service;

import com.orientalexchange.exception.AccountNotFound;
import com.orientalexchange.exception.AccountNotFound;
import com.orientalexchange.model.*;

import java.util.List;

public interface AccountServiceV2 {
    List<AccountTO> findAllAccounts() throws AccountNotFound;
    AccountTO findById(String id) throws AccountNotFound;

    AccountTO save(AccountRequest accountRequest) throws AccountNotFound;



    AccountTO update(AccountUpdateRequest accountRequest) throws AccountNotFound;

    String delete(String id) throws AccountNotFound;
}
