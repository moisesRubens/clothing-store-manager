/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.service.IClothingService;
import java.util.List;

/**
 *
 * @author moise
 */
public class ShirtController implements IClothingController {
    private IClothingService shirtService;

    public ShirtController(IClothingService shirtService) {
        this.shirtService = shirtService;
    }
    
    @Override
    public void createClothing(ClothingRequestDTO clothingData) throws Exception {
        if(clothingData instanceof ShirtRequestDTO shirtData) {
            shirtService.registerClothing(shirtData);
        }
    }

    @Override
    public List<ShirtResponseDTO> getAllClothing() throws Exception {
        return shirtService.getAllClothings();
    }

    @Override
    public ShirtResponseDTO consultClothingById(Integer id) throws Exception{
        return (ShirtResponseDTO) shirtService.getClothingById(id);
    }

    @Override
    public List<ShirtResponseDTO> consultClothings(ClothingRequestDTO dto) throws Exception {
        return shirtService.getClothings(dto);
    }

    @Override
    public void deleteClothingModelById(Integer id) {
        
    }

    @Override
    public void removeClothingUnitsById(Integer id, Integer quantity) throws Exception {
        
    }
}
