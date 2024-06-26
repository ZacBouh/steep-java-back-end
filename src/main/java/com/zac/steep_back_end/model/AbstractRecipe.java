package com.zac.steep_back_end.model;

import java.util.List;
import org.springframework.data.annotation.Id;

public abstract class AbstractRecipe <T> {
    @Id
    private String id;
    private String name;
    private String pgVgRatio;
    private int nicotinRatio;
    private List<T> aromas;
    
    public AbstractRecipe() {
    }

    

    public AbstractRecipe(String name, String pgVgRatio, int nicotinRatio, List<T> aromas) {
        this.name = name;
        this.pgVgRatio = pgVgRatio;
        this.nicotinRatio = nicotinRatio;
        this.aromas = aromas;
    }



    public AbstractRecipe(String id, String name, String pgVgRatio, int nicotinRatio, List<T> aromas) {
        this.id = id;
        this.name = name;
        this.pgVgRatio = pgVgRatio;
        this.nicotinRatio = nicotinRatio;
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



    public String getPgVgRatio() {
        return pgVgRatio;
    }



    public void setPgVgRatio(String pgVgRatio) {
        this.pgVgRatio = pgVgRatio;
    }



    public int getNicotinRatio() {
        return nicotinRatio;
    }



    public void setNicotinRatio(int nicotinRatio) {
        this.nicotinRatio = nicotinRatio;
    }



    public List<T> getAromas() {
        return aromas;
    }



    public void setAromas(List<T> aromas) {
        this.aromas = aromas;
    }

    
    
}
