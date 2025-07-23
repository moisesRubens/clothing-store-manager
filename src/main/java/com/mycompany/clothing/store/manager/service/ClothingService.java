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
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingPiece;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.HemType;
import com.mycompany.clothing.store.manager.domain.enums.PantieLengthType;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
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

    private Integer existClothing(ClothingRequestDTO data) {
        return clothingRepository.existClothing(data);
    }

    public List<Clothing> getAllClothings(ClothingPiece piece) throws Exception {
        List<Clothing> list;
        String query = "";
        
        if(piece.equals(ClothingPiece.SHIRT)) {
            query += "SELECT s FROM Shirt s";
        } else if (piece.equals(ClothingPiece.PANT)) {
            query += "SELECT p FROM Pant p";
        }
        
        list = clothingRepository.getAll(query);
        if(list.isEmpty()) {
            throw new RoupaNaoExistenteException();
        }
        return list;
    }

    public void register(ClothingRequestDTO data) throws Exception {
        Integer id = existClothing(data);

        if (id == -1) {
            Clothing clothing = ClothingMapper.DTOToEntity(data);
            clothingRepository.registerInDatabase(clothing);
        } else {
            incrementClothing(id, data);
        }
    }

    public List<Clothing> consult(ClothingRequestDTO data) throws Exception {
        String query = createQuery(data);
        return clothingRepository.consult(data, query);
    }

    private String createQuery(ClothingRequestDTO data) {
        String query = "";
        
        if (data instanceof ShirtRequestDTO shirtData) {
            query += "SELECT s FROM Shirt s WHERE";

            if (!containstAtributes(shirtData)) {
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
        } else if(data instanceof PantRequestDTO dataPantie) {
            query += "SELECT p FROM Pant p WHERE";

            if (!containstAtributes(dataPantie)) {
                query += " 1=0";
                return query;
            }
            query += " 1=1";
            
            if (EnumSet.allOf(HemType.class).contains(dataPantie.hemType())) {
                query += " AND p.hemType = :hemType";
            }
            if (EnumSet.allOf(WaistType.class).contains(dataPantie.waistType())) {
                query += " AND p.waistType = :waistType";
            }
            if (EnumSet.allOf(PantieLengthType.class).contains(dataPantie.length())) {
                query += " AND p.length = :length";
            }
            if (EnumSet.allOf(Gender.class).contains(dataPantie.gender())) {
                query += " AND p.gender = :gender";
            }
            if (dataPantie.size() != -1) {
                query += " AND p.size >= :size";
            }
            if (dataPantie.quantity() != -1) {
                query += " AND p.quantity >= :quantity";
            }
            if (dataPantie.pocket() != -1) {
                query += " AND p.pocket = :pocket";
            }
            if (!dataPantie.color().isBlank()) {
                query += " AND p.color LIKE :color";
            }
            if (!dataPantie.fabric().isBlank()) {
                query += " AND p.fabric LIKE :fabric";
            }
            if (!dataPantie.brand().isBlank()) {
                query += " AND p.brand LIKE :brand";
            }
            if (!dataPantie.style().isBlank()) {
                query += " AND p.style LIKE :style";
            }
            if (!dataPantie.pattern().isBlank()) {
                query += " AND p.pattern LIKE :pattern";
            }
            if (!dataPantie.closureType().isBlank()) {
                query += " AND p.closureType LIKE :closureType";
            }
            return query;
        }
        return "";
    }
    

    public Integer getTotalQuantity() {
        return clothingRepository.getTotalQuantity();
    }

    private boolean containstAtributes(ClothingRequestDTO data) {
        System.out.println(data);
        
        Boolean hasAtribute = (!data.color().isBlank() || !data.brand().isBlank() || !data.pattern().isBlank()
                || !data.fabric().isBlank() || EnumSet.allOf(Gender.class).contains(data.gender())
                || !data.style().isBlank() || data.quantity() != -1 || data.price() != -1);

        if (hasAtribute == true) {
            return hasAtribute;
        }
        
        switch (data) {
            case ShirtRequestDTO shirtData -> hasAtribute = (EnumSet.allOf(ShirtSize.class).contains(shirtData.size()) || shirtData.collar() != -1
                    || shirtData.sleeve() != -1 || !shirtData.closureType().isBlank() || shirtData.pocket() != -1 );
            case PantRequestDTO pantieData -> hasAtribute = (EnumSet.allOf(HemType.class).contains(pantieData.hemType())
                    || EnumSet.allOf(WaistType.class).contains(pantieData.waistType())
                    || EnumSet.allOf(PantieLengthType.class).contains(pantieData.length())
                    || pantieData.size() != -1 || !pantieData.closureType().isBlank() || pantieData.pocket() != -1 );
            default -> {}
        }
        return hasAtribute;
    }

    private Clothing getClothingById(Integer id) throws Exception {
        return clothingRepository.getClothingById(id);
    }

    public void decrement(Integer id, Integer quantity) throws Exception {
        if (id < 0 || quantity <= 0) {
            throw new IllegalArgumentException("INSIRA UM ID VALIDO");
        }
        
        Clothing clothing = getClothingById(id);
        if (clothing.getQuantity() < quantity) {
            throw new IllegalStateException("QUANTIDADE INSUFICIENTE DE UNIDADES DESTE MODELO");
        }
        clothing.setQuantity(clothing.getQuantity() - quantity);
        clothingRepository.updateData(clothing);
    }

    private void incrementClothing(Integer id, ClothingRequestDTO data) {
        if (id < 0 || data.quantity() <= 0) {
            throw new IllegalArgumentException("PREENCHA CORRETAMENTE OS CAMPOS");
        }
        clothingRepository.incrementClothing(id, data);
    }

    public Clothing getById(Integer id) throws Exception {
        return clothingRepository.getById(id);
    }
}
