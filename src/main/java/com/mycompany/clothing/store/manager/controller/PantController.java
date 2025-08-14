/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.interfaces.IClothingController;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantResponseDTO;
import com.mycompany.clothing.store.manager.interfaces.IClothingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author moise
 */
@Controller
public class PantController implements IClothingController {

    @Autowired
    private IClothingService pantService;

    @Override
    public void createClothing(ClothingRequestDTO clothingData) throws Exception {
        if (!(clothingData instanceof PantRequestDTO pantDTO)) {
            throw new IllegalArgumentException("Forneça dados corretos para a criação de uma calça");
        } 
        pantService.registerClothing(clothingData);
    }

    @Override
    public ClothingResponseDTO consultClothing(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PantResponseDTO> getAllClothings() throws Exception {
        return pantService.getAllClothings();
    }

    @Override
    public List<PantResponseDTO> getClothingsList(ClothingRequestDTO dto) throws Exception {
        return pantService.getListClothings(dto);
    }

    @Override
    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        pantService.decrementClothing(id, quantity); 
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
