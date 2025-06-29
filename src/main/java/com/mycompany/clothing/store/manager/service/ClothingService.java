/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author moise
 */
public class ClothingService {

    private static ClothingRepository clothingRepository = new ClothingRepository();

    public void register(Clothing clothing) throws Exception {
        clothingRepository.registerInDatabase(clothing);
    }

    public List consult(Clothing clothing) throws Exception {
        Boolean hasAtribute = containstAtributes(clothing);
        return clothingRepository.consult(clothing, hasAtribute);
    }

    private boolean containstAtributes(Clothing clothing) {
        Boolean hasAtribute = false;
        
        if (clothing instanceof Shirt shirt) {
            System.out.println(shirt);
            hasAtribute = (!shirt.getColor().equals("empty") || !shirt.getBrand().equals("empty")
                    || !shirt.getPattern().equals("empty") || EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())
                    || !shirt.getClosureType().equals("empty") || EnumSet.allOf(ClothingType.class).contains(shirt.getClothingType())
                    || !shirt.getFabric().equals("empty") || EnumSet.allOf(Gender.class).contains(shirt.getGender())
                    || !shirt.getStyle().equals("empty") || shirt.getCollar() == true || shirt.getSleeve() == true
                    || shirt.getPocket() != -1 || shirt.getQuantity() != -1);
        }   
        
        return hasAtribute;
    }
}
