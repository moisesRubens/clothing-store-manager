/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.interfaces;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author moise
 */
@Component
public interface IClothingService extends IClothingRename {
    
    void registerClothing(ClothingRequestDTO dto) throws Exception;
    
    void decrementClothing(Integer id, Integer quantity) throws Exception;
    
    <T extends ClothingResponseDTO> T getClothingById(Integer id) throws Exception;
    
    <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception;
    
    <T extends ClothingResponseDTO> List<T> getListClothings(ClothingRequestDTO dto) throws Exception;
    
    void deleteClothing(Integer id) throws Exception;
    
    void deleteAllClothings() throws Exception;
}
