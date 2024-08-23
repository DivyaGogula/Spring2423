package com.orientalexchange.mongoRepository;

import com.orientalexchange.mongodbEntity.CustomerMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerMongoRepository extends MongoRepository<CustomerMongo,String> {
}
