/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain.dto;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;

/**
 *
 * @author moise
 */
public interface ClothingResponseDTO {
    Integer id();
    String color();
    Integer quantity();
    String fabric();
    String brand(); 
    String style();
    Gender gender();
    String pattern();
    ClothingType clothingType(); 
}
