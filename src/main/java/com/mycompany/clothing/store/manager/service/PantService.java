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
import com.mycompany.clothing.store.manager.domain.dto.PantResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.PantLengthType;
import com.mycompany.clothing.store.manager.repository.IPantRepository;
import com.mycompany.clothing.store.manager.repository.IShirtRepository;
import com.mycompany.clothing.store.manager.repository.PantSpecification;
import com.mycompany.clothing.store.manager.repository.ShirtSpecification;
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

    private IPantRepository pantRepository;
    private ClothingMapper pantMapper;

    @Autowired
    public PantService(IPantRepository pantRepository, PantMapper pantMapper) {
        this.pantRepository = pantRepository;
        this.pantMapper = pantMapper;
    }
    
    

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
        Pant pant = pantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Calca com id:"+id+" não encontrada para a remoção"));
        
        Integer newQuantity = pant.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new IllegalStateException("Quantidade insuficiente para a remoção do modelo de calça: "+id);
        } else if (newQuantity == 0) {
            pantRepository.delete(pant);
        } else {
            pant.setQuantity(newQuantity);
            pantRepository.save(pant);
        }
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getAllClothings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PantResponseDTO> getListClothings(ClothingRequestDTO dto) {
        PantRequestDTO pantDTO = (PantRequestDTO) dto;
        Pant pantFilter = (Pant) pantMapper.RequestDTOToEntity(pantDTO);
        System.out.println(pantFilter);
        List<Pant> filteredPants = pantRepository.findAll(PantSpecification.withFilters(pantFilter));
        return filteredPants.stream()
                .map(pant -> (PantResponseDTO) pantMapper.EntityToResponseDTO(pant))
                .toList();
    }
    
}
