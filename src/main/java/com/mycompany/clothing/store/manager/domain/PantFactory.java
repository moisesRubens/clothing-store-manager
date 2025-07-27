/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.domain;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;

/**
 *
 * @author moise
 */
public class PantFactory implements IClothingFactory {

    @Override
    public Clothing createClothing(ClothingRequestDTO dto) {
        PantRequestDTO pant = (PantRequestDTO) dto;

        return new Pant(pant.hemType(), pant.waistType(), pant.length(), pant.size(), pant.color(),
                pant.price(), pant.quantity(), pant.clothingType(), pant.fabric(), pant.brand(),
                pant.style(), pant.gender(), pant.pattern(), pant.pocket(), pant.closureType());
    }

}
