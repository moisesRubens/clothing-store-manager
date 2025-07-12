/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaNaoExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingPiece;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author moise
 */
public class ClothingService {

    private final EntityManager em;
    private final ClothingRepository clothingRepository;

    public ClothingService(EntityManager em) {
        this.em = em;
        this.clothingRepository = new ClothingRepository(em);
    }

    private Integer existClothing(ClothingRequestDTO clothing) {
        return clothingRepository.existClothing(clothing);
    }

    public List<Clothing> getAllClothings(ClothingPiece piece) {
        return clothingRepository.getAll(piece);
    }

    public void register(ClothingRequestDTO clothing) throws Exception {
        Integer id = existClothing(clothing);

        if (id == -1) {
            Clothing clothing2 = ClothingMapper.DTOToShirt(clothing);
            clothingRepository.registerInDatabase(clothing2);
        } else {
            incrementClothing(id, clothing.quantity());
        }
    }

    public List consult(Clothing clothing) throws Exception {
        Boolean hasAtribute = containstAtributes(clothing);

        return clothingRepository.consult(clothing, hasAtribute);
    }

    public Integer getTotalQuantity() {
        return clothingRepository.getTotalQuantity();
    }

    private boolean containstAtributes(Clothing clothing) {
        Boolean hasAtribute = false;

        if (clothing instanceof Shirt shirt) {
            hasAtribute = (!shirt.getColor().isEmpty() || !shirt.getColor().isBlank() || !shirt.getBrand().isEmpty() || !shirt.getBrand().isBlank()
                    || !shirt.getPattern().isEmpty() || !shirt.getPattern().isBlank() || EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())
                    || !shirt.getClosureType().isEmpty() || !shirt.getClosureType().isBlank() || !shirt.getFabric().isEmpty() || !shirt.getFabric().isBlank()
                    || EnumSet.allOf(Gender.class).contains(shirt.getGender())
                    || !shirt.getStyle().isEmpty() || !shirt.getStyle().isBlank() || shirt.getCollar() != -1 || shirt.getSleeve() != -1
                    || shirt.getPocket() != -1 || shirt.getQuantity() != -1 || shirt.getPrice() != -1);
        }

        return hasAtribute;
    }

    private Clothing getClothingById(Integer id) throws Exception {
        return clothingRepository.getClothingById(id);
    }

    public void decrement(Integer id, Integer quantity) throws Exception {
        if (id < 0 || quantity <= 0) {
            throw new IllegalArgumentException("PREENCHA CORRETAMENTE OS CAMPOS");
        }
        Clothing clothing = getClothingById(id);
        if (clothing.getQuantity() < quantity) {
            throw new IllegalStateException("QUANTIDADE INSUFICIENTE");
        }
        clothing.setQuantity(clothing.getQuantity() - quantity);
        clothingRepository.updateData(clothing);
    }

    private void incrementClothing(Integer id, Integer quantity) {
        if (id < 0 || quantity <= 0) {
            throw new IllegalArgumentException("PREENCHA CORRETAMENTE OS CAMPOS");
        }
        clothingRepository.incrementClothing(id, quantity);
    }

    public List getById(Integer id) {
        return clothingRepository.getById(id);
    }
}
