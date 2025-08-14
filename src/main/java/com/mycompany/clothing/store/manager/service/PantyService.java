/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.service;

import com.mycompany.clothing.store.manager.interfaces.IClothingService;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.Panty;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ClothingResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.CutType;
import com.mycompany.clothing.store.manager.domain.enums.DetailsPantie;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.LiningType;
import com.mycompany.clothing.store.manager.domain.enums.Size;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import com.mycompany.clothing.store.manager.interfaces.IClothingMapper;
import com.mycompany.clothing.store.manager.repository.IPantyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moise
 */
public class PantyService implements IClothingService {

    @Autowired
    private IPantyRepository pantyRepository;
    @Autowired
    private IClothingMapper pantyMapper;

    @Override
    public void registerClothing(ClothingRequestDTO dto) throws Exception {
        Panty panty = pantyMapper.RequestDTOToEntity(dto);
        Optional<Panty> found = pantyRepository.findExistingPant(panty.getColor() , panty.getClothingType(), panty.getFabric(), panty.getBrand(),
                panty.getGender(), panty.getPattern(), panty.getStyle(), panty.getDetails(), panty.getSize(), panty.getLining(), panty.getCut(),
                panty.getWaist());

        if (found.isPresent()) {
            Panty foundPanty = found.get();
            foundPanty.setQuantity(foundPanty.getQuantity() + panty.getQuantity());
            pantyRepository.save(foundPanty);
        } else {
            pantyRepository.save(panty);
        }
    }

    @Override
    public void decrementClothing(Integer id, Integer quantity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getAllClothings() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends ClothingResponseDTO> List<T> getListClothings(ClothingRequestDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
