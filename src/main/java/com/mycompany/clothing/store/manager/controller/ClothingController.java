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
import com.mycompany.clothing.store.manager.service.ClothingService;
import java.util.List;

/**
 *
 * @author moise
 */
public class ClothingController {
    private static ClothingService clothingService = new ClothingService();
    
    public static void register(ClothingRequestDTO data) throws Exception {
        if(data instanceof ShirtRequestDTO shirtData) {
            Shirt shirt = new Shirt(shirtData.color(), shirtData.price(), shirtData.quantity(), shirtData.clothingType(),
                                    shirtData.fabric(), shirtData.brand(), shirtData.style(), shirtData.gender(), shirtData.pattern(),
                                    shirtData.pocket(), shirtData.closureType(), shirtData.size(), shirtData.sleeve(), shirtData.collar());
            
            clothingService.register(shirt);
        }
    }
    
    public static List<ClothingResponseDTO> consult(ClothingRequestDTO data) throws Exception {
        List list = null;
        if(data instanceof ShirtRequestDTO shirtData) {
            Shirt shirt = new Shirt(shirtData.color(), shirtData.price(), shirtData.quantity(), shirtData.clothingType(),
                                    shirtData.fabric(), shirtData.brand(), shirtData.style(), shirtData.gender(), shirtData.pattern(),
                                    shirtData.pocket(), shirtData.closureType(), shirtData.size(), shirtData.sleeve(), shirtData.collar());
            list = clothingService.consult(shirt); 
        }
        
        return list;
    }
}
