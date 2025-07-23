/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;

/**
 *
 * @author moise
 */
public interface IClothingRepository {
    void saveClothing(Clothing clothing);
    void updateQuantityClothing(Integer id, Integer quantity);
    Integer existsClothing(String query);
    Clothing getClothingById(Integer id);
}
