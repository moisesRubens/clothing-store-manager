/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.repository.IClothingRepository;
import java.util.List;

/**
 *
 * @author moise
 */
public abstract class ClothingService {
    IClothingRepository clothingRepository;

    public ClothingService(IClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }
    
    abstract void removeClothingById(Integer id);
    
    abstract List<ClothingResponseDTO> getAllClothings();
    
    abstract Clothing createClothing(ClothingRequestDTO dto);
    
    Integer getClothingId(String query) {
        return clothingRepository.existsClothing(query);
    }
    
    void incrementClothing(Integer id, Integer quantity) {
        
    }
}
