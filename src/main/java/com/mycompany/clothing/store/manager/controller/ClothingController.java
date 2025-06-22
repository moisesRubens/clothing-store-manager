/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.service.ClothingService;

/**
 *
 * @author moise
 */
public class ClothingController {
    private static ClothingService clothingService = new ClothingService();
    
    public static boolean register(Clothing clothing) throws Exception {
        if(clothing instanceof Shirt shirt) {
            clothingService.register(shirt);
            return true;
        } else {
            return false;
        }
    }
}
