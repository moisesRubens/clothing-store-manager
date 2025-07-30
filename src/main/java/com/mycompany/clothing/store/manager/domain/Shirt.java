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
import java.util.EnumSet;

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
    
    @Column(name="MODELO", nullable=true)
    String style;

    public Shirt() {}

    public Shirt(String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand,
                 String style, Gender gender, String pattern, Integer pocket, String closureType, ShirtSize size, Integer sleeve, Integer collar) {
        super(color, price, quantity, clothingType, fabric, brand, gender, pattern);
        this.size = size;
        this.sleeve = sleeve;
        this.collar = collar;
        this.pocket = pocket;
        this.closureType = closureType;
        this.style = style;
    }

    @Override
    public String toString() {
        return super.toString() + "Shirt{" + "sleeve=" + sleeve + ", collar=" + collar + ", size=" + size + ", pocket=" + pocket + ", closureType=" + closureType + ", style=" + style + '}';
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String createQuery( ) {
        
        String query = "SELECT s FROM Shirt s WHERE";

        if (!containsAttribute()) {
            query += " 1=0";
            return query;
        }

        query += " 1=1";
        if (this.getSleeve() != -1) {
            query += " AND s.sleeve <= :sleeve";
        }
        if (this.getCollar() != -1) {
            query += " AND s.collar <= :collar";
        }
        if (this.getPrice() != -1) {
            query += " AND s.price <= :price";
        }
        if (this.getQuantity() != -1) {
            query += " AND s.quantity >= :quantity";
        }
        if (this.getPocket() != -1) {
            query += " AND s.pocket = :pocket";
        }
        if (this.getColor() != null) {
            query += " AND s.color LIKE :color";
        }
        if (this.getFabric() != null) {
            query += " AND s.fabric LIKE :fabric";
        }
        if (this.getBrand() != null) {
            query += " AND s.brand LIKE :brand";
        }
        if (this.getStyle() != null) {
            query += " AND s.style LIKE :style";
        }
        if (this.getPattern() != null) {
            query += " AND s.pattern LIKE :pattern";
        }
        if (this.getClosureType() != null) {
            query += " AND s.closureType LIKE :closureType";
        }
        if (EnumSet.allOf(ShirtSize.class).contains(this.getSize())) {
            query += " AND s.size = :size";
        }
        if (EnumSet.allOf(Gender.class).contains(this.getGender())) {
            query += " AND s.gender = :gender";
        }
        return query;
    }
    
    @Override
    public Boolean containsAttribute() {
        return (!this.getColor().isBlank() || !this.getBrand().isBlank() || !this.getPattern().isBlank()
                || !this.getFabric().isBlank() || EnumSet.allOf(Gender.class).contains(this.getGender())
                || !this.getStyle().isBlank() || this.getQuantity() != -1 || this.getPrice() != -1
                || EnumSet.allOf(ShirtSize.class).contains(this.getSize()) || this.getCollar() != -1
                || this.getSleeve() != -1 || !this.getClosureType().isBlank() || this.getPocket() != -1);
    }
}
