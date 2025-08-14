/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service.mapper;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Panty;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantyRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantyResponseDTO;
import com.mycompany.clothing.store.manager.interfaces.IClothingMapper;

/**
 *
 * @author moise
 */
public class PantyMapper implements IClothingMapper {

    @Override
    public PantyResponseDTO EntityToResponseDTO(Clothing clothing) throws Exception {
        if (clothing instanceof Panty panty) {
            return new PantyResponseDTO(panty.getId(), panty.getColor(), panty.getQuantity(), panty.getPrice(),
                    panty.getFabric(), panty.getBrand(), panty.getStyle(), panty.getGender(), panty.getPattern(), panty.getClothingType(),
                    panty.getCut(), panty.getDetails(), panty.getLining(), panty.getSize(), panty.getWaist());
        }
        throw new IllegalArgumentException("Passe um Panty");
    }

    @Override
    public Panty RequestDTOToEntity(ClothingRequestDTO dto) throws Exception {
        if (dto instanceof PantyRequestDTO pantyDTO) {
            return new Panty(pantyDTO.size(), pantyDTO.waist(), pantyDTO.lining(), pantyDTO.cut(), pantyDTO.details(),
                    pantyDTO.color(), pantyDTO.price(), pantyDTO.quantity(), pantyDTO.clothingType(), pantyDTO.fabric(),
                    pantyDTO.brand(), pantyDTO.gender(), pantyDTO.pattern(), pantyDTO.style());
        }
        throw new IllegalArgumentException("Passe um PantyRequestDTO");
    }
}
