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
    
    @Column(name="QUANT_BOLSOS", nullable=true)
    Integer pocket;
    
    @Column(name="TIPO_FECHAMENTO", nullable=true)
    String closureType;
   
    public Pant() {}

    public Pant(HemType hemType, WaistType waistType, PantieLengthType length, Integer size, String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand, String style, Gender gender, String pattern, Integer pocket, String closureType) {
        super(color, price, quantity, clothingType, fabric, brand, style, gender, pattern);
        this.hemType = hemType;
        this.waistType = waistType;
        this.length = length;
        this.size = size;
        this.pocket = pocket;
        this.closureType = closureType;
    }

    @Override
    public String toString() {
        return super.toString() + "Pant{" + "hemType=" + hemType + ", waistType=" + waistType + ", length=" + length + ", size=" + size + ", pocket=" + pocket + ", closureType=" + closureType + '}';
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
    
    
    
}
