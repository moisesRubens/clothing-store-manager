/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;

/**
 *
 * @author moise
 */
public interface IClothingService {
    
    void registerClothing(ClothingRequestDTO dto) throws Exception;
}
