/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.service.IClothingService;
import java.util.List;
import java.util.stream.Collectors;

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
    public void createClothing(ClothingRequestDTO clothingData) {
        if(clothingData instanceof ShirtRequestDTO shirtData) {
            shirtService.addClothing(shirtData);
        }
    }

    @Override
    public void deleteClothingById(Integer id) {
        shirtService.deleteById(id);
    }

    @Override
    public List<ShirtResponseDTO> getAllClothing() {
        return shirtService.getAllClothings().stream().map(dto -> (ShirtResponseDTO) dto)
                .collect(Collectors.toList());
    }
    
}
