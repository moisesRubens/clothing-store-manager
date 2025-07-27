/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.IClothingFactory;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import java.util.List;

/**
 *
 * @author moise
 */
public class PantService extends ClothingService {

    public PantService(ClothingRepository clothingRepository, IClothingFactory clothingFactory, ClothingMapper clothingMapper) {
        super(clothingRepository, clothingFactory, clothingMapper);
    }
    
    @Override
    public void registerClothing(ClothingRequestDTO dto) throws Exception {
        Clothing pant = clothingMapper.RequestDTOToEntity(dto);
        String query = pant.createQuery();
        Integer id = clothingRepository.getClothingId(pant, query);
        
        if(id != -1) {
            clothingRepository.saveClothing(pant);
        } else {
            incrementClothing(id, pant.getId());
        }
    }

    @Override
    public void incrementClothing(Integer id, Integer quantity) {
        clothingRepository.updateQuantityClothing(id, quantity);
    }

    @Override
    public void removeClothingModelById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PantResponseDTO> getAllClothings() throws Exception {
        return clothingRepository.getAllClothing().stream().map(
            clothing -> (PantResponseDTO) clothingMapper.EntityToResponseDTO(clothing)).toList();
    }

    @Override
    public List<PantResponseDTO> getClothings(ClothingRequestDTO dto) throws Exception {
        Clothing clothing = clothingMapper.RequestDTOToEntity(dto);

        return clothingRepository.getClothings(clothing).stream()
                .map(c -> (PantResponseDTO) clothingMapper.EntityToResponseDTO(c)).toList();
    }

    @Override
    public ClothingResponseDTO getClothingById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
