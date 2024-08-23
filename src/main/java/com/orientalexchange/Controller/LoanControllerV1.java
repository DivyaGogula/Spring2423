package com.orientalexchange.Controller;

import com.orientalexchange.model.LoanRequest;
import com.orientalexchange.model.LoanUpdateRequest;
import com.orientalexchange.model.LoanTO;
import com.orientalexchange.service.LoanService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("nosql/api/v1/loans")
public class LoanControllerV1 {
    
    @Autowired
    LoanService loanService;
    //http://localhost:9090/api/v1/loans/name?name=sbi - @RequestParam
    @GetMapping("/name")
    public ResponseEntity<LoanTO> findByLoanType(@RequestParam("type") String type) throws Exception{
        log.info("Inside the loanController.findByLoanType and the type:{}", type);
        LoanTO loanTO = loanService.findByLoanType(type);
        log.info("loan details for the loan type:{} and the response:{}", type, loanTO);
        return new ResponseEntity<>(loanTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LoanTO> findById(@PathVariable("id") String id) throws Exception{
        log.info("Inside the loanController.findById and the id:{}", id);
        LoanTO loan = loanService.findById(id);
        log.info("loan details for the loan code:{} and the response:{}", id, loan);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<LoanTO> save(@RequestBody @Valid LoanRequest loanRequest) throws Exception{
        log.info("Inside the loanController.save, loanRequest:{}", loanRequest);
        LoanTO loan = loanService.save(loanRequest);
        log.info("Save loan, response:{}", loan);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<LoanTO> update(@RequestBody LoanUpdateRequest loanRequest) throws Exception{
        log.info("Inside the loanController.update, loanRequest:{}", loanRequest);
        LoanTO loan = loanService.update(loanRequest);
        log.info("update loan, response:{}", loan);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    //http://localhost:9090/api/v2/loans?id=444
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id")  String id) throws Exception{
        log.info("Inside the loanController.delete, id:{}", id);
        String response = loanService.delete(id);
        log.info("Delete loan, response:{}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

// delete by loan 
}
