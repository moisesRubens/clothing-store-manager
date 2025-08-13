/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantieRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantieResponseDTO;
import com.mycompany.clothing.store.manager.service.IClothingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moise
 */
public class PantieController implements IClothingController {
    
    @Autowired
    private IClothingService pantieService;
    
    @Override
    public void createClothing(ClothingRequestDTO clothingData) throws Exception {
        if(!(clothingData instanceof PantieRequestDTO)) {
            throw new IllegalArgumentException("Passe um PantieRequestDTO");
        }
        pantieService.registerClothing(clothingData);
    }

    @Override
    public List<PantieResponseDTO> getAllClothings() throws Exception {
        return pantieService.getAllClothings();
    }

    @Override
    public List<PantieResponseDTO> getClothingsList(ClothingRequestDTO dto) throws Exception {
        if(!(dto instanceof PantieRequestDTO)) {
            throw new IllegalArgumentException("Passe um PantieResponseDTO");
        }
        return pantieService.getListClothings(dto);
    }

    @Override
    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        pantieService.decrementClothing(id, quantity);
    }
    
    @Override
    public ClothingResponseDTO consultClothing(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteClothing(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteClothingsList(ClothingRequestDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
