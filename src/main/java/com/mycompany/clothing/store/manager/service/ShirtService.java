/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.factory.IClothingFactory;
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
    public void removeClothingModelById(Integer id) throws Exception {
        shirtRepository.removeClothingById(id);
    }

    @Override
    public ShirtResponseDTO getClothingById(Integer id) throws Exception {
        Clothing clothing = shirtRepository.getClothingById(id);
        return (ShirtResponseDTO) shirtMapper.EntityToResponseDTO(clothing);
    }

    @Override
    public List<ShirtResponseDTO> getAllClothings() throws Exception {
        return shirtRepository.getAllClothing().stream()
                .map(clothing -> (ShirtResponseDTO) shirtMapper.EntityToResponseDTO(clothing)).toList();
    }

    @Override
    public List<ShirtResponseDTO> getClothings(ClothingRequestDTO dto) throws Exception {
        Clothing clothing = shirtMapper.RequestDTOToEntity(dto);
        
        return shirtRepository.getClothings(clothing).stream()
                .map(c -> (ShirtResponseDTO) shirtMapper.EntityToResponseDTO(c)).toList();
    }
}
