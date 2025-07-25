/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;

/**
 *
 * @author moise
 */
public class ShirtMapper extends ClothingMapper {

    @Override
    public ClothingResponseDTO EntityToDTO(Clothing clothing) {
        Shirt shirt = (Shirt) clothing;
        
        return new ShirtResponseDTO(shirt.getId(), shirt.getColor(), shirt.getPrice(), shirt.getQuantity(), shirt.getFabric(),
                        shirt.getBrand(), shirt.getStyle(), shirt.getGender(), shirt.getPattern(), shirt.getPocket(),
                        shirt.getClosureType(), shirt.getClothingType(), shirt.getSleeve(), shirt.getCollar(), shirt.getSize());
    }
}
