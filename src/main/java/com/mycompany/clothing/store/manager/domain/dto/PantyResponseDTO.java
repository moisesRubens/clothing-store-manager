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
        Integer id,
        String color,
        Double price,
        Integer quantity,
        String fabric,
        String brand,
        Gender gender,
        String pattern,
        Size size,
        WaistType waist,
        LiningType lining,
        CutType cut,
        DetailPanty detail,
        String style) implements ClothingResponseDTO {

}
