/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
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
public class ShirtService extends ClothingService {

    public ShirtService(IClothingRepository shirtRepository) {
        super(shirtRepository);
        this.clothingRepository = shirtRepository;
    }

    @Override
    public Clothing createClothing(ClothingRequestDTO clothingData) {
        //usa os dados do dto pra instaciar shirt
        return new Shirt();
    }

    void addShirtInDatabase(Shirt shirt) {
        String query = createQuery(shirt);
        Integer id = clothingRepository.existsClothing(query);

        if (id == -1) {
            clothingRepository.saveClothing(shirt);
        } else {
            incrementClothing(id, shirt.getQuantity());
        }
    }

    @Override
    public void incrementClothing(Integer id, Integer quantity) {
        clothingRepository.updateQuantityClothing(id, quantity);
    }

    @Override
    public void removeClothingById(Integer id) {

    }

    @Override
    public List<ClothingResponseDTO> getAllClothings() {
        return null;
    }

    private String createQuery(Shirt shirt) {
        String query = "SELECT s FROM Shirt s WHERE";

        if (!containsAttribute(shirt)) {
            query += " 1=0";
            return query;
        }

        query += " 1=1";
        if (shirt.getSleeve() != -1) {
            query += " AND s.sleeve <= :sleeve";
        }
        if (shirt.getCollar() != -1) {
            query += " AND s.collar <= :collar";
        }
        if (shirt.getPrice() != -1) {
            query += " AND s.price <= :price";
        }
        if (shirt.getQuantity() != -1) {
            query += " AND s.quantity >= :quantity";
        }
        if (shirt.getPocket() != -1) {
            query += " AND s.pocket = :pocket";
        }
        if (!shirt.getColor().isBlank()) {
            query += " AND s.color LIKE :color";
        }
        if (!shirt.getFabric().isBlank()) {
            query += " AND s.fabric LIKE :fabric";
        }
        if (!shirt.getBrand().isBlank()) {
            query += " AND s.brand LIKE :brand";
        }
        if (!shirt.getStyle().isBlank()) {
            query += " AND s.style LIKE :style";
        }
        if (!shirt.getPattern().isBlank()) {
            query += " AND s.pattern LIKE :pattern";
        }
        if (!shirt.getClosureType().isBlank()) {
            query += " AND s.closureType LIKE :closureType";
        }
        if (EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())) {
            query += " AND s.size = :size";
        }
        if (EnumSet.allOf(Gender.class).contains(shirt.getGender())) {
            query += " AND s.gender = :gender";
        }
        return query;
    }

    private Boolean containsAttribute(Shirt data) {
        return (!data.getColor().isBlank() || !data.getBrand().isBlank() || !data.getPattern().isBlank()
                || !data.getFabric().isBlank() || EnumSet.allOf(Gender.class).contains(data.getGender())
                || !data.getStyle().isBlank() || data.getQuantity() != -1 || data.getPrice() != -1
                || EnumSet.allOf(ShirtSize.class).contains(data.getSize()) || data.getCollar() != -1
                || data.getSleeve() != -1 || !data.getClosureType().isBlank() || data.getPocket() != -1);
    }
}
