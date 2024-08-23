
package com.orientalexchange.service.impl;

import com.orientalexchange.mongoRepository.CustomerMongoRepository;
import com.orientalexchange.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMongoRepository customerMongoRepository;
}

