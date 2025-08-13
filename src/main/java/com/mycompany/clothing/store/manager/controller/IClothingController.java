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
    
    void createClothing(ClothingRequestDTO clothingData) throws Exception;
    
    ClothingResponseDTO consultClothing(Integer id) throws Exception;
    
    <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception;
    
    <T extends ClothingResponseDTO> List<T> getClothingsList(ClothingRequestDTO dto) throws Exception;
    
    void decrementClothing(Integer id, Integer quantity) throws Exception;
    
    void deleteClothing(Integer id) throws Exception;
    
    void deleteClothingsList(ClothingRequestDTO dto) throws Exception;
}
