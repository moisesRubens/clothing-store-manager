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
import com.mycompany.clothing.store.manager.domain.enums.ClothingPiece;
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

    private final ClothingService clothingService;
    private final EntityManager em;

    public ClothingController(EntityManager em) {
        this.em = em;
        this.clothingService = new ClothingService(em);
    }

    public void registerClothing(ClothingRequestDTO data) throws Exception {
        clothingService.register(data);
    }

    public List viewAll(ClothingPiece piece) {
        List<Clothing> list = clothingService.getAllClothings(piece);

        if (piece.equals(ClothingPiece.SHIRT)) {
            List<ShirtResponseDTO> shirts = new ArrayList<>();
            for (Clothing clothing : list) {
                Shirt shirt = (Shirt) clothing;
                shirts.add(new ShirtResponseDTO(shirt.getId(), shirt.getColor(), shirt.getPrice(), shirt.getQuantity(), shirt.getFabric(),
                        shirt.getBrand(), shirt.getStyle(), shirt.getGender(), shirt.getPattern(), shirt.getPocket(),
                        shirt.getClosureType(), shirt.getClothingType(), shirt.getSleeve(), shirt.getCollar(), shirt.getSize()));
            }
            return shirts;
        }
        return null;
    }

    public List<ClothingResponseDTO> consult(ClothingRequestDTO data) throws Exception {
        List<ClothingResponseDTO> list = new ArrayList<>();
        List<Clothing> shirts = clothingService.consult(data);

        if (data instanceof ShirtRequestDTO) {
            for (Clothing c : shirts) {
                Shirt s = (Shirt)c;
                list.add(new ShirtResponseDTO(s.getId(), s.getColor(), s.getPrice(), s.getQuantity(),
                        s.getFabric(), s.getBrand(), s.getStyle(), s.getGender(),
                        s.getPattern(), s.getPocket(), s.getClosureType(),
                        s.getClothingType(), s.getSleeve(), s.getCollar(),
                        s.getSize()));
            }
            return list;
        }
        return list;
    }

    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        clothingService.decrement(id, quantity);
    }

    public Integer getQuantity() {
        return clothingService.getTotalQuantity();
    }
}
