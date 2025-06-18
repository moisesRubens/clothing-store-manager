/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

/**
 *
 * @author moise
 */
public class Shirt extends Clothing {
    private Integer id;
    private Character size;
    
    public Shirt(String color, Double price, Integer quantity, Character size) {
        super(color, price, quantity);
        this.size = size;
    }

    public Character getSize() {
        return size;
    }

    public void setSize(Character size) {
        this.size = size;
    }
    
    
}
