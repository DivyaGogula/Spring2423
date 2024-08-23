package com.orientalexchange.mongoRepository;

import com.orientalexchange.mongodbEntity.BankMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface BankMongoRepository extends MongoRepository<BankMongo, String> {


    Optional<BankMongo> findByBankName(String name);


   /* @Query("select bank from BankMongo bank where bankName=:name")
    Optional<BankMongo> findByName(@Param("name") String name);*/
}
