/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 *
 * @author moise
 */
@Entity
@Table(name = "ROUPA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Clothing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    Integer id;

    @Column(name="COR")
    String color;

    @Column(name="VALOR")
    Double price;

    @Column(name="QUANTIDADE")
    Integer quantity;

    @Column(name="TECIDO", nullable = true)
    String fabric;

    @Column(name="MARCA")
    String brand;

    @Column(name="GENERO")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name="ESTAMPA", nullable = true)
    String pattern;

    @Column(name="TIPO DE ROUPA")
    @Enumerated(EnumType.STRING)
    ClothingType clothingType;

    public Clothing() {
    }

    public Clothing(String color, Double price, Integer quantity, ClothingType clothingType, String fabric, String brand, Gender gender, String pattern) {
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.gender = gender;
        this.clothingType = clothingType;
        this.fabric = fabric;
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Clothing{" + "id=" + id + ", color=" + color + ", price=" + price + ", quantity=" + quantity + ", fabric=" + fabric + ", brand=" + brand + ", gender=" + gender + ", pattern=" + pattern + ", clothingType=" + clothingType + '}';
    }

    public abstract String createQuery();

    protected abstract Boolean containsAttribute();

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

    public ClothingType getClothingType() {
        return clothingType;
    }

    public void setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
    }
}
