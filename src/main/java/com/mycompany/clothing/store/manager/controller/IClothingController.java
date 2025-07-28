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
    
    void deleteClothingModelById(Integer id) throws Exception;
    
    void removeClothingUnitsById(Integer id, Integer quantity) throws Exception;
    
    ClothingResponseDTO consultClothingById(Integer id) throws Exception;   
    
    <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception;
    
    <T extends ClothingResponseDTO> List<T> consultClothings(ClothingRequestDTO dto) throws Exception;
}
