/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import java.util.List;

/**
 *
 * @author moise
 */
public abstract class ClothingService {
    ClothingRepository clothingRepository;

    public ClothingService(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }
    
    public abstract void removeClothingById(Integer id);
    
    public abstract List<ClothingResponseDTO> getAllClothings();
    
    public  abstract Clothing createClothing(ClothingRequestDTO dto);
    
    public Integer getClothingId(Clothing clothing, String query) throws Exception {
        return null;
    }
    
    public void incrementClothing(Integer id, Integer quantity) {
        clothingRepository.updateQuantityClothing(id, quantity);
    }
}
