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
import com.mycompany.clothing.store.manager.domain.IClothingFactory;
import java.util.List;

/**
 *
 * @author moise
 */
public class ShirtService extends ClothingService {

    public ShirtService(ClothingRepository shirtRepository, IClothingFactory shirtFactory, ClothingMapper shirtMapper) {
        super(shirtRepository, shirtFactory, shirtMapper);
    }

    @Override
    public void registerClothing(ClothingRequestDTO clothingData) throws Exception {
        Clothing clothing = clothingFactory.createClothing(clothingData);
        String query = clothing.createQuery();
        Integer id = clothingRepository.getClothingId(clothing, query);

        if (id == -1) {
            clothingRepository.saveClothing(clothing);
        } else {
            incrementClothing(id, clothing.getQuantity());
        }
    }

    @Override
    public void incrementClothing(Integer id, Integer quantity) {
        clothingRepository.updateQuantityClothing(id, quantity);
    }

    @Override
    public void removeClothingModelById(Integer id) throws Exception {
        clothingRepository.removeClothingById(id);
    }

    @Override
    public ShirtResponseDTO getClothingById(Integer id) throws Exception {
        Clothing clothing = clothingRepository.getClothingById(id);
        return (ShirtResponseDTO) clothingMapper.EntityToResponseDTO(clothing);
    }

    @Override
    public List<ShirtResponseDTO> getAllClothings() throws Exception {
        return clothingRepository.getAllClothing().stream()
                .map(clothing -> (ShirtResponseDTO) clothingMapper.EntityToResponseDTO(clothing)).toList();
    }

    @Override
    public List<ShirtResponseDTO> getClothings(ClothingRequestDTO dto) throws Exception {
        Clothing clothing = clothingMapper.RequestDTOToEntity(dto);

        return clothingRepository.getClothings(clothing).stream()
                .map(c -> (ShirtResponseDTO) clothingMapper.EntityToResponseDTO(c)).toList();
    }
}
