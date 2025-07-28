/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantResponseDTO;
import com.mycompany.clothing.store.manager.service.ClothingService;
import com.mycompany.clothing.store.manager.service.IClothingService;
import java.util.List;

/**
 *
 * @author moise
 */
public class PantController implements IClothingController {

    private ClothingService pantService;

    public PantController(ClothingService pantService) {
        this.pantService = pantService;
    }
    
    @Override
    public void createClothing(ClothingRequestDTO clothingData) throws Exception {
        if(clothingData instanceof PantRequestDTO pantData) {
            pantService.registerClothing(pantData);
        }
    }

    @Override
    public void deleteClothingModelById(Integer id) throws Exception {
        pantService.removeClothingModelById(id);
    }

    @Override
    public void removeClothingUnitsById(Integer id, Integer quantity) throws Exception {
        pantService.removeClothingUnitsById(id, quantity);
    }

    @Override
    public List<PantResponseDTO> getAllClothings() throws Exception {
        return pantService.getAllClothings();
    }

    @Override
    public PantResponseDTO consultClothingById(Integer id) throws Exception {
        return (PantResponseDTO) pantService.getClothingById(id);
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> consultClothings(ClothingRequestDTO dto) throws Exception {
        return pantService.getClothings(dto);
    }
    
}
