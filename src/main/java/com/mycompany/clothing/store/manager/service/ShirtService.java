/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.interfaces.IClothingService;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.Size;
import com.mycompany.clothing.store.manager.interfaces.IClothingMapper;
import com.mycompany.clothing.store.manager.repository.IShirtRepository;
import com.mycompany.clothing.store.manager.repository.ShirtSpecification;
import com.mycompany.clothing.store.manager.service.mapper.ShirtMapper;
import java.util.ArrayList;
import java.util.List;
import static java.util.Locale.filter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author moise
 */
@Service
public class ShirtService implements IClothingService {

    private IShirtRepository shirtRepository;
    private IClothingMapper shirtMapper;

    @Autowired
    public ShirtService(IShirtRepository shirtRepository, ShirtMapper shirtMapper) {
        this.shirtRepository = shirtRepository;
        this.shirtMapper = shirtMapper;
    }
    
    @Override
    public void registerClothing(ClothingRequestDTO dto) throws Exception {

        if (!(dto instanceof ShirtRequestDTO)) {
            throw new IllegalArgumentException("Passe um ShirtResponseDTO");
        }

        ShirtRequestDTO shirtDTO = (ShirtRequestDTO) dto;
        Shirt shirt = (Shirt) shirtMapper.RequestDTOToEntity(shirtDTO);
        Optional<Shirt> found = shirtRepository.findExistingShirt(shirt.getColor(), shirt.getFabric(),
                shirt.getBrand(), shirt.getStyle(), shirt.getGender(), shirt.getPattern(), shirt.getPocket(),
                shirt.getClosureType(), shirt.getSize(), shirt.getSleeve(), shirt.getCollar());

        if (found.isPresent()) {
            Shirt foundShirt = found.get();
            foundShirt.setQuantity(foundShirt.getQuantity() + shirt.getQuantity());
            shirtRepository.save(foundShirt);
        } else {
            shirtRepository.save(shirt);
        }
    }

    @Override
    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        Shirt shirt = shirtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Camisa com ID " + id + " não encontrada"));
        Integer newQuantity = shirt.getQuantity() - quantity;

        if (newQuantity < 0) {
            throw new IllegalStateException("Quantidade insuficiente para a remoção deste modelo de camisa");
        } else if (newQuantity == 0) {
            shirtRepository.delete(shirt);
        } else {
            shirt.setQuantity(newQuantity);
            shirtRepository.save(shirt);
        }
    }

    @Override
    public List<ShirtResponseDTO> getListClothings(ClothingRequestDTO dto) throws Exception {
        Shirt shirtFilter = shirtMapper.RequestDTOToEntity(dto);
        List<Shirt> filteredShirts = shirtRepository.findAll(ShirtSpecification.withFilters(shirtFilter));
        List<ShirtResponseDTO> list = new ArrayList<>();
        
        for(Shirt shirt: filteredShirts) {
            list.add(shirtMapper.EntityToResponseDTO(shirt));
        }
        return list;
    } 

    @Override
    public List<ShirtResponseDTO> getAllClothings() throws Exception {
        List<ShirtResponseDTO> list = new ArrayList<>();
        List<Shirt> shirts = shirtRepository.findAll();
        
        for(Shirt shirt : shirts) {
            list.add(shirtMapper.EntityToResponseDTO(shirt));
        }
        return list;
    }

    @Override
    public void deleteClothing(Integer id) throws Exception {
        shirtRepository.deleteById(id);
    }

    @Override
    public ShirtResponseDTO getClothingById(Integer id) throws Exception {
        Shirt shirt = shirtRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Camisa com id: "+id+" não encontrada"));
        return shirtMapper.EntityToResponseDTO(shirt);
    }

    @Override
    public void deleteAllClothings() throws Exception {
        shirtRepository.deleteAll();
    }

    @Override
    public List<String> getColumnsNames() {
        return shirtRepository.getNames();
    }
}
