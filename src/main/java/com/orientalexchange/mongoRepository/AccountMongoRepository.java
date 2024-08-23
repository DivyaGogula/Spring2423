package com.orientalexchange.mongoRepository;

import com.orientalexchange.mongodbEntity.AccountMongo;
import com.orientalexchange.mongodbEntity.BranchMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountMongoRepository extends MongoRepository<AccountMongo,String>{

}
