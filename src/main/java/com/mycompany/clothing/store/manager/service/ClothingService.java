/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moise
 */
public class ClothingService {
    private static ClothingRepository clothingRepository = new ClothingRepository();
    
    public void register(Clothing clothing) throws Exception{
        clothingRepository.registerInDatabase(clothing);   
    }
    
    public List consult(Clothing clothing) throws Exception {
        return clothingRepository.consult(clothing); 
    }
    
    
}
