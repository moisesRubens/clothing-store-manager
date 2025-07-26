/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import java.util.List;

/**
 *
 * @author moise
 */
public interface IClothingController {
    
    public void createClothing(ClothingRequestDTO clothingData) throws Exception;
    
    public void deleteClothingById(Integer id);
    
    public <T extends ClothingResponseDTO> List<T> getAllClothing() throws Exception;
    
    public <T extends ClothingResponseDTO> T consultClothingById(Integer id) throws Exception;
    
    public <T extends ClothingResponseDTO> List<T> consultClothings(ClothingRequestDTO dto) throws Exception;
}
