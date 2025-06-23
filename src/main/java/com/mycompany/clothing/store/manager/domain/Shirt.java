/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

/**
 *
 * @author moise
 */
@Entity
@Table(name="CAMISA")
public class Shirt extends Clothing {
    
    @Column(nullable=true)
    Boolean sleeve;
    
    @Column(nullable=true)
    Boolean collar;
    
    @Enumerated(EnumType.STRING)
    @Column
    ShirtSize size;

    public Shirt() {}

    public Shirt(String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand,
                 String style, Gender gender, String pattern, Integer pocket, String closureType, ShirtSize size, Boolean sleeve, Boolean collar) {
        super(color, price, quantity, clothingType, fabric, brand, style, gender, pattern, pocket, closureType);
        this.size = size;
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

    public ShirtSize getSize() {
        return size;
    }

    public void setSize(ShirtSize size) {
        this.size = size;
    }
    
    
}
