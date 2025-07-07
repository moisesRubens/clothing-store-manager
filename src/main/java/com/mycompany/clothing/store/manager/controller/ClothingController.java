/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.service.ClothingService;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moise
 */
public class ClothingController {

    private ClothingService clothingService;
    private EntityManager em;
    
    public ClothingController(EntityManager em) {
        this.em = em;
        this.clothingService = new ClothingService(em);
    }

    public void register(ClothingRequestDTO data) throws Exception {
        if (data instanceof ShirtRequestDTO shirtData) {
            Shirt shirt = new Shirt(shirtData.color(), shirtData.price(), shirtData.quantity(), shirtData.clothingType(),
                    shirtData.fabric(), shirtData.brand(), shirtData.style(), shirtData.gender(), shirtData.pattern(),
                    shirtData.pocket(), shirtData.closureType(), shirtData.size(), shirtData.sleeve(), shirtData.collar());

            clothingService.register(shirt);
        }
    }

    public List<ClothingResponseDTO> consult(ClothingRequestDTO data) throws Exception {
        List list = null;

        if (data instanceof ShirtRequestDTO shirtData) {
            Shirt shirt = new Shirt(shirtData.color(), shirtData.price(), shirtData.quantity(), shirtData.clothingType(),
                                    shirtData.fabric(), shirtData.brand(), shirtData.style(), shirtData.gender(), shirtData.pattern(),
                                    shirtData.pocket(), shirtData.closureType(), shirtData.size(), shirtData.sleeve(), shirtData.collar());
            
            List<Shirt> shirts = clothingService.consult(shirt);
            list = shirts.stream().map(shirtAux -> new ShirtResponseDTO(shirtAux.getId(), shirtAux.getColor(), shirtAux.getPrice(), shirtAux.getQuantity(),
                                                                        shirtAux.getFabric(), shirtAux.getBrand(), shirtAux.getStyle(), shirtAux.getGender(),
                                                                        shirtAux.getPattern(), shirtAux.getPocket(), shirtAux.getClosureType(),
                                                                        shirtAux.getClothingType(), shirtAux.getSleeve(), shirtAux.getCollar(),
                                                                        shirtAux.getSize())).toList();
        } 
        return list;
    }
    
    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        clothingService.decrement(id, quantity);
    }
    
    public List<ClothingResponseDTO> consultById(Integer id) {
        List<Clothing> list = clothingService.getById(id);
        List listResult = new ArrayList<>();
        
        if(list == null || list.isEmpty()) {
            return listResult;
        }
        
        if(list.get(0) instanceof Shirt) {
            List<Shirt> shirts = new ArrayList<>();
            
            for(Clothing c : list) {
                shirts.add((Shirt)c);
            }
            
            listResult = shirts.stream().map(shirt -> new ShirtResponseDTO(shirt.getId(), shirt.getColor(), shirt.getPrice(), shirt.getQuantity(),
                                                                   shirt.getFabric(), shirt.getBrand(), shirt.getStyle(), shirt.getGender(), 
                                                                   shirt.getPattern(), shirt.getPocket(), shirt.getClosureType(), shirt.getClothingType(),
                                                                   shirt.getSleeve(), shirt.getCollar(), shirt.getSize())).toList();
        }

        return listResult;
    }
    
    public Integer getQuantity() {
        return clothingService.getTotalQuantity();
    }
}
