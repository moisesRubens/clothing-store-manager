/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.LiningType;
import com.mycompany.clothing.store.manager.domain.enums.SideType;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import jakarta.persistence.Column;

/**
 *
 * @author moise
 */
public class Pantie extends Clothing {

    public enum PantieStyle {
        TRADITIONAL, 
        THONG,
        TANGA,
        BIKINI,
        HIGH_WAIST,
        SEAMLESS,
        BOYSHORT,
        RETRO
    }
    
    private Character size;
    private LiningType liningType;
    private SideType sideType;
    private WaistType waistType;
    
    @Column(name="MODELO", nullable=true)
    PantieStyle style;

    public Pantie(Character size, LiningType liningType, SideType sideType, WaistType waistType, String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand, Gender gender, String pattern, PantieStyle style) {
        super(color, price, quantity, clothingType, fabric, brand, gender, pattern);
        this.size = size;
        this.liningType = liningType;
        this.sideType = sideType;
        this.waistType = waistType;
        this.style = style;
    }

    public Pantie() {}

    @Override
    public String toString() {
        return super.toString() + "Pantie{" + "size=" + size + ", liningType=" + liningType + ", sideType=" + sideType + ", waistType=" + waistType + '}';
    }

    public PantieStyle getStyle() {
        return style;
    }

    public void setStyle(PantieStyle style) {
        this.style = style;
    }
    
    public Character getSize() {
        return size;
    }

    public void setSize(Character size) {
        this.size = size;
    }

    public LiningType getLiningType() {
        return liningType;
    }

    public void setLiningType(LiningType liningType) {
        this.liningType = liningType;
    }

    public SideType getSideType() {
        return sideType;
    }

    public void setSideType(SideType sideType) {
        this.sideType = sideType;
    }

    public WaistType getWaistType() {
        return waistType;
    }

    public void setWaistType(WaistType waistType) {
        this.waistType = waistType;
    }
    
    
    
}
