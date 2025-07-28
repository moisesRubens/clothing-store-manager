/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.IClothingFactory;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import java.util.List;

/**
 *
 * @author moise
 */
public abstract class ClothingService {

    protected ClothingRepository clothingRepository;
    protected IClothingFactory clothingFactory;
    protected ClothingMapper clothingMapper;

    public ClothingService(ClothingRepository clothingRepository, IClothingFactory clothingFactory, ClothingMapper clothingMapper) {
        this.clothingRepository = clothingRepository;
        this.clothingFactory = clothingFactory;
        this.clothingMapper = clothingMapper;
    }

    public abstract void registerClothing(ClothingRequestDTO dto) throws Exception;

    public abstract void removeClothingUnitsById(Integer id, Integer quantity);

    public abstract void removeClothingModelById(Integer id) throws Exception;

    public abstract <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception;

    public abstract <T extends ClothingResponseDTO> List<T> getClothings(ClothingRequestDTO dto) throws Exception;

    public abstract ClothingResponseDTO getClothingById(Integer id) throws Exception;
}
