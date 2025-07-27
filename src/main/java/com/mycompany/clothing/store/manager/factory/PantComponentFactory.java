/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.factory;

import com.mycompany.clothing.store.manager.controller.IClothingController;
import com.mycompany.clothing.store.manager.controller.PantController;
import com.mycompany.clothing.store.manager.domain.IClothingFactory;
import com.mycompany.clothing.store.manager.domain.PantFactory;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.repository.PantRepository;
import com.mycompany.clothing.store.manager.service.ClothingService;
import com.mycompany.clothing.store.manager.service.PantService;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import com.mycompany.clothing.store.manager.service.mapper.PantMapper;
import jakarta.persistence.EntityManager;

/**
 *
 * @author moise
 */
public class PantComponentFactory implements ClothingComponentFactory {

    @Override
    public PantController createController(ClothingService clothingService) {
        return new PantController(clothingService);
    }

    @Override
    public ClothingService createService(ClothingRepository clothingRepository, IClothingFactory clothingFactory, ClothingMapper clothingMapper) {
        return new PantService(clothingRepository, clothingFactory, clothingMapper);
    }

    @Override
    public ClothingRepository createRepository(EntityManager em) {
        return new PantRepository(em);
    }

    @Override
    public PantFactory createClothingFactory() {
        return new PantFactory();
    }

    @Override
    public PantMapper createClothingMapper() {
        return new PantMapper();
    }
    
}
