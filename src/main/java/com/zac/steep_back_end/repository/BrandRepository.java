package com.zac.steep_back_end.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.zac.steep_back_end.model.Brand;
import java.util.List;


public interface BrandRepository extends MongoRepository<Brand, String> {

    @Query("{'name': ?0}")
    Boolean existsByName(String name);

    @Query("{'name': ?0}")
    Brand  findByName(String name);
}
