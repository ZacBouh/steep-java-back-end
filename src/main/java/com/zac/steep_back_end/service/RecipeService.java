package com.zac.steep_back_end.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zac.steep_back_end.dto.IngredientClientDTO;
import com.zac.steep_back_end.dto.RecipeClientDTO;
import com.zac.steep_back_end.dto.RecipeMapper;
import com.zac.steep_back_end.model.Aroma;
import com.zac.steep_back_end.model.Brand;
import com.zac.steep_back_end.model.Recipe;
import com.zac.steep_back_end.repository.AromaRepository;
import com.zac.steep_back_end.repository.RecipeRepository;


@Service
public class RecipeService {
    Gson gson = new Gson();
    @Autowired
    RecipeRepository recipeRepo;
    @Autowired
    AromaRepository aromaRepo;
    @Autowired
    AromaService aromaService;
    
    @Transactional
    public Recipe saveRecipe(RecipeClientDTO recipeDto) throws BadRequestException, Exception{
        System.out.println("INFO saveRecipe() called");
        List<IngredientClientDTO> ingredients = recipeDto.getAromas();
        
        System.out.println("list of ingredients");
        System.out.println(gson.toJson(ingredients));

        List<String> existingAromasIds = ingredients.stream()
        .map(IngredientClientDTO::getAromaId)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

         if (aromaRepo.findAllById(existingAromasIds).size() < existingAromasIds.size() ){
            throw new BadRequestException("The list of ingredient includes ids that do not exist in db");
         } 

         List<Aroma> newAromas = ingredients.stream()
         .map(ingredient -> {
            if (ingredient.getAromaId() != null) return null;
            Brand brand = ingredient.getBrandId() == null ? new Brand(ingredient.getAromaBrand()) : new Brand(ingredient.getAromaBrand(), ingredient.getBrandId());
            return new Aroma(ingredient.getAromaName(), brand);
         })
         .filter(Objects::nonNull)
         .collect(Collectors.toList());

         System.out.println("list of new aromas");
         System.out.println(gson.toJson(newAromas));

             List<Aroma> savedAromas =  newAromas.stream()
             .map(aroma ->{
                try {
                    Aroma saved = aromaService.saveAroma(aroma);
                    System.out.println("saved new aroma");
                    System.out.println(saved);
                    return saved;
                    
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
             })
             .collect(Collectors.toList());
            
         
             savedAromas.stream()
             .forEach(aroma -> {
                System.out.println("this is called too");
                 String identifier = aroma.getName() + aroma.getBrand().getName();
                 for (IngredientClientDTO ingredient : recipeDto.getAromas()){
                    String ingredientIdentifier =  ingredient.getAromaName()+ingredient.getAromaBrand();
                    
                    System.out.println("aromaIdentifier and ingredientIdentifier");
                    System.out.println(identifier+" "+ ingredientIdentifier);
                    System.out.println(identifier.equals(ingredientIdentifier));
                    if(identifier.equals(ingredientIdentifier)){
                        System.out.println("this condition is true");
                         ingredient.setAromaId(aroma.getId());
                        };
                    }
                });
            
            System.out.println("Saved Aromas : ");
            System.out.println(gson.toJson(savedAromas));
        
            System.out.println("RecipeDTO now is ");
            System.out.println(gson.toJson(recipeDto));

        Recipe recipe = RecipeMapper.getRecipeFromDTO(recipeDto);


        recipe = recipeRepo.save(recipe);
        return recipe;
    }
}