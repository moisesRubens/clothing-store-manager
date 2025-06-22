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
    private static ClothingService clothingService;
    
    public static void register(Clothing clothing) {
        Shirt shirt = (Shirt)clothing;
        
        clothingService.register(shirt);
    }
}
