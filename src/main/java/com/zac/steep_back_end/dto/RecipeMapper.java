package com.zac.steep_back_end.dto;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.zac.steep_back_end.model.Ingredient;
import com.zac.steep_back_end.model.Recipe;
import com.zac.steep_back_end.repository.AromaRepository;

public class RecipeMapper {

    @Autowired
    AromaRepository aromaRepo;

    public static Recipe getRecipeFromDTO(RecipeClientDTO recipeDTO ){

        
        Recipe recipe = new Recipe(recipeDTO.getName(), recipeDTO.getPgVgRatio(), recipeDTO.getNicotinRatio(), new ArrayList<Ingredient>());
        



        for (IngredientClientDTO ingredientDto : recipeDTO.getAromas()){
            Ingredient ingredient = new Ingredient();
            

            ingredient.setAromaId(ingredientDto.getAromaId());
            ingredient.setAromaProportion(ingredientDto.getAromaProportion());
            ingredient.setAromaQuantity(ingredientDto.getAromaQuantity());

            recipe.getAromas().add(ingredient);
        }

        return recipe;
    }
    
}
