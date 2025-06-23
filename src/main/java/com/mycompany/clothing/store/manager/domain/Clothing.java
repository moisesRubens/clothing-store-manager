/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 *
 * @author moise
 */
@Entity
@Table(name="ROUPA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Clothing {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)       
    Integer id;
    
    @Column(name="COR")
    String color;
    
    @Column(name="PRECO")
    Double price;
    
    @Column(name="QUANTIDADE")
    Integer quantity;
    
    @Column(name="TECIDO", nullable=true)
    String fabric;
    
    @Column(name="MARCA")
    String brand;
    
    @Column(name="MODELO", nullable=true)
    String style;
    
    @Enumerated(EnumType.STRING)
    @Column(name="GENERO")
    Gender gender;
    
    @Column(name="ESTAMPA", nullable=true)
    String pattern;
    
    @Column(name="QUANT_BOLSOS", nullable=true)
    Integer pocket;
    
    @Column(name="TIPO_FECHAMENTO", nullable=true)
    String closureType;
    
    @Enumerated(EnumType.STRING)
    @Column(name="TIPO_ROUPA")
    ClothingType clothingType;

    public Clothing() {}

    public Clothing(String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand, String style, Gender gender, String pattern, Integer pocket, String closureType) {
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.gender = gender;
        this.clothingType = clothingType;
        this.fabric = fabric;
        this.style = style;
        this.pattern = pattern;
        this.pocket = pocket;
        this.closureType = closureType;
    }

    public Integer getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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

    public ClothingType getClothingType() {
        return clothingType;
    }

    public void setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
    }   
}
