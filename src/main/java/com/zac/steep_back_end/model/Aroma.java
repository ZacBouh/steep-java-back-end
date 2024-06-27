package com.zac.steep_back_end.model;

import java.util.List;
import java.util.ArrayList;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document
public class Aroma {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Brand brand;
    private List<String> recipes;

    
    public Aroma(){
        this.id = "undefined";
        this.name = "undefined";
        this.brand = new Brand();
        this.recipes = new ArrayList<>();
    }
    
    
    
    public Aroma(String name, Brand brand) {
        this.name = name;
        this.brand = brand;
    }
    public Aroma(String name, Brand brand, String id) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }
    
    public Aroma(String name, Brand brand, String id,  List<String> recipes) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.recipes = recipes;
    }



    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

}
