package com.orientalexchange.service.impl;

import com.orientalexchange.exception.AccountNotFound;
import com.orientalexchange.model.AccountTO;
import com.orientalexchange.model.AccountRequest;
import com.orientalexchange.model.AccountUpdateRequest;
import com.orientalexchange.mongoRepository.AccountMongoRepository;
import com.orientalexchange.mongodbEntity.AccountMongo;
import com.orientalexchange.service.AccountServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountServiceImplV2 implements AccountServiceV2 {
    @Autowired
    AccountMongoRepository accountMongoRepository;

    @Override
    public List<AccountTO> findAllAccounts() throws AccountNotFound {
        log.info("Inside the AccountServiceImpl.findAllAccounts");
        List<AccountMongo> accountList = accountMongoRepository.findAll();
        log.info("Account List: {}", accountList);

        if(CollectionUtils.isEmpty(accountList)){
            log.info("Account details not found");
            throw new AccountNotFound("Account details not found");
        }
        List<AccountTO> accountTOList = accountList.stream().map(accountMongo -> {
            AccountTO accountTO = new AccountTO();

            accountTO.setAccountType(accountMongo.getAccountType());
            accountTO.setAccountBalance(accountMongo.getAccountBalance());

            return accountTO;
        }).collect(Collectors.toList());

        log.info("End of AccountServiceImpl.findAllAccounts");
        return accountTOList;
    }
    /**
     * Find the Account details by id
     * @param id
     * @return
     * @throws AccountNotFound
     */
    @Override
    public AccountTO findById(String id) throws AccountNotFound {
        log.info("Input to AccountServiceImpl.findById and id:{}", id);
        Optional<AccountMongo> AccountOptional =  accountMongoRepository.findById(id);

        if(AccountOptional.isEmpty()){
            log.error("Account details not found for the Account id:{}", id);
            throw new AccountNotFound("Account details not found for the Account id");
        }
        AccountMongo Account = AccountOptional.get();

        AccountTO AccountTO = new AccountTO();
       AccountTO.setAccountBalance(Account.getAccountBalance());
       AccountTO.setAccountType(Account.getAccountType());
        log.info("End of AccountServiceImpl.findById");
        return AccountTO;
    }



    /**
     * Save Account
     * @param accountRequest
     * @return
     * @throws AccountNotFound
     */
    @Override
    public AccountTO save(AccountRequest accountRequest) throws AccountNotFound {
        log.info("Inside the AccountServiceImpl.save, AccountRequest:{}", accountRequest);

        AccountMongo account = new AccountMongo();
      account.setAccountBalance(accountRequest.getAccountBalance());
      account.setAccountType(accountRequest.getAccountType());
        AccountMongo saveAccount = accountMongoRepository.save(account);

        if(Objects.isNull(saveAccount)){
            log.error("Account details not saved");
            throw  new AccountNotFound("Account details not saved");
        }

        AccountTO accountTO = new AccountTO();
        accountTO.setAccountBalance(saveAccount.getAccountBalance());
        accountTO.setAccountType(saveAccount.getAccountType());
        log.info("End of AccountServiceImpl.save");

        return accountTO;
    }

    @Override
    public AccountTO update(AccountUpdateRequest accountRequest) throws AccountNotFound {
        log.info("Inside the AccountServiceImpl.update, AccountRequest:{}", accountRequest);

        AccountMongo account = new AccountMongo();
        account.setAccountBalance(accountRequest.getAccountBalance());
        account.setAccountType(accountRequest.getAccountType());
        AccountMongo saveAccount = accountMongoRepository.save(account);

        if(Objects.isNull(saveAccount)){
            log.error("Account details not updated");
            throw  new AccountNotFound("Account details not updated");
        }

        AccountTO AccountTO = new AccountTO();
        AccountTO.setAccountType(saveAccount.getAccountType());
        AccountTO.setAccountBalance(saveAccount.getAccountBalance());

        log.info("End of AccountServiceImpl.update");

        return AccountTO;
    }

    @Override
    public String delete(String id) throws AccountNotFound {
        log.info("Inside the AccountServiceImpl.delete, id:{}", id);
        Optional<AccountMongo> AccountOptional = accountMongoRepository.findById(id);
        if(AccountOptional.isEmpty()){
            log.error("Account details not found for the Account id:{}", id);
            throw new AccountNotFound("Account details not found for the Account id");
        }
        accountMongoRepository.delete(AccountOptional.get());
        return "Account details has been deleted";
    }
}



