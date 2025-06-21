/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

/**
 *
 * @author moise
 */
public class Shirt extends Clothing {
    Boolean sleeve;
    Boolean collar;
    Character size;
    
    public Shirt(String color, Double price, Integer quantity, String brand, String gender, ClothingType clothingType, Character size) {
        super(color, price, quantity, brand, gender, clothingType);
        this.size = size;
    }
    
    public Shirt(String color, Double price, Integer quantity, String brand, String gender, ClothingType clothingType, Character size, Boolean sleeve, Boolean collar) {
        this(color, price, quantity, brand, gender, clothingType, size);
        this.sleeve = sleeve;
        this.collar = collar;
    }

    public Shirt(ClothingType clothingType, Character size, String color, Double price, Integer quantity, String fabric, String brand, String style, String gender, String pattern, Integer pocket, String closureType) {
        super(color, price, quantity, clothingType, fabric, brand, style, gender, pattern, pocket, closureType);
        this.size = size;
    }

    public Shirt(ClothingType clothingType, Boolean sleeve, Boolean collar, Character size, String color, Double price, Integer quantity, String fabric, String brand, String style, String gender, String pattern, Integer pocket, String closureType) {
        this(clothingType, size, color, price, quantity, fabric, brand, style, gender, pattern, pocket, closureType);
        this.sleeve = sleeve;
        this.collar = collar;
    }

    public Boolean getSleeve() {
        return sleeve;
    }

    public void setSleeve(Boolean sleeve) {
        this.sleeve = sleeve;
    }

    public Boolean getCollar() {
        return collar;
    }

    public void setCollar(Boolean collar) {
        this.collar = collar;
    }

    public Character getSize() {
        return size;
    }

    public void setSize(Character size) {
        this.size = size;
    }
    
    
}
