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
    
    public abstract <T extends ClothingResponseDTO> T EntityToResponseDTO(Clothing clothing) throws Exception;
    
    public abstract <T extends Clothing> T RequestDTOToEntity(ClothingRequestDTO dto) throws Exception;
}
