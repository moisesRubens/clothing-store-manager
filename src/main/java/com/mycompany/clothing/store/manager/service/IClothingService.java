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
    void addClothing(ClothingRequestDTO clothing);
    void incrementClothing(Integer id, Integer quantity);
    void deleteById(Integer id);
    List<ClothingResponseDTO> getAllClothings();
}
