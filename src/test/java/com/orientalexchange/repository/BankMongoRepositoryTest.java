package com.orientalexchange.repository;

import com.orientalexchange.mongoRepository.BankMongoRepository;
import com.orientalexchange.mongodbEntity.BankMongo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
public class BankMongoRepositoryTest {
    @Autowired
    private BankMongoRepository bankMongoRepository;

    public void save(){
        BankMongo bank = new BankMongo();
        bank.setId("6694496e8bad99683a4ce334");
        bank.setBankName("BOFA");
        bank.setBankAddress("Bangalore");
        bank.setBankCode("567");
        bankMongoRepository.save(bank);
    }

    @Test
    public void findByIdRepo_whenBankDetailsExists_thenReturnBankDetails(){
        save();
        Optional<BankMongo> bankMongo = bankMongoRepository.findById("6694496e8bad99683a4ce334");
        assertEquals("567",  bankMongo.get().getBankCode());
    }
    @Test
    public void findByIdRepo_whenBankDetailsNotExists_thenThrowException(){
        save();
        Optional<BankMongo> bankMongo = bankMongoRepository.findById("6694496e8bad99683a4ce335");
       // assertThrows("",  );
    }
    @Test
    public void findByNameRepo_whenBankDetailsExists_thenReturnBankDetails() {
        save();
        Optional<BankMongo> bankMongo = bankMongoRepository.findByBankName("BOFA");
        assertEquals("567", bankMongo.get().getBankCode());
    }
    @Test
    public void saveRepo_whenBankDetailsExists_thenReturnBankDetails() {

        BankMongo bank = new BankMongo();
        bank.setId("6694496e8bad99683a4ce339");
        bank.setBankName("BOFA");
        bank.setBankAddress("Bangalore,India");
        bank.setBankCode("569");
        BankMongo bankMongo = bankMongoRepository.save(bank);
        assertEquals("569", bankMongo.getBankCode());
    }
    @Test
    public void updateRepo_whenBankDetailsExists_thenReturnBankDetails() {

        BankMongo bank = new BankMongo();
        bank.setId("6694496e8bad99683a4ce339");
        bank.setBankName("BOFA");
        bank.setBankAddress("Bangalore");
        bank.setBankCode("569");
        BankMongo bankMongo = bankMongoRepository.save(bank);
        assertEquals("569", bankMongo.getBankCode());
    }
    @Test
    public void deleteRepo_whenBankDetailsExists_thenReturnNothing() {
        BankMongo bankd = new BankMongo();
        bankd.setId("6694496e8bad99683a4ce339");
        bankd.setBankName("BOFA");
        bankd.setBankAddress("Bangalore");
        bankd.setBankCode("569");
        bankMongoRepository.delete(bankd);

    }

}
