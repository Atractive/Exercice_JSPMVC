package com.mycompany.exercice_jspmvc;

public class DiscountCodeEntity {
    
    private char code;
    private float taux;
    
    public DiscountCodeEntity(char c, float t){
        this.code = c;
        this.taux = t;
    }

    DiscountCodeEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public char getCode(){
        return code;
    }
    
    public float getTaux(){
        return taux;
    }
    
}
