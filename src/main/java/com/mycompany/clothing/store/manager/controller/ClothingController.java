/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.controller;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingPiece;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.service.ClothingAntigo;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moise
 */
public class ClothingController {

    private final ClothingAntigo clothingService;
    private final EntityManager em;

    public ClothingController(EntityManager em) {
        this.em = em;
        this.clothingService = new ClothingAntigo(em);
    }

    public void registerClothing(ClothingRequestDTO data) throws Exception {
        clothingService.register(data);
    }

    public List<ClothingResponseDTO> viewAll(ClothingPiece piece) throws Exception {
        List<Clothing> list = clothingService.getAllClothings(piece);
        List<ClothingResponseDTO> listResult = new ArrayList<>();

        if (piece.equals(ClothingPiece.SHIRT)) {
            for (Clothing c : list) {
                Shirt shirt = (Shirt) c;
                listResult.add(new ShirtResponseDTO(shirt.getId(), shirt.getColor(), shirt.getPrice(), shirt.getQuantity(), shirt.getFabric(),
                        shirt.getBrand(), shirt.getStyle(), shirt.getGender(), shirt.getPattern(), shirt.getPocket(),
                        shirt.getClosureType(), shirt.getClothingType(), shirt.getSleeve(), shirt.getCollar(), shirt.getSize()));
            }
            return listResult;
        } else if (piece.equals(ClothingPiece.PANT)) {
            for (Clothing c : list) {
                Pant pant = (Pant) c;
                listResult.add(new PantResponseDTO(pant.getId(), pant.getColor(), pant.getPrice(), pant.getQuantity(), pant.getFabric(),
                        pant.getFabric(), pant.getStyle(), pant.getGender(), pant.getPattern(), pant.getPocket(),
                        pant.getClosureType(), pant.getClothingType(), pant.getSize(), pant.getLength(), pant.getWaistType(), pant.getHemType()));
            }
            return listResult;
        }
        return null;
    }

    public List<ClothingResponseDTO> consult(ClothingRequestDTO data) throws Exception {
        List<ClothingResponseDTO> list = new ArrayList<>();
        List<Clothing> clothings = clothingService.consult(data);

        if (data instanceof ShirtRequestDTO) {
            for (Clothing c : clothings) {
                Shirt s = (Shirt)c;
                list.add(new ShirtResponseDTO(s.getId(), s.getColor(), s.getPrice(), s.getQuantity(),
                        s.getFabric(), s.getBrand(), s.getStyle(), s.getGender(),
                        s.getPattern(), s.getPocket(), s.getClosureType(),
                        s.getClothingType(), s.getSleeve(), s.getCollar(),
                        s.getSize()));
            }
            return list;
        } else if(data instanceof PantRequestDTO) {
            for (Clothing c : clothings) {
                Pant p = (Pant)c;
                list.add(new PantResponseDTO(p.getId(), p.getColor(), p.getPrice(), p.getQuantity(), 
                        p.getFabric(), p.getBrand(), p.getStyle(), p.getGender(), p.getPattern(), p.getPocket(),
                        p.getClosureType(), p.getClothingType(), p.getSize(), p.getLength(), p.getWaistType(), p.getHemType()));
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
