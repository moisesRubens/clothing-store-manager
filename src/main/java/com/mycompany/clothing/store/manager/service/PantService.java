/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.PantLengthType;
import com.mycompany.clothing.store.manager.repository.IPantRepository;
import com.mycompany.clothing.store.manager.repository.IShirtRepository;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import com.mycompany.clothing.store.manager.service.mapper.PantMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author moise
 */
@Service
public class PantService implements IClothingService {

    @Autowired
    private IPantRepository pantRepository;
    @Autowired
    private PantMapper pantMapper;

    @Override
    public void registerClothing(ClothingRequestDTO dto) throws Exception {

        PantRequestDTO pantDTO = (PantRequestDTO) dto;
        Pant pant = (Pant) pantMapper.RequestDTOToEntity(pantDTO);
        Optional<Pant> found = pantRepository.findExistingPant(pant.getColor(), pant.getClothingType(), pant.getFabric(), 
                pant.getBrand(), pant.getStyle(), pant.getGender(), pant.getPattern(), pant.getPocket(), pant.getClosureType(),
                pant.getSize(), pant.getHemType(), pant.getLength(), pant.getWaistType());

        if (found.isPresent()) {
            Pant foundPant = found.get();
            foundPant.setQuantity(foundPant.getQuantity() + pant.getQuantity());
            pantRepository.save(foundPant);
        } else {
            pantRepository.save(pant);
        }
    }

    @Override
    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getAllClothings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getListClothings(ClothingRequestDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
