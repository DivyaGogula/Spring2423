package com.orientalexchange.service;

import com.orientalexchange.exception.BankDetailsNotFound;
import com.orientalexchange.model.BankRequest;
import com.orientalexchange.model.BankTO;
import com.orientalexchange.model.BankUpdateRequest;
import com.orientalexchange.mongoRepository.BankMongoRepository;
import com.orientalexchange.mongodbEntity.BankMongo;
import com.orientalexchange.service.impl.BankServiceImplV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BankServiceImplV2Test {
    @Mock
    private BankMongoRepository bankMongoRepository;

    @Mock
    BankRequest bankRequest;

    @Mock
    BankUpdateRequest updateRequest;

    @InjectMocks
    private BankServiceImplV2 bankServiceImplV2;

    @Test
    public void findAll_whenBankDetailsExists_thenReturnBanks() throws BankDetailsNotFound {
        List<BankMongo> banks = new ArrayList<>();
        BankMongo bank = new BankMongo();
        bank.setBankName("SBI");
        bank.setBankCode("12");
        bank.setBankAddress("Bangalore");
        banks.add(bank);

        when(bankMongoRepository.findAll()).thenReturn(banks);
        List<BankTO> bankTOS = bankServiceImplV2.findAll();
        assertEquals(1, bankTOS.size());
    }


    @Test
    public void findAll_whenBankDetailsNotExists_thenThrowException() {
        List<BankMongo> banks = new ArrayList<>();

        when(bankMongoRepository.findAll()).thenReturn(banks);

        BankDetailsNotFound thrown = assertThrows(BankDetailsNotFound.class, () -> {
            bankServiceImplV2.findAll();
        });

        assertEquals("Bank Details not found", thrown.getMessage());
    }

    @Test
    public void findById_whenBankDetailsExists_thenReturnBank() throws BankDetailsNotFound {
        BankMongo bank = new BankMongo();
        when(bankMongoRepository.findById(anyString())).thenReturn(Optional.of(bank));
        BankTO bankTO = bankServiceImplV2.findById("6684a641a20cab0fdba3cc34");
        assertEquals(bank.getBankName(), bankTO.getBankName());
    }

    @Test
    public void findById_whenBankDetailsNotExists_thenThrowException() throws BankDetailsNotFound {
        BankMongo bank = null;
        when(bankMongoRepository.findById(anyString())).thenReturn(Optional.ofNullable(bank));
        BankDetailsNotFound thrown = assertThrows(BankDetailsNotFound.class, () -> {
            bankServiceImplV2.findById(anyString());
        });
        assertEquals("Bank details not found", thrown.getMessage());
    }


    @Test
    public void findByName_whenBankDetailsExists_thenReturnBanks() throws BankDetailsNotFound {
        BankMongo bank = new BankMongo();
        when(bankMongoRepository.findByBankName(anyString())).thenReturn(Optional.of(bank));
        BankTO bankTO = bankServiceImplV2.findByBankName("BOA");
        assertEquals(bank.getBankName(), bankTO.getBankName());
    }

    @Test
    public void findByName_whenBankDetailsNotExists_thenThrowException() {
        BankMongo bank = null;

        when(bankMongoRepository.findByBankName(anyString())).thenReturn(Optional.ofNullable(bank));
        BankDetailsNotFound thrown = assertThrows(BankDetailsNotFound.class, () -> {
            bankServiceImplV2.findByBankName(anyString());
        });
        assertEquals("Bank details not found for the bank name", thrown.getMessage());
    }

    //Save Test +Ve case

    @Test
    public void save_whenBankDetailsExists_thenReturnBanks() throws BankDetailsNotFound {
        BankMongo bank = new BankMongo();
        bank.setBankName(bankRequest.getBankName());
        bank.setBankCode(bankRequest.getBankCode());
        bank.setBankAddress(bankRequest.getBankAddress());
        when(bankMongoRepository.save(any())).thenReturn(bank);
        BankTO bankTO = bankServiceImplV2.save(bankRequest);
        assertEquals(bank.getBankName(), bankTO.getBankName());
    }

    @Test
    public void save_whenBankDetailsNotExists_thenThrowException(){
        BankMongo bank = null;
        BankRequest bankRequest=new BankRequest();

        when(bankMongoRepository.save(any())).thenReturn(bank);

        BankDetailsNotFound thrown = assertThrows(BankDetailsNotFound.class, () -> {
            bankServiceImplV2.save(bankRequest);
        });

        assertEquals("Bank details not saved", thrown.getMessage());
    }
    @Test
    public void update_whenBankDetailsExists_thenReturnBanks() throws BankDetailsNotFound {
        BankMongo bank = new BankMongo();
        bank.setBankName(updateRequest.getBankName());
        bank.setBankCode(updateRequest.getBankCode());
        bank.setBankAddress(updateRequest.getBankAddress());
        when(bankMongoRepository.save(any())).thenReturn(bank);
        BankTO bankTO = bankServiceImplV2.update(updateRequest);
        assertEquals(bank.getBankName(), bankTO.getBankName());
    }

    @Test
    public void update_whenBankDetailsNotExists_thenThrowException(){
        BankMongo bank = null;

        when(bankMongoRepository.save(any())).thenReturn(bank);

        BankDetailsNotFound thrown = assertThrows(BankDetailsNotFound.class, () -> {
            bankServiceImplV2.update(updateRequest);
        });

        assertEquals("Bank details not updated", thrown.getMessage());
    }
    @Test
    public void delete_whenBankDetailsExists_thenReturnString() throws BankDetailsNotFound {
        BankMongo bank = new BankMongo();
        when(bankMongoRepository.findById(anyString())).thenReturn(Optional.ofNullable(bank));
        String s = bankServiceImplV2.delete(anyString());
        assertEquals("Bank details has been deleted", s);
    }
    @Test
    public void delete_whenBankDetailsNotExists_thenThrowException() {
        BankMongo bank=null;
        when(bankMongoRepository.findById(anyString())).thenReturn(Optional.ofNullable(bank));

        BankDetailsNotFound thrown = assertThrows(BankDetailsNotFound.class, () -> {
            bankServiceImplV2.delete(anyString());
        });

        assertEquals("Bank details not found for the bank id", thrown.getMessage());
    }

}


