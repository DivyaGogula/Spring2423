package com.orientalexchange.service.impl;


import com.orientalexchange.exception.BankDetailsNotFound;
import com.orientalexchange.model.BankRequest;
import com.orientalexchange.model.BankTO;
import com.orientalexchange.model.BankUpdateRequest;
import com.orientalexchange.mongoRepository.BankMongoRepository;
import com.orientalexchange.mongodbEntity.BankMongo;
import com.orientalexchange.service.BankServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
/*@EnableMethodSecurity(prePostEnabled = true)*/
public class BankServiceImplV2 implements BankServiceV2 {
    @Autowired
    public BankMongoRepository bankMongoRepository;
   /* @PreAuthorize("hasRole('ADMIN')")*/
    @Override
    public List<BankTO> findAll() throws BankDetailsNotFound {
        log.info("Inside the BankServiceImplV2.findAllBank");
        List<BankMongo> banks = bankMongoRepository.findAll();
        System.out.println(banks);

        log.info("List of Banks:{}", banks);
        if(CollectionUtils.isEmpty(banks)){
            throw new BankDetailsNotFound("Bank Details not found");
        }

        List<BankTO> bankTOS = banks.stream().map(bankMongo -> {
            BankTO bankTO = new BankTO();
            bankTO.setId(bankMongo.getId());
            bankTO.setBankName(bankMongo.getBankName());
            bankTO.setBankAddress(bankMongo.getBankAddress());
            bankTO.setBankCode(bankMongo.getBankCode());
            return bankTO;
        }).collect(Collectors.toList());
        log.info("End of BankServiceImplV2.findAllBank");
        return bankTOS;
    }

    /**
     * Find the bank details by id
     * @param id
     * @return
     * @throws BankDetailsNotFound
     */
    @Override
    public BankTO findById(String id) throws BankDetailsNotFound {
        log.info("Input to BankServiceImpl.findById and id:{}", id);
        Optional<BankMongo> bankOptional =  bankMongoRepository.findById(id);

        if(bankOptional.isEmpty()){
            log.error("Bank details not found for the bank id:{}", id);
            throw new BankDetailsNotFound("Bank details not found");
        }
        BankMongo bank = bankOptional.get();

        BankTO bankTO = new BankTO();
        bankTO.setBankCode(bank.getBankCode());
        bankTO.setBankName(bank.getBankName());
        bankTO.setBankAddress(bank.getBankAddress());
        log.info("End of BankServiceImpl.findById");
        return bankTO;
    }

    /**
     * Find the bank details by bank name
     * @param name
     * @return
     * @throws BankDetailsNotFound
     */
    @Override
    public BankTO findByBankName(String name) throws BankDetailsNotFound {
        log.info("Input to BankServiceImpl.findByName and name:{}", name);
        Optional<BankMongo> bankOptional =  bankMongoRepository.findByBankName(name);

        if(bankOptional.isEmpty()){
            log.error("Bank details not found for the bank name:{}", name);
            throw new BankDetailsNotFound("Bank details not found for the bank name");
        }
        BankMongo bank = bankOptional.get();

        BankTO bankTO = new BankTO();
        bankTO.setId(bank.getId());
        bankTO.setBankCode(bank.getBankCode());
        bankTO.setBankName(bank.getBankName());
        bankTO.setBankAddress(bank.getBankAddress());
        log.info("End of BankServiceImpl.findByName");
        return bankTO;
    }
    /**
     * Save Bank
     * @param bankRequest
     * @return
     * @throws BankDetailsNotFound
     */
    @Override
    public BankTO save(BankRequest bankRequest) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.save, bankRequest:{}", bankRequest);

        BankMongo bank = new BankMongo();
        bank.setBankName(bankRequest.getBankName());
       bank.setBankCode(bankRequest.getBankCode());
        bank.setBankAddress(bankRequest.getBankAddress());
        BankMongo saveBank = bankMongoRepository.save(bank);

        if(Objects.isNull(saveBank)){
            log.error("Bank details not saved");
            throw  new BankDetailsNotFound("Bank details not saved");
        }

        BankTO bankTO = new BankTO();
        bankTO.setBankCode(saveBank.getBankCode());
        bankTO.setBankName(saveBank.getBankName());
        bankTO.setBankAddress(saveBank.getBankAddress());
        log.info("End of BankServiceImpl.save");

        return bankTO;
    }

    @Override
    public BankTO update(BankUpdateRequest bankRequest) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.update, bankRequest:{}", bankRequest);

        BankMongo bank = new BankMongo();
        bank.setBankName(bankRequest.getBankName());
       bank.setBankCode(bankRequest.getBankCode());
        bank.setBankAddress(bankRequest.getBankAddress());
        BankMongo saveBank = bankMongoRepository.save(bank);

        if(Objects.isNull(saveBank)){
            log.error("Bank details not updated");
            throw  new BankDetailsNotFound("Bank details not updated");
        }

        BankTO bankTO = new BankTO();
        bankTO.setBankCode(saveBank.getBankCode());
        bankTO.setBankName(saveBank.getBankName());
        bankTO.setBankAddress(saveBank.getBankAddress());
        log.info("End of BankServiceImpl.update");

        return bankTO;
    }

    @Override
    public String delete(String id) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.delete, id:{}", id);
        Optional<BankMongo> bankOptional = bankMongoRepository.findById(id);
        if(bankOptional.isEmpty()){
            log.error("Bank details not found for the bank id:{}", id);
            throw new BankDetailsNotFound("Bank details not found for the bank id");
        }
        bankMongoRepository.delete(bankOptional.get());
        return "Bank details has been deleted";
    }
}














