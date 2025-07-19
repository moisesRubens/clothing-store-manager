/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.HemType;
import com.mycompany.clothing.store.manager.domain.enums.PantieLengthType;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
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
@Table(name="CALCA")
public class Pant extends Clothing {
    @Column(name="TIPO_BARRA", nullable=true)
    @Enumerated(EnumType.STRING)
    HemType hemType;
    
    @Column(name="TIPO_CINTURA", nullable=true)
    @Enumerated(EnumType.STRING)        
    WaistType waistType;
    
    @Column(name="COMPRIMENTO")
    @Enumerated(EnumType.STRING)
    PantieLengthType length;
    
    @Column(name="TAMANHO")
    Integer size;
   
    public Pant() {}

    public Pant(HemType hemType, WaistType waistType, PantieLengthType length, Integer size, String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand, String style, Gender gender, String pattern, Integer pocket, String closureType) {
        super(color, price, quantity, clothingType, fabric, brand, style, gender, pattern, pocket, closureType);
        this.hemType = hemType;
        this.waistType = waistType;
        this.length = length;
        this.size = size;
    }

    public HemType getHemType() {
        return hemType;
    }

    public WaistType getWaistType() {
        return waistType;
    }

    public PantieLengthType getLength() {
        return length;
    }

    public Integer getSize() {
        return size;
    }

    public void setHemType(HemType hemType) {
        this.hemType = hemType;
    }

    public void setWaistType(WaistType waistType) {
        this.waistType = waistType;
    }

    public void setLength(PantieLengthType length) {
        this.length = length;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    
    
}
