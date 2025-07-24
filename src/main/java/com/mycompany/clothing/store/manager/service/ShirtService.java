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
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import factory.ClothingFactory;
import factory.IClothingFactory;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author moise
 */
public class ShirtService extends ClothingService {
    private IClothingFactory shirtFactory;
    
    
    public ShirtService(ClothingRepository shirtRepository, IClothingFactory clothingFactory) {
        super(shirtRepository);
        this.shirtFactory = clothingFactory;
    }

    @Override
    public Shirt createClothing(ClothingRequestDTO clothingData) {
        ShirtRequestDTO shirtData = (ShirtRequestDTO) clothingData;

        return new Shirt(shirtData.color(), shirtData.price(), shirtData.quantity(),
                shirtData.clothingType(), shirtData.fabric(), shirtData.brand(),
                shirtData.style(), shirtData.gender(), shirtData.pattern(), shirtData.pocket(),
                shirtData.closureType(), shirtData.size(), shirtData.sleeve(), shirtData.collar());
    }

    void addShirtInDatabase(ClothingRequestDTO clothingData) throws Exception {
        Clothing clothing = shirtFactory.createClothing(clothingData);
        String query = clothing.createQuery();
        Integer id = clothingRepository.existsClothing(clothing, query);

        if (id == -1) {
            clothingRepository.saveClothing(clothing);
        } else {
            incrementClothing(id, clothing.getQuantity());
        }
    }

    @Override
    public void removeClothingById(Integer id) {

    }

    @Override
    public List<ClothingResponseDTO> getAllClothings() {
        return null;
    }

    private Boolean containsAttribute(Shirt data) {
        return (!data.getColor().isBlank() || !data.getBrand().isBlank() || !data.getPattern().isBlank()
                || !data.getFabric().isBlank() || EnumSet.allOf(Gender.class).contains(data.getGender())
                || !data.getStyle().isBlank() || data.getQuantity() != -1 || data.getPrice() != -1
                || EnumSet.allOf(ShirtSize.class).contains(data.getSize()) || data.getCollar() != -1
                || data.getSleeve() != -1 || !data.getClosureType().isBlank() || data.getPocket() != -1);
    }
}
