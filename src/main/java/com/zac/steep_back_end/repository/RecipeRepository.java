package com.zac.steep_back_end.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zac.steep_back_end.model.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

 
}
