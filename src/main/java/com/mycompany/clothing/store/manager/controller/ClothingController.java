/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.service.ClothingService;

/**
 *
 * @author moise
 */
public class ClothingController {
    private static ClothingService clothingService = new ClothingService();
    
    public static void register(ClothingRequestDTO data) throws Exception {
        if(data instanceof ShirtRequestDTO shirtData) {
            Shirt shirt = new Shirt(shirtData.clothingType(), shirtData.sleeve(), shirtData.collar(), shirtData.size(),
                                    shirtData.color(), shirtData.price(), shirtData.quantity(), shirtData.fabric(), shirtData.brand(),
                                    shirtData.style(), shirtData.gender(), shirtData.pattern(), shirtData.pocket(), shirtData.closureType());
            
            clothingService.register(shirt);
        }
    }
}
