/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service.mapper;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.interfaces.IClothingMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author moise
 */
@Component
public class ShirtMapper implements IClothingMapper {

    @Override
    public ClothingResponseDTO EntityToResponseDTO(Clothing clothing) throws Exception {
        if (clothing instanceof Shirt shirt) {
            return new ShirtResponseDTO(shirt.getId(), shirt.getColor(), shirt.getPrice(), shirt.getQuantity(), shirt.getFabric(),
                    shirt.getBrand(), shirt.getStyle(), shirt.getGender(), shirt.getPattern(), shirt.getPocket(),
                    shirt.getClosureType(), shirt.getSleeve(), shirt.getCollar(), shirt.getSize());
        }
        throw new IllegalArgumentException("Passe uma instância Shirt");
    }

    @Override
    public Shirt RequestDTOToEntity(ClothingRequestDTO dto) throws Exception {
        if (dto instanceof ShirtRequestDTO shirtDTO) {
            return new Shirt(shirtDTO.color(), shirtDTO.price(), shirtDTO.quantity(),
                    shirtDTO.fabric(), shirtDTO.brand(), shirtDTO.style(),
                    shirtDTO.gender(), shirtDTO.pattern(), shirtDTO.pocket(), shirtDTO.closureType(),
                    shirtDTO.size(), shirtDTO.sleeve(), shirtDTO.collar());
        }
        throw new IllegalArgumentException("Passe uma instância ShirtRequestDTO");
    }
}
