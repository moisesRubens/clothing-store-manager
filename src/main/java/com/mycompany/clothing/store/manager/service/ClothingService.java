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
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
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

    public List<Clothing> consult(ClothingRequestDTO data) throws Exception {
        String query = createQuerry(data);
        return clothingRepository.consult(data, query);
    }

    private String createQuerry(ClothingRequestDTO data) {
        String query = "";
        
        if (data instanceof ShirtRequestDTO shirtData) {
            query += "SELECT s FROM Shirt s WHERE";

            if (!containstAtributes(data)) {
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
        return "";
    }
    

    public Integer getTotalQuantity() {
        return clothingRepository.getTotalQuantity();
    }

    private boolean containstAtributes(ClothingRequestDTO data) {
        Boolean hasAtribute = false;

        if (data instanceof ShirtRequestDTO shirtData) {
            hasAtribute = (!shirtData.color().isBlank() || !shirtData.brand().isBlank() || !shirtData.pattern().isBlank()
                    || EnumSet.allOf(ShirtSize.class).contains(shirtData.size()) || !shirtData.closureType().isBlank()
                    || !shirtData.fabric().isBlank() || EnumSet.allOf(Gender.class).contains(shirtData.gender())
                    || !shirtData.style().isBlank() || shirtData.collar() != -1 || shirtData.sleeve() != -1
                    || shirtData.pocket() != -1 || shirtData.quantity() != -1 || shirtData.price() != -1);
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
