/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.CutType;
import com.mycompany.clothing.store.manager.domain.enums.DetailPanty;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.LiningType;
import com.mycompany.clothing.store.manager.domain.enums.Size;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 *
 * @author moise
 */
@Entity
public class Panty extends Clothing {

    @Column(name = "TAMANHO")
    private Size size;

    @Column(name = "CINTURA")
    private WaistType waist;

    @Column(nullable = true, name = "FORRO")
    private LiningType lining;

    @Column(nullable = true, name = "CORTE")
    private CutType cut;

    @Column(nullable = true, name = "DETALHE")
    private DetailPanty detail;

    @Column(nullable = true, name = "estilo")
    private String style;

    public Panty() {
    }

    public Panty(Size size, WaistType waist, LiningType lining, CutType cut, DetailPanty detail, String color, Double price, Integer quantity,
            ClothingType clothingType, String fabric, String brand, Gender gender, String pattern, String style) {
        super(color, price, quantity, clothingType, fabric, brand, gender, pattern);
        this.size = size;
        this.waist = waist;
        this.lining = lining;
        this.cut = cut;
        this.detail = detail;
        this.style = style;
    }

    @Override
    public String toString() {
        return super.toString() + "Pantie{" + "size=" + size + ", waist=" + waist + ", lining=" + lining + ", cut=" + cut + ", details=" + detail + ", style="+ style + '}';
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public WaistType getWaist() {
        return waist;
    }

    public void setWaist(WaistType waist) {
        this.waist = waist;
    }

    public LiningType getLining() {
        return lining;
    }

    public void setLining(LiningType lining) {
        this.lining = lining;
    }

    public CutType getCut() {
        return cut;
    }

    public void setCut(CutType cut) {
        this.cut = cut;
    }

    public DetailPanty getDetail() {
        return detail;
    }

    public void setDetails(DetailPanty detail) {
        this.detail = detail;
    }

}
