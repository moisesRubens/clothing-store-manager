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

/**
 *
 * @author moise
 */
public class Pantie extends Clothing {

    public enum PantyStyle {
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

    public Pantie(Character size, LiningType liningType, SideType sideType, WaistType waistType, String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand, String style, Gender gender, String pattern) {
        super(color, price, quantity, clothingType, fabric, brand, style, gender, pattern);
        this.size = size;
        this.liningType = liningType;
        this.sideType = sideType;
        this.waistType = waistType;
    }

    public Pantie() {}

    @Override
    public String toString() {
        return super.toString() + "Pantie{" + "size=" + size + ", liningType=" + liningType + ", sideType=" + sideType + ", waistType=" + waistType + '}';
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
