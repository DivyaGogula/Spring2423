package com.orientalexchange.Controller;

import com.orientalexchange.exception.AccountNotFound;
import com.orientalexchange.model.AccountTO;
import com.orientalexchange.model.AccountRequest;
import com.orientalexchange.model.AccountUpdateRequest;
import com.orientalexchange.service.AccountServiceV2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("nosql/api/v2/accounts")
public class AccountControllerV2 {
        @Autowired
        AccountServiceV2 accountServiceV2;

        @GetMapping
        public ResponseEntity<List<AccountTO>> getAllAccounts() {
            log.info("Inside the AccountController.getAllAccounts");
            List<AccountTO> accountTOList = null;
            try {
                accountTOList = accountServiceV2.findAllAccounts();
                log.info("Account List:{}", accountTOList);
            } catch (AccountNotFound ex1){
                log.error("Account Details Not found", ex1);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception ex){
                log.error("Exception while getting the accounts", ex);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("End of AccountController.getAllAccounts");
            return new ResponseEntity<>(accountTOList, HttpStatus.OK);
        }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTO> findById(@PathVariable("id") String id) throws Exception{
        log.info("Inside the AccountController.findById and the id:{}", id);
        AccountTO account = accountServiceV2.findById(id);
        log.info("Account details for the Account code:{} and the response:{}", id, account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<AccountTO> save(@RequestBody @Valid AccountRequest accountRequest) throws Exception{
        log.info("Inside the AccountController.save, AccountRequest:{}", accountRequest);
        AccountTO Account = accountServiceV2.save(accountRequest);
        log.info("Save Account, response:{}", Account);
        return new ResponseEntity<>(Account, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountTO> update(@RequestBody @Valid AccountUpdateRequest accountRequest) throws Exception{
        log.info("Inside the AccountController.update, AccountRequest:{}", accountRequest);
        AccountTO Account = accountServiceV2.update(accountRequest);
        log.info("update Account, response:{}", Account);
        return new ResponseEntity<>(Account, HttpStatus.OK);
    }

    //http://localhost:9090/api/v2/Accounts?id=444
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id")  String id) throws Exception{
        log.info("Inside the AccountController.delete, id:{}", id);
        String response = accountServiceV2.delete(id);
        log.info("Delete Account, response:{}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

// delete by Account name
}
