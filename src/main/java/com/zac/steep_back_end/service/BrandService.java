package com.zac.steep_back_end.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zac.steep_back_end.model.Aroma;
import com.zac.steep_back_end.model.Brand;
import com.zac.steep_back_end.repository.BrandRepository;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    

    @Transactional
    public Brand saveNewBrand(Brand brandRequest){
        
        Brand brand = brandRepository.findByName(brandRequest.getName());
        if (brand != null) return brand;

        brand = brandRepository.save(new Brand(brandRequest.getName()));
        return brand;

    }

    @Transactional
    public Long addAromaToBrand(Aroma aroma, Brand brand) throws BadRequestException{
        if(aroma.getId() == null || brand.getId() == null) throw new BadRequestException("id for brand an aroma must be provided");


        Query query = new Query(Criteria.where("_id").is(brand.getId()));
        Update update = new Update().push("brand.aromas", aroma.getId());

        return mongoTemplate.updateFirst(query, update, Brand.class).getModifiedCount();
    }
}
