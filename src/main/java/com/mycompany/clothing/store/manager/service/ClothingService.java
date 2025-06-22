/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;

/**
 *
 * @author moise
 */
public class ClothingService {
    private static ClothingRepository clothingRepository = new ClothingRepository();
    
    public static void register(Clothing clothing) throws Exception{
        if(clothing instanceof Shirt shirt) {
            clothingRepository.registerInDatabase(clothing);
        } else { 
            return;
        }
    }
}
