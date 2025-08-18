/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service.mapper;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.HemType;
import com.mycompany.clothing.store.manager.domain.enums.PantLengthType;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import com.mycompany.clothing.store.manager.interfaces.IClothingMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author moise
 */
@Component
public class PantMapper implements IClothingMapper {

    @Override
    public PantResponseDTO EntityToResponseDTO(Clothing clothing) throws Exception {
        if (clothing instanceof Pant pant) {
            return new PantResponseDTO(pant.getId(), pant.getColor(), pant.getPrice(), pant.getQuantity(), pant.getFabric(), pant.getBrand(),
                    pant.getStyle(), pant.getGender(), pant.getPattern(), pant.getPocket(), pant.getClosureType(), pant.getSize(), pant.getLength(),
                    pant.getWaistType(), pant.getHemType());
        }
        throw new IllegalArgumentException("Passe no parâmetro uma instância de Pant");
    }

    @Override
    public Pant RequestDTOToEntity(ClothingRequestDTO dto) throws Exception {
        if (dto instanceof PantRequestDTO pantData) {
            return new Pant(pantData.hemType(), pantData.waistType(), pantData.length(), pantData.size(),
                    pantData.color(), pantData.price(), pantData.quantity(), pantData.fabric(),
                    pantData.brand(), pantData.style(), pantData.gender(), pantData.pattern(), pantData.pocket(), pantData.closureType());
        }
        throw new IllegalArgumentException("Passe no parâmetro uma instância de PantRequestDTO");
    }

}
