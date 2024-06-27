package com.zac.steep_back_end.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zac.steep_back_end.repository.AromaRepository;
import com.zac.steep_back_end.repository.RecipeRepository;
import com.zac.steep_back_end.service.RecipeService;




@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api")
public class Router {
    
    @GetMapping("/test")
    public String sayHello(){
        return "Hello";
    }
    
    @Autowired
    RecipeRepository recipeRepo;

    @Autowired
    AromaRepository aromaRepo;

    @Autowired
    RecipeService recipeService;

    

    @GetMapping("/aromas")
    public ResponseEntity< Map<String, Object>> getMethodName() {
        Map<String, Object> results = new HashMap<>();
        int status;
        
        try{
            results.put("data", aromaRepo.findAll());
            results.put("success", true);
            status = 200;
        } catch (Exception error) {
            status = 400;
            results.put("success", "false");
            results.put("error", error.getMessage());
        }

        return ResponseEntity.status(status).body(results);
    }
        
}
