package com.orientalexchange.service.impl;

import com.orientalexchange.exception.BranchDetailsNotFound;
import com.orientalexchange.model.BranchRequest;
import com.orientalexchange.model.BranchTO;
import com.orientalexchange.model.BranchUpdateRequest;
import com.orientalexchange.mongoRepository.BranchMongoRepository;
import com.orientalexchange.mongodbEntity.BranchMongo;
import com.orientalexchange.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class BranchServiceImplV1 implements BranchService {
    @Autowired
    BranchMongoRepository branchMongoRepository;
   @Override
    public BranchTO findByBranchName(String name) throws BranchDetailsNotFound {
        log.info("Input to BranchServiceImpl.findByBranchName and name:{}", name);
        Optional<BranchMongo> branchOptional = branchMongoRepository.findByBranchName(name);

        if (branchOptional.isEmpty()) {
            log.error("Branch details not found for the Branch name:{}", name);
            throw new BranchDetailsNotFound("Branch details not found for the Branch name");
        }
        BranchMongo branchMongo = branchOptional.get();

       BranchTO branchTO = new BranchTO();
       branchTO.setBranchName(branchMongo.getBranchName());
       branchTO.setBranchAddress(branchMongo.getBranchAddress());

        log.info("End of BranchServiceImpl.findByBranchName");
        return branchTO;

    }



    /**
     * Find the Branch details by id
     * @param id
     * @return
     * @throws BranchDetailsNotFound
     */
    @Override
    public BranchTO findById(String id) throws BranchDetailsNotFound {
        log.info("Input to BranchServiceImpl.findById and id:{}", id);
        Optional<BranchMongo> BranchOptional =  branchMongoRepository.findById(id);

        if(BranchOptional.isEmpty()){
            log.error("Branch details not found for the Branch id:{}", id);
            throw new BranchDetailsNotFound("Branch details not found for the Branch id");
        }
        BranchMongo Branch = BranchOptional.get();

        BranchTO BranchTO = new BranchTO();

        BranchTO.setBranchName(Branch.getBranchName());
        BranchTO.setBranchAddress(Branch.getBranchAddress());
        log.info("End of BranchServiceImpl.findById");
        return BranchTO;
    }


    /**
     * Save Branch
     * @param BranchRequest
     * @return
     * @throws BranchDetailsNotFound
     */
    @Override
    public BranchTO save(BranchRequest BranchRequest) throws BranchDetailsNotFound {
        log.info("Inside the BranchServiceImpl.save, BranchRequest:{}", BranchRequest);

        BranchMongo Branch = new BranchMongo();
        Branch.setBranchName(BranchRequest.getBranchName());
        Branch.setBranchAddress(BranchRequest.getBranchAddress());
        BranchMongo saveBranch = branchMongoRepository.save(Branch);

        if(Objects.isNull(saveBranch)){
            log.error("Branch details not saved");
            throw  new BranchDetailsNotFound("Branch details not saved");
        }

        BranchTO BranchTO = new BranchTO();
        BranchTO.setBranchName(saveBranch.getBranchName());
        BranchTO.setBranchAddress(saveBranch.getBranchAddress());
        log.info("End of BranchServiceImpl.save");

        return BranchTO;
    }



    @Override
    public BranchTO update(BranchUpdateRequest BranchRequest) throws BranchDetailsNotFound {
        log.info("Inside the BranchServiceImpl.update, BranchRequest:{}", BranchRequest);

        BranchMongo Branch = new BranchMongo();
        Branch.setBranchName(BranchRequest.getBranchName());
        // Branch.setBranchCode(BranchRequest.getBranchCode());
        Branch.setBranchAddress(BranchRequest.getBranchAddress());
        BranchMongo saveBranch = branchMongoRepository.save(Branch);

        if(Objects.isNull(saveBranch)){
            log.error("Branch details not updated");
            throw  new BranchDetailsNotFound("Branch details not updated");
        }

        BranchTO BranchTO = new BranchTO();
        BranchTO.setBranchName(saveBranch.getBranchName());
        BranchTO.setBranchAddress(saveBranch.getBranchAddress());
        log.info("End of BranchServiceImpl.update");

        return BranchTO;
    }

    @Override
    public String delete(String id) throws BranchDetailsNotFound {
        log.info("Inside the BranchServiceImpl.delete, id:{}", id);
        Optional<BranchMongo> BranchOptional = branchMongoRepository.findById(id);
        if(BranchOptional.isEmpty()){
            log.error("Branch details not found for the Branch id:{}", id);
            throw new BranchDetailsNotFound("Branch details not found for the Branch id");
        }
        branchMongoRepository.delete(BranchOptional.get());
        return "Branch details has been deleted";
    }
}


