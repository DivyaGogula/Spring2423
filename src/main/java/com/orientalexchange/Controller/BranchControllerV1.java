package com.orientalexchange.Controller;


import com.orientalexchange.model.BranchRequest;
import com.orientalexchange.model.BranchUpdateRequest;
import com.orientalexchange.model.BranchTO;
import com.orientalexchange.service.BranchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("nosql/api/v1/branchs")
public class BranchControllerV1 {
    @Autowired
    BranchService branchService;

    //http://localhost:9090/api/v1/branchs/name?name=sbi - @RequestParam
   @GetMapping("/name")
    public ResponseEntity<BranchTO> findByBranchName(@RequestParam("name") String name) throws Exception{
        log.info("Inside the branchController.findByName and the name:{}", name);
        BranchTO branch = branchService.findByBranchName(name);
        log.info("branch details for the branch name:{} and the response:{}", name, branch);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BranchTO> findById(@PathVariable("id") String id) throws Exception{
        log.info("Inside the branchController.findById and the id:{}", id);
        BranchTO branch = branchService.findById(id);
        log.info("branch details for the branch code:{} and the response:{}", id, branch);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<BranchTO> save(@RequestBody @Valid BranchRequest branchRequest) throws Exception{
        log.info("Inside the branchController.save, branchRequest:{}", branchRequest);
        BranchTO branch = branchService.save(branchRequest);
        log.info("Save branch, response:{}", branch);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<BranchTO> update(@RequestBody BranchUpdateRequest  branchRequest) throws Exception{
        log.info("Inside the branchController.update, branchRequest:{}", branchRequest);
        BranchTO branch = branchService.update(branchRequest);
        log.info("update branch, response:{}", branch);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }

    //http://localhost:9090/api/v2/branchs?id=444
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id")  String id) throws Exception{
        log.info("Inside the branchController.delete, id:{}", id);
        String response = branchService.delete(id);
        log.info("Delete branch, response:{}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

// delete by branch name
}
