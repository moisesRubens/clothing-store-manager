/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.interfaces;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;

/**
 *
 * @author moise
 */
public interface IClothingMapper {
    
    <T extends ClothingResponseDTO> T EntityToResponseDTO(Clothing clothing) throws Exception;
    
    <T extends Clothing> T RequestDTOToEntity(ClothingRequestDTO dto) throws Exception;
}
