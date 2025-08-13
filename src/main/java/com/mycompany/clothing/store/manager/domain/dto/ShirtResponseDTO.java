/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain.dto;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.Size;

/**
 *
 * @author moise
 */
public record ShirtResponseDTO(Integer id, String color, Double price, Integer quantity, String fabric,
                              String brand, String style, Gender gender, String pattern, 
                              Integer pocket, String closureType, ClothingType clothingType,
                              Integer sleeve, Integer collar, Size size) implements ClothingResponseDTO {
}
