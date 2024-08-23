package com.orientalexchange.mongoRepository;

import com.orientalexchange.mongodbEntity.BankMongo;
import com.orientalexchange.mongodbEntity.BranchMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BranchMongoRepository extends MongoRepository<BranchMongo, String> {
    Optional<BranchMongo> findByBranchName(String name);
}