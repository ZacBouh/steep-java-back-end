package com.zac.steep_back_end.service;

import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zac.steep_back_end.model.Aroma;
import com.zac.steep_back_end.model.Brand;
import com.zac.steep_back_end.repository.AromaRepository;
import com.zac.steep_back_end.repository.BrandRepository;

@Service
public class AromaService {
    @Autowired
    AromaRepository aromaRepo;
    @Autowired
    BrandRepository brandRepo;
    @Autowired
    BrandService brandService;
    @Autowired
    MongoTemplate mongoTemplate;


    @Transactional
    public Aroma saveAroma(Aroma aromaRequest) throws BadRequestException {

        if (aromaRequest.getId() != null) throw new BadRequestException("Cannot create a new aroma with an existing id : " + aromaRequest.getId());

        String aromaExists = aromaExists(aromaRequest, aromaRequest.getBrand());

        String brandId = aromaRequest.getBrand().getId();
    
        if(brandId != null) {
            if(!brandRepo.existsById(brandId)) throw new BadRequestException("Id provided for brand does not exist");

            return aromaRepo.save(aromaRequest);
        }
         


        Brand newSavedBrand = brandService.saveNewBrand(aromaRequest.getBrand());
        Aroma newAroma = new Aroma(aromaRequest.getName(), newSavedBrand);

        Aroma savedAroma = aromaRepo.save(newAroma);
        brandService.addAromaToBrand(newAroma, newSavedBrand);
        
        return savedAroma;
    }

    public String aromaExists(Aroma aroma, Brand brand){

        Criteria nameCriteria = new Criteria().where("name").is(aroma.getName()).and("aroma.brand.name").is(brand.getName());

        Query query = new Query();
        query.addCriteria(nameCriteria);
        Aroma result = mongoTemplate.findOne(query, Aroma.class);
        return Optional.ofNullable(result).orElse(new Aroma()).getId();
    }
}
