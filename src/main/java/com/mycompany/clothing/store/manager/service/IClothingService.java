/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import java.util.List;

/**
 *
 * @author moise
 */
public interface IClothingService {
    void registerClothing(ClothingRequestDTO dto) throws Exception;

    void incrementClothing(Integer id, Integer quantity);

    void removeClothingModelById(Integer id);

    <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception;

    Clothing getClothingById(Clothing clothing, String query) throws Exception;
}
