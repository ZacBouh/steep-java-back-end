package com.zac.steep_back_end.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zac.steep_back_end.model.Aroma;

public interface AromaRepository extends MongoRepository<Aroma, String> {

    
    
}
