/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;

/**
 *
 * @author moise
 */
public class ShirtFactory implements IClothingFactory {

    @Override
    public Shirt createClothing(ClothingRequestDTO dto) {
        ShirtRequestDTO shirtData = (ShirtRequestDTO) dto;

        return new Shirt(shirtData.color(), shirtData.price(), shirtData.quantity(),
                shirtData.clothingType(), shirtData.fabric(), shirtData.brand(),
                shirtData.style(), shirtData.gender(), shirtData.pattern(), shirtData.pocket(),
                shirtData.closureType(), shirtData.size(), shirtData.sleeve(), shirtData.collar());
    }
    
}
