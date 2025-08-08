/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.HemType;
import com.mycompany.clothing.store.manager.domain.enums.PantLengthType;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.EnumSet;

/**
 *
 * @author moise
 */
@Entity
public class Pant extends Clothing {
    @Column(name="TIPO_BARRA", nullable=true)
    @Enumerated(EnumType.STRING)
    HemType hemType;
    
    @Column(name="TIPO_CINTURA", nullable=true)
    @Enumerated(EnumType.STRING)        
    WaistType waistType;
    
    @Column(name="COMPRIMENTO")
    @Enumerated(EnumType.STRING)
    PantLengthType length;
    
    @Column(name="TAMANHO")
    Integer size;
    
    @Column(name="QUANT_BOLSOS", nullable=true)
    Integer pocket;
    
    @Column(name="TIPO_FECHAMENTO", nullable=true)
    String closureType;
    
    @Column(name="MODELO", nullable=true)
    String style;
   
    public Pant() {}

    public Pant(HemType hemType, WaistType waistType, PantLengthType length, Integer size, String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand, String style, Gender gender, String pattern, Integer pocket, String closureType) {
        super(color, price, quantity, clothingType, fabric, brand, gender, pattern);
        this.hemType = hemType;
        this.waistType = waistType;
        this.length = length;
        this.size = size;
        this.pocket = pocket;
        this.closureType = closureType;
        this.style = style;
    }

    @Override
    public String toString() {
        return super.toString() + "Pant{" + "hemType=" + hemType + ", waistType=" + waistType + ", length=" + length + ", size=" + size + ", pocket=" + pocket + ", closureType=" + closureType + ", style=" + style + '}';
    }

    public HemType getHemType() {
        return hemType;
    }

    public WaistType getWaistType() {
        return waistType;
    }

    public PantLengthType getLength() {
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

    public void setLength(PantLengthType length) {
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String createQuery() {
        String query = "SELECT p FROM Pant p WHERE";

        if (!containsAttribute()) {
            query += " 1=0";
            return query;
        }
        
        query += " 1=1";
        if (this.getSize()!= -1) {
            query += " AND p.size = :size";
        }
        if (this.getPrice() != -1) {
            query += " AND p.price <= :price";
        }
        if (this.getQuantity() != -1) {
            query += " AND p.quantity >= :quantity";
        }
        if (this.getPocket() != -1) {
            query += " AND p.pocket = :pocket";
        }
        if (!this.getColor().isBlank()) {
            query += " AND p.color LIKE :color";
        }
        if (!this.getFabric().isBlank()) {
            query += " AND p.fabric LIKE :fabric";
        }
        if (!this.getBrand().isBlank()) {
            query += " AND p.brand LIKE :brand";
        }
        if (!this.getStyle().isBlank()) {
            query += " AND p.style LIKE :style";
        }
        if (!this.getPattern().isBlank()) {
            query += " AND p.pattern LIKE :pattern";
        }
        if (!this.getClosureType().isBlank()) {
            query += " AND p.closureType LIKE :closureType";
        }
        if (EnumSet.allOf(PantLengthType.class).contains(this.getLength())) {
            query += " AND p.length = :length";
        }
        if (EnumSet.allOf(Gender.class).contains(this.getGender())) {
            query += " AND p.gender = :gender";
        }
        if (EnumSet.allOf(HemType.class).contains(this.getHemType())) {
            query += " AND p.hemType = :hemType";
        }
        if (EnumSet.allOf(WaistType.class).contains(this.getWaistType())) {
            query += " AND p.waistType= :waistType";
        }
        return query;
    }

    @Override
    protected Boolean containsAttribute() {
        return (!this.getColor().isBlank() || !this.getBrand().isBlank() || !this.getPattern().isBlank()
                || !this.getFabric().isBlank() || EnumSet.allOf(Gender.class).contains(this.getGender())
                || !this.getStyle().isBlank() || this.getQuantity() != -1 || this.getPrice() != -1
                || EnumSet.allOf(HemType.class).contains(this.getHemType()) || this.getSize() != -1
                || EnumSet.allOf(WaistType.class).contains(this.getWaistType()) || this.getPocket() != -1
                || EnumSet.allOf(PantLengthType.class).contains(this.getLength()) || !this.getClosureType().isBlank());
    }
    
    
    
}
