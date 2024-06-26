package com.zac.steep_back_end.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.mongodb.lang.NonNull;

@Document("brands")
public class Brand {

    @Id
    private String id;
    @NonNull
    private String name;
    @DocumentReference
    private List<Aroma> aromas;

    public Brand(){}

    public Brand(String name){
        this.name = name;
    }
    public Brand(String name, String id){
        this.name = name;
        this.id = id;
    }
    public Brand(String name, String id, List<Aroma> aromas){
        this.name = name;
        this.id = id;
        this.aromas = aromas;
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

    public List<Aroma> getAromas() {
        return aromas;
    }

    public void setAromas(List<Aroma> aromas) {
        this.aromas = aromas;
    }
}
