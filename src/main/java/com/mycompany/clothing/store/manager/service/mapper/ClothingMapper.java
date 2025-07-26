/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service.mapper;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;

/**
 *
 * @author moise
 */
public abstract class ClothingMapper {

    public static Clothing DTOToEntity(ClothingRequestDTO data) {
        switch (data) {
            case ShirtRequestDTO shirtData -> {
                Shirt shirt = new Shirt(shirtData.color(), shirtData.price(), shirtData.quantity(), shirtData.clothingType(), shirtData.fabric(),
                        shirtData.brand(), shirtData.style(), shirtData.gender(), shirtData.pattern(), shirtData.pocket(),
                        shirtData.closureType(), shirtData.size(), shirtData.sleeve(), shirtData.collar());
                
                return shirt;
            }
            case PantRequestDTO pantieData -> {
                Pant pantie = new Pant(pantieData.hemType(), pantieData.waistType(), pantieData.length(), pantieData.size(), pantieData.color(),
                        pantieData.price(), pantieData.quantity(), pantieData.clothingType(), pantieData.fabric(), pantieData.brand(), pantieData.style(),
                        pantieData.gender(), pantieData.pattern(), pantieData.pocket(), pantieData.closureType());
                
                return pantie;
            }
            default -> {
                return null;
            }
        }
    }
    
    public abstract ClothingResponseDTO EntityToResponseDTO(Clothing clothing);
    
    public abstract Clothing RequestDTOToEntity(ClothingRequestDTO dto);
}
