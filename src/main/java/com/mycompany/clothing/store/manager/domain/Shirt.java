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
    
    @Column(name="MANGA", nullable=true)
    Integer sleeve;
    
    @Column(name="GOLA", nullable=true)
    Integer collar;
    
    @Enumerated(EnumType.STRING)
    @Column(name="TAMANHO")
    ShirtSize size;
    
    @Column(name="QUANT_BOLSOS", nullable=true)
    Integer pocket;
    
    @Column(name="TIPO_FECHAMENTO", nullable=true)
    String closureType;

    public Shirt() {}

    public Shirt(String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand,
                 String style, Gender gender, String pattern, Integer pocket, String closureType, ShirtSize size, Integer sleeve, Integer collar) {
        super(color, price, quantity, clothingType, fabric, brand, style, gender, pattern);
        this.size = size;
        this.sleeve = sleeve;
        this.collar = collar;
        this.pocket = pocket;
        this.closureType = closureType;
    }

    @Override
    public String toString() {
        return super.toString() + "Shirt{" + "sleeve=" + sleeve + ", collar=" + collar + ", size=" + size + ", pocket=" + pocket + ", closureType=" + closureType + '}';
    }
    
    public Integer getPocket() {
        return pocket;
    }

    public void setPocket(Integer pocket) {
        this.pocket = pocket;
    }

    public String getClosureType() {
        return closureType;
    }

    public void setClosureType(String closureType) {
        this.closureType = closureType;
    }
    
    public Integer getSleeve() {
        return sleeve;
    }

    public void setSleeve(Integer sleeve) {
        this.sleeve = sleeve;
    }

    public Integer getCollar() {
        return collar;
    }

    public void setCollar(Integer collar) {
        this.collar = collar;
    }

    public ShirtSize getSize() {
        return size;
    }

    public void setSize(ShirtSize size) {
        this.size = size;
    }
    
    
}
