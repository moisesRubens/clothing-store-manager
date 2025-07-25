/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import factory.ClothingFactory;
import factory.IClothingFactory;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author moise
 */
public class ShirtService implements IClothingService {
    private IClothingFactory shirtFactory;
    private ClothingRepository shirtRepository;
    private ClothingMapper shirtMapper;
    
    public ShirtService(ClothingRepository shirtRepository, IClothingFactory clothingFactory, ClothingMapper clothingMapper) {
        this.shirtRepository = shirtRepository;
        this.shirtFactory = clothingFactory;
        this.shirtMapper = clothingMapper;
    }

    @Override
    public void registerClothing(ClothingRequestDTO clothingData) throws Exception {
        Clothing clothing = shirtFactory.createClothing(clothingData);
        String query = clothing.createQuery();
        Integer id = shirtRepository.existsClothing(clothing, query);

        if (id == -1) {
            shirtRepository.saveClothing(clothing);
        } else {
            incrementClothing(id, clothing.getQuantity());
        }
    }

    @Override
    public void incrementClothing(Integer id, Integer quantity) {
        shirtRepository.updateQuantityClothing(id, quantity);
    }
    
    @Override
    public void removeClothingModelById(Integer id) {
        
    }

    @Override
    public Clothing getClothingById(Clothing clothing, String query) throws Exception {
        return null;
    }

    @Override
    public List<ShirtResponseDTO> getAllClothings() throws Exception {
        return shirtRepository.getAllClothing().stream()
                .map(clothing -> (ShirtResponseDTO) shirtMapper.EntityToDTO(clothing)).toList();
    }
}
