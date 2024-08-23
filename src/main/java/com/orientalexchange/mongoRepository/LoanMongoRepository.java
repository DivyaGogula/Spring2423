package com.orientalexchange.mongoRepository;

import com.orientalexchange.mongodbEntity.LoanMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoanMongoRepository extends MongoRepository<LoanMongo,String> {
    Optional<LoanMongo> findByLoanType(String type);
}
