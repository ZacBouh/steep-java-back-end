package com.zac.steep_back_end.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zac.steep_back_end.model.Brand;

public interface BrandRepository extends MongoRepository<Brand, String> {
    
}
