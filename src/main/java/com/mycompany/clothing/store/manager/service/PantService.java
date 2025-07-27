/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import java.util.List;

/**
 *
 * @author moise
 */
public class PantService implements IClothingService {

    private ClothingRepository pantRepository;
    private ClothingMapper pantMapper;

    public PantService(ClothingRepository pantRepository, ClothingMapper pantMapper) {
        this.pantRepository = pantRepository;
        this.pantMapper = pantMapper;
    }
    
    @Override
    public void registerClothing(ClothingRequestDTO dto) throws Exception {
        Clothing pant = pantMapper.RequestDTOToEntity(dto);
        String query = pant.createQuery();
        Integer id = pantRepository.getClothingId(pant, query);
        
        if(id != -1) {
            pantRepository.saveClothing(pant);
        } else {
            incrementClothing(id, pant.getId());
        }
    }

    @Override
    public void incrementClothing(Integer id, Integer quantity) {
        pantRepository.updateQuantityClothing(id, quantity);
    }

    @Override
    public void removeClothingModelById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getClothings(ClothingRequestDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClothingResponseDTO getClothingById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
