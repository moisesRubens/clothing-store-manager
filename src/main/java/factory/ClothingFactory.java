/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;

/**
 *
 * @author moise
 */
public abstract class ClothingFactory {
    public abstract Clothing createClothing(ClothingRequestDTO dto);
}
