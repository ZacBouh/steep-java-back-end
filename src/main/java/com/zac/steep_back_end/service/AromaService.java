package com.zac.steep_back_end.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public Aroma saveAroma(Aroma aromaRequest) throws BadRequestException {

        if (aromaRequest.getId() != null) throw new BadRequestException("Cannot create a new aroma with an existing id : " + aromaRequest.getId());

        String brandId = aromaRequest.getBrand().getId();
    
        if(brandId != null) {
            if(!brandRepo.existsById(brandId)) throw new BadRequestException("Id provided for brand does not exist");

            return aromaRepo.save(aromaRequest);
        }

        Brand newSavedBrand = brandRepo.save(aromaRequest.getBrand());
        Aroma newAroma = new Aroma(aromaRequest.getName(), newSavedBrand);

        return aromaRepo.save(newAroma);
    }
}
