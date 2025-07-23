/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.repository.IClothingRepository;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author moise
 */
public class ShirtService implements IClothingService{
    private IClothingRepository shirtRepository;

    public ShirtService(IClothingRepository shirtRepository) {
        this.shirtRepository = shirtRepository;
    }
    
    @Override
    public void addClothing(ClothingRequestDTO clothingData) {
        ShirtRequestDTO shirtData = (ShirtRequestDTO) clothingData;
        String query = createQuery(shirtData);
        Integer id = shirtRepository.existsClothing(query);
        
        if(id != -1) {
            Clothing clothing = ClothingMapper.DTOToEntity(clothingData);
            shirtRepository.saveClothing(clothing);
        } else {
            incrementClothing(id , clothingData.quantity());
        }
    }
    
    @Override
    public void incrementClothing(Integer id, Integer quantity) {
        shirtRepository.updateQuantityClothing(id, quantity);
    }

    @Override
    public void deleteById(Integer id) {
        
    }

    @Override
    public List<ClothingResponseDTO> getAllClothings() {
        return null;
    }
    
    private String createQuery(ShirtRequestDTO shirtData) {
        String query = "SELECT s FROM Shirt s WHERE";

        if (!containsAttribute(shirtData)) {
            query += " 1=0";
            return query;
        }

        query += " 1=1";
        if (shirtData.sleeve() != -1) {
            query += " AND s.sleeve <= :sleeve";
        }
        if (shirtData.collar() != -1) {
            query += " AND s.collar <= :collar";
        }
        if (shirtData.price() != -1) {
            query += " AND s.price <= :price";
        }
        if (shirtData.quantity() != -1) {
            query += " AND s.quantity >= :quantity";
        }
        if (shirtData.pocket() != -1) {
            query += " AND s.pocket = :pocket";
        }
        if (!shirtData.color().isBlank()) {
            query += " AND s.color LIKE :color";
        }
        if (!shirtData.fabric().isBlank()) {
            query += " AND s.fabric LIKE :fabric";
        }
        if (!shirtData.brand().isBlank()) {
            query += " AND s.brand LIKE :brand";
        }
        if (!shirtData.style().isBlank()) {
            query += " AND s.style LIKE :style";
        }
        if (!shirtData.pattern().isBlank()) {
            query += " AND s.pattern LIKE :pattern";
        }
        if (!shirtData.closureType().isBlank()) {
            query += " AND s.closureType LIKE :closureType";
        }
        if (EnumSet.allOf(ShirtSize.class).contains(shirtData.size())) {
            query += " AND s.size = :size";
        }
        if (EnumSet.allOf(Gender.class).contains(shirtData.gender())) {
            query += " AND s.gender = :gender";
        }
        return query;
    }

    private Boolean containsAttribute(ShirtRequestDTO data) {
        return (!data.color().isBlank() || !data.brand().isBlank() || !data.pattern().isBlank()
                || !data.fabric().isBlank() || EnumSet.allOf(Gender.class).contains(data.gender())
                || !data.style().isBlank() || data.quantity() != -1 || data.price() != -1 
                || EnumSet.allOf(ShirtSize.class).contains(data.size()) || data.collar() != -1
                || data.sleeve() != -1 || !data.closureType().isBlank() || data.pocket() != -1 );
    }
}
