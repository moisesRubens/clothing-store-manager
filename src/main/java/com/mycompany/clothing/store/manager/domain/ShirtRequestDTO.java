/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;

/**
 *
 * @author moise
 */
public record ShirtRequestDTO(String color, Double price, Integer quantity, String fabric,
                              String brand, String style, Gender gender, String pattern, 
                              Integer pocket, String closureType, ClothingType clothingType,
                              Boolean sleeve, Boolean collar, Character size) implements ClothingRequestDTO {
}
