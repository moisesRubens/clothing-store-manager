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
import com.mycompany.clothing.store.manager.domain.dto.PantyResponseDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.CutType;
import com.mycompany.clothing.store.manager.domain.enums.DetailPanty;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.LiningType;
import com.mycompany.clothing.store.manager.domain.enums.Size;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import com.mycompany.clothing.store.manager.interfaces.IClothingMapper;
import com.mycompany.clothing.store.manager.interfaces.IClothingRename;
import com.mycompany.clothing.store.manager.repository.IPantyRepository;
import com.mycompany.clothing.store.manager.repository.PantySpecification;
import com.mycompany.clothing.store.manager.service.mapper.PantyMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author moise
 */
@Service
public class PantyService implements IClothingService {

    private IPantyRepository pantyRepository;
    private IClothingMapper pantyMapper;

    @Autowired
    public PantyService(IPantyRepository pantyRepository, PantyMapper pantyMapper) {
        this.pantyRepository = pantyRepository;
        this.pantyMapper = pantyMapper;
    }
    
    @Override
    public void registerClothing(ClothingRequestDTO dto) throws Exception {
        Panty panty = pantyMapper.RequestDTOToEntity(dto);
        Optional<Panty> found = pantyRepository.findExistingPanty(panty.getColor(), panty.getFabric(), panty.getBrand(),
                panty.getGender(), panty.getPattern(), panty.getStyle(), panty.getDetail(), panty.getSize(), panty.getLining(), panty.getCut(),
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
        Panty panty = pantyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Calcinha de id " + id + " não encontrada"));
        int newQuantity = panty.getQuantity() - quantity;
        if(newQuantity < 0) {
            throw new IllegalStateException("Quantidade insuficiente para a remoção do modelo de calcinha: "+id);
        } else if(newQuantity == 0) {
            pantyRepository.delete(panty);
        } else {
            panty.setQuantity(newQuantity);
            pantyRepository.save(panty);
        }
        
    }

    @Override
    public List<PantyResponseDTO> getAllClothings() throws Exception {
        List <Panty> list = pantyRepository.findAll();
        System.out.println("Lista de Panty: " + list);
        List<PantyResponseDTO> listDTO = new ArrayList<>();
        for(Panty p : list) {
            listDTO.add(pantyMapper.EntityToResponseDTO(p));
        }
        System.out.println("meus dto: "+listDTO);
        return listDTO;
    }

    @Override
    public List<PantyResponseDTO> getListClothings(ClothingRequestDTO dto) throws Exception {
        Panty pantyFilter = pantyMapper.RequestDTOToEntity(dto);
        List<Panty> filteredPants = pantyRepository.findAll(PantySpecification.withFilters(pantyFilter));
        List<PantyResponseDTO> list = new ArrayList<>();
        
        for(Panty panty: filteredPants) {
            list.add(pantyMapper.EntityToResponseDTO(panty));
        }
        return list;
    }

    @Override
    public void deleteClothing(Integer id) throws Exception {
        pantyRepository.deleteById(id);
    }

    @Override
    public void deleteAllClothings() throws Exception {
        pantyRepository.deleteAll();
    }

    @Override
    public PantyResponseDTO getClothingById(Integer id) throws Exception {
        Panty panty = pantyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Calçinha com id: "+id+" não existente."));
        return pantyMapper.EntityToResponseDTO(panty);
    }

    @Override
    public List<String> getColumnsNames() {
        return pantyRepository.getNames();
    }
}
