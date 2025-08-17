/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.interfaces.IClothingController;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.mycompany.clothing.store.manager.interfaces.IClothingService;
import java.util.ArrayList;

/**
 *
 * @author moise
 */
@Controller
public class ShirtController implements IClothingController {

    @Autowired
    private IClothingService shirtService;

    @Override
    public void createClothing(ClothingRequestDTO clothingData) throws Exception {
        if (!(clothingData instanceof ShirtRequestDTO)) {
            throw new IllegalArgumentException("Forneça dados corretos para a criação de uma camisa");
        }
        shirtService.registerClothing(clothingData);
    }

    @Override
    public ClothingResponseDTO consultClothing(Integer id) throws Exception {
        return shirtService.getClothingById(id);
    }

    @Override
    public List<ClothingResponseDTO> getClothingsList(ClothingRequestDTO dto) throws Exception {
        return shirtService.getListClothings(dto);
    }

    @Override
    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        shirtService.decrementClothing(id, quantity);
    }

    @Override
    public void deleteClothing(Integer id) throws Exception {
        shirtService.deleteClothing(id);
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception {
        return shirtService.getAllClothings();
    }

    @Override
    public void deleteAllClothings() throws Exception {
        shirtService.deleteAllClothings();
    }

    @Override
    public List<String> getColumnsNames() {
        return shirtService.getColumnsNames();
    }
}
