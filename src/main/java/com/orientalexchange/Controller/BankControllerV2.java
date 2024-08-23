package com.orientalexchange.Controller;

import com.orientalexchange.exception.BankDetailsNotFound;
import com.orientalexchange.model.BankRequest;
import com.orientalexchange.model.BankTO;
import com.orientalexchange.model.BankUpdateRequest;
import com.orientalexchange.service.BankServiceV2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("nosql/api/v2/banks")
public class BankControllerV2 {
    @Autowired
    public BankServiceV2 bankService;
    @GetMapping
    public ResponseEntity<List<BankTO>> findAll() throws BankDetailsNotFound {
        log.info("Inside the BankControllerV2.findAll");
        List<BankTO> banks = bankService.findAll();
        log.info("Bank details for all the ids");
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }
     /*   List<BankTO> bankTOS = null;
        try{
            bankTOS = bankService.findAll();
        } catch (BankDetailsNotFound ex){
            log.error("Bank details not found", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.error("Exception while getting the bank details", ex1);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankControllerV2.findAll");
        return new ResponseEntity<>(bankTOS, HttpStatus.OK);
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity<BankTO> findById(@PathVariable("id") String id) throws Exception{
        log.info("Inside the BankController.findById and the id:{}", id);
        BankTO bank = bankService.findById(id);
        log.info("Bank details for the bank code:{} and the response:{}", id, bank);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    //http://localhost:9090/api/v2/banks/name?name=sbi - @RequestParam
    @GetMapping("/name")
    public ResponseEntity<BankTO> findByBankName(@RequestParam("name") String name) throws Exception{
        log.info("Inside the BankController.findByName and the name:{}", name);
        BankTO bank = bankService.findByBankName(name);
        log.info("Bank details for the bank name:{} and the response:{}", name, bank);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BankTO> save(@RequestBody @Valid BankRequest bankRequest) throws Exception{
        log.info("Inside the BankController.save, bankRequest:{}", bankRequest);
        BankTO bank = bankService.save(bankRequest);
        log.info("Save bank, response:{}", bank);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BankTO> update(@RequestBody @Valid BankUpdateRequest bankRequest) throws Exception{
        log.info("Inside the BankController.update, bankRequest:{}", bankRequest);
        BankTO bank = bankService.update(bankRequest);
        log.info("update bank, response:{}", bank);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    //http://localhost:9090/api/v2/banks?id=444
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id")  String id) throws Exception{
        log.info("Inside the BankController.delete, id:{}", id);
        String response = bankService.delete(id);
        log.info("Delete bank, response:{}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

// delete by bank name

}

