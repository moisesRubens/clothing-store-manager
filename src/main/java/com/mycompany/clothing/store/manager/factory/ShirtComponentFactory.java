/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.factory;

import com.mycompany.clothing.store.manager.domain.IClothingFactory;
import com.mycompany.clothing.store.manager.controller.IClothingController;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.service.ClothingService;
import com.mycompany.clothing.store.manager.service.IClothingService;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import jakarta.persistence.EntityManager;

/**
 *
 * @author moise
 */
public interface ShirtComponentFactory {
    
    public IClothingController createController(ClothingService clothingService);
    
    public ClothingService createService(ClothingRepository clothingRepository, IClothingFactory clothingFactory, ClothingMapper clothingMapper);
    
    public ClothingRepository createRepository(EntityManager em);
    
    public IClothingFactory createClothingFactory();
    
    public ClothingMapper createClothingMapper();
}
