/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.factory;

import com.mycompany.clothing.store.manager.domain.ShirtFactory;
import com.mycompany.clothing.store.manager.domain.IClothingFactory;
import com.mycompany.clothing.store.manager.controller.ShirtController;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.repository.ShirtRepository;
import com.mycompany.clothing.store.manager.service.ClothingService;
import com.mycompany.clothing.store.manager.service.ShirtService;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import com.mycompany.clothing.store.manager.service.mapper.ShirtMapper;
import jakarta.persistence.EntityManager;

/**
 *
 * @author moise
 */
public class ShirtComponentFactory implements ClothingComponentFactory {

    @Override
    public ShirtController createController(ClothingService shirtService) {
        return new ShirtController(shirtService);
    }

    @Override
    public ShirtService createService(ClothingRepository clothingRepository, IClothingFactory clothingFactory, ClothingMapper clothingMapper) {
        return new ShirtService(clothingRepository, clothingFactory, clothingMapper);
    }
    
    @Override
    public ShirtRepository createRepository(EntityManager em) {
        return new ShirtRepository(em);
    }

    @Override
    public ShirtFactory createClothingFactory() {
        return new ShirtFactory();
    }
    
    @Override
    public ShirtMapper createClothingMapper() {
        return new ShirtMapper();
    }

}
