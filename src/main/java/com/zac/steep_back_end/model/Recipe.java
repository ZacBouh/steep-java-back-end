package com.zac.steep_back_end.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document("recipes")
public class Recipe extends AbstractRecipe<Ingredient> {

        public Recipe(){
            super();
        }

        public Recipe(String name, String pgVgRatio, int nicotinRatio){
            super.setName(name);
            super.setPgVgRatio(pgVgRatio);
            super.setNicotinRatio(nicotinRatio);
        }

        public Recipe(String name, String pgVgRatio, int nicotinRatio, List<Ingredient> aromas) {
        super(name, pgVgRatio, nicotinRatio, aromas );
        
    }

    }
