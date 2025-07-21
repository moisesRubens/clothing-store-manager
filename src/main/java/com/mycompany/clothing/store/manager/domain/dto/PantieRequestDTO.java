/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain.dto;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.LiningType;
import com.mycompany.clothing.store.manager.domain.enums.SideType;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;

/**
 *
 * @author moise
 */
public record PantieRequestDTO(String color, Integer quantity, Double price,
    String fabric, String brand, String style,
    Gender gender, String pattern, ClothingType clothingType,
    Character size, LiningType liningType, WaistType waistType, SideType sideType) implements ClothingRequestDTO {
}
