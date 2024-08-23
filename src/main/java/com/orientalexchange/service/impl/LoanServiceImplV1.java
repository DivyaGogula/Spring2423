package com.orientalexchange.service.impl;

import com.orientalexchange.exception.BankDetailsNotFound;
import com.orientalexchange.exception.LoanNotFoundException;
import com.orientalexchange.model.BankTO;
import com.orientalexchange.model.LoanRequest;
import com.orientalexchange.model.LoanTO;
import com.orientalexchange.model.LoanUpdateRequest;
import com.orientalexchange.mongoRepository.LoanMongoRepository;
import com.orientalexchange.mongodbEntity.BankMongo;
import com.orientalexchange.mongodbEntity.LoanMongo;
import com.orientalexchange.mongodbEntity.LoanMongo;
import com.orientalexchange.service.LoanService;
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
public class LoanServiceImplV1 implements LoanService {

    @Autowired
    LoanMongoRepository loanMongoRepository;

    @Override
    public List<LoanTO> findAll() throws LoanNotFoundException {
        log.info("Inside the BankServiceImplV2.findAllBank");
        List<LoanMongo> loans = loanMongoRepository.findAll();

        log.info("List of Banks:{}", loans);
        if(!CollectionUtils.isEmpty(loans)){
            throw new LoanNotFoundException("loan Details not found");
        }

        List<LoanTO> loanTOS = loans.stream().map(loanMongo -> {
            LoanTO loanTO = new LoanTO();
            loanTO.setLoanAmount(loanMongo.getLoanAmount());
            loanTO.setLoanType(loanMongo.getLoanType());

            return loanTO;
        }).collect(Collectors.toList());
        log.info("End of BankServiceImplV2.findAllBank");
        return loanTOS;
    }


    @Override
    public LoanTO findById(String id) throws LoanNotFoundException {
        log.info("Input to BankServiceImpl.findById and id:{}", id);
        Optional<LoanMongo> loanOptional =  loanMongoRepository.findById(id);

        if(loanOptional.isEmpty()){
            log.error("Bank details not found for the loan id:{}", id);
            throw new LoanNotFoundException("Loan details not found for the loan id");
        }
        LoanMongo loanMongo = loanOptional.get();

        LoanTO loanTO = new LoanTO();
        loanTO.setLoanType(loanMongo.getLoanType());
        loanTO.setLoanAmount(loanMongo.getLoanAmount());

        log.info("End of LoanServiceImplV1.findById");
        return loanTO;
    }
    

    @Override
    public LoanTO findByLoanType(String type) throws LoanNotFoundException {
        log.info("Input to BankServiceImpl.findByType and type:{}", type);
        Optional<LoanMongo> loanOptional =  loanMongoRepository.findByLoanType(type);

        if(loanOptional.isEmpty()){
            log.error("Bank details not found for the loan type:{}", type);
            throw new LoanNotFoundException("Loan details not found for the bank name");
        }
        LoanMongo loanMongo = loanOptional.get();

        LoanTO loanTO = new LoanTO();
        loanTO.setLoanType(loanMongo.getLoanType());
        loanTO.setLoanAmount(loanMongo.getLoanAmount());
        log.info("End of BankServiceImpl.findByName");
        return loanTO;
    }
    

    @Override
    public LoanTO save(LoanRequest loanRequest) throws LoanNotFoundException {
        log.info("Inside the BankServiceImpl.save, loanRequest:{}", loanRequest);

        LoanMongo loan = new LoanMongo();
        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setLoanType(loanRequest.getLoanType());
        LoanMongo saveLoan = loanMongoRepository.save(loan);

        if(Objects.isNull(saveLoan)){
            log.error("Bank details not saved");
            throw  new LoanNotFoundException("Loan details not saved");
        }

        LoanTO loanTO = new LoanTO();
        loanTO.setLoanType(saveLoan.getLoanType());
        loanTO.setLoanAmount(saveLoan.getLoanAmount());
        log.info("End of LoanServiceImplV1.save");

        return loanTO;
    }


    @Override
    public LoanTO update(LoanUpdateRequest loanRequest) throws LoanNotFoundException {
        log.info("Inside the BankServiceImpl.save, loanRequest:{}", loanRequest);

        LoanMongo loan = new LoanMongo();
        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setLoanType(loanRequest.getLoanType());
        LoanMongo saveLoan =loanMongoRepository.save(loan);

        if(Objects.isNull(saveLoan)){
            log.error("Loan details not saved");
            throw  new LoanNotFoundException("Loan details not saved");
        }

        LoanTO loanTO = new LoanTO();
        loanTO.setLoanType(saveLoan.getLoanType());
        loanTO.setLoanAmount(saveLoan.getLoanAmount());
        log.info("End of LoanServiceImplV1.update");

        return loanTO;
    }



    @Override
    public String delete(String id) throws LoanNotFoundException {
        log.info("Inside the LoanServiceImpl.delete, id:{}", id);
        Optional<LoanMongo> loanOptional = loanMongoRepository.findById(id);
        if(loanOptional.isEmpty()){
            log.error("Loan details not found for the bank id:{}", id);
            throw new LoanNotFoundException("Loan details not found for the bank id");
        }
        loanMongoRepository.delete(loanOptional.get());
        return "Bank details has been deleted";
    }
    }

