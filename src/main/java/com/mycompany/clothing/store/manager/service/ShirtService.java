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
import com.mycompany.clothing.store.manager.domain.dto.ShirtResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.repository.IShirtRepository;
import com.mycompany.clothing.store.manager.repository.ShirtSpecification;
import com.mycompany.clothing.store.manager.service.mapper.ClothingMapper;
import com.mycompany.clothing.store.manager.service.mapper.ShirtMapper;
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
    private ClothingMapper shirtMapper;

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
        Optional<Shirt> found = shirtRepository.findExistingShirt(shirt.getColor(), shirt.getClothingType(), shirt.getFabric(),
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
    public List<ShirtResponseDTO> getListClothings(ClothingRequestDTO dto) {
        
        ShirtRequestDTO shirtDTO = (ShirtRequestDTO) dto;
        Shirt shirtFilter = (Shirt) shirtMapper.RequestDTOToEntity(shirtDTO);
        System.out.println(shirtFilter);
        List<Shirt> filteredShirts = shirtRepository.findAll(ShirtSpecification.withFilters(shirtFilter));

        return filteredShirts.stream()
                .map(shirt -> (ShirtResponseDTO) shirtMapper.EntityToResponseDTO(shirt))
                .toList();
    }

    @Override
    public List<ShirtResponseDTO> getAllClothings() {
        return shirtRepository.findAll().stream()
                .map(shirt -> (ShirtResponseDTO) shirtMapper.EntityToResponseDTO(shirt))
                .toList();
    }
}
