/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain.dto;

import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.CutType;
import com.mycompany.clothing.store.manager.domain.enums.DetailPanty;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.LiningType;
import com.mycompany.clothing.store.manager.domain.enums.Size;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import jakarta.persistence.Column;

/**
 *
 * @author moise
 */
public record PantyResponseDTO(
    @Column(name="ID")
    Integer id,
    
    @Column(name="COR")
    String color,
    Integer quantity,
    Double price,
    String fabric,
    String brand,
    String style,
    Gender gender,
    String pattern,
    ClothingType clothingType, CutType cut,
    DetailPanty detail, LiningType lining, 
    Size size, WaistType waist) implements ClothingResponseDTO{

}
