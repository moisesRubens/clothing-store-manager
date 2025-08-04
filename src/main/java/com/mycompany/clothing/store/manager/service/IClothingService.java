/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import java.util.List;

/**
 *
 * @author moise
 */
public interface IClothingService {
    
    void registerClothing(ClothingRequestDTO dto) throws Exception;
    
    void decrementClothing(Integer id, Integer quantity) throws Exception;
    
    <T extends ClothingResponseDTO> List<T> getAllClothings();
    
    <T extends ClothingResponseDTO> List<T> getListClothings(ClothingRequestDTO dto);
}
