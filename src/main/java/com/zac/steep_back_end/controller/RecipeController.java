package com.zac.steep_back_end.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.zac.steep_back_end.dto.RecipeClientDTO;
import com.zac.steep_back_end.model.Recipe;
import com.zac.steep_back_end.repository.RecipeRepository;
import com.zac.steep_back_end.service.RecipeService;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:5173/")
public class RecipeController {
    
    @Autowired
    RecipeRepository recipeRepo;
    @Autowired 
    RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Recipe> receivedRecipe(@RequestBody RecipeClientDTO request){
        Gson gson = new Gson();
        
        System.out.println("Received the following recipe : " );
        System.out.println("[CREATE] Recipe creation with values ");
        System.out.println(gson.toJson(request));
        
        try {
            
            Recipe savedRecipe = recipeService.saveRecipe(request);
            return ResponseEntity.ok(savedRecipe);

        } catch (Exception e) {
            System.out.println("[ERROR] creating the recipe");
            System.out.println(e);
            e.printStackTrace(System.out);

            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        System.out.println("Got request for All recipes");
        return recipeRepo.findAll();
        
    }
    
    @DeleteMapping("{recipeId}")
    public ResponseEntity<Map<String, String>> deleteRecipe (@PathVariable String recipeId){
        Map<String, String> responseBody = new HashMap<>();
        int status;

        try {
            recipeRepo.deleteById(recipeId);
            responseBody.put("message", "sucessful");
            status = 201;

        } catch (Exception e) {
            status = 500;
            responseBody.put("success", "false");
            responseBody.put("error", e.getMessage()); 
        } 
        return ResponseEntity.status(status).body(responseBody);
    }
}
