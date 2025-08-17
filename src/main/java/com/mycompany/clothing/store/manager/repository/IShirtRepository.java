/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Panty;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.Size;
import jakarta.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author moise
 */
public interface IShirtRepository extends JpaRepository<Shirt, Integer>, JpaSpecificationExecutor<Shirt> {

    @Query("""
    select s.id from Shirt s where s.color = :color and
        s.clothingType = :clothingType and s.fabric = :fabric and
        s.brand = :brand and s.style = :style and
        s.gender = :gender and s.pattern = :pattern and
        s.pocket = :pocket and s.closureType = :closureType and
        s.size = :size and s.sleeve = :sleeve and s.collar = :collar""")
    Integer getId(@Param("color") String color, @Param("clothingType") ClothingType clothingType,
            @Param("fabric") String fabric, @Param("brand") String brand,
            @Param("style") String style, @Param("gender") Gender gender,
            @Param("pattern") String pattern, @Param("pocket") Integer pocket,
            @Param("closureType") String closureType, @Param("size") Size size,
            @Param("sleeve") Integer sleeve, @Param("collar") Integer collar
    );

    @Query("""
    SELECT s FROM Shirt s
    WHERE s.color = :color
    AND s.clothingType = :clothingType
    AND (:fabric IS NULL OR s.fabric = :fabric)
    AND s.brand = :brand
    AND (:style IS NULL OR s.style = :style)
    AND s.gender = :gender
    AND (:pattern IS NULL OR s.pattern = :pattern)
    AND s.pocket = :pocket
    AND (:closureType IS NULL OR s.closureType = :closureType)
    AND s.size = :size
    AND s.sleeve = :sleeve
    AND s.collar = :collar
    """)
    Optional<Shirt> findExistingShirt(
            @Param("color") String color,
            @Param("clothingType") ClothingType clothingType,
            @Param("fabric") String fabric,
            @Param("brand") String brand,
            @Param("style") String style,
            @Param("gender") Gender gender,
            @Param("pattern") String pattern,
            @Param("pocket") Integer pocket,
            @Param("closureType") String closureType,
            @Param("size") Size size,
            @Param("sleeve") Integer sleeve,
            @Param("collar") Integer collar
    );

    default List<String> getNames() {
        Field[] fields = Shirt.class.getDeclaredFields();
        List<String> names = new ArrayList<>();
        for (Field f : fields) {
            Column c = f.getAnnotation(Column.class);
            if(c != null) {
                names.add(c.name());
            } else {
                names.add(f.getName());
            }
        }
        return names;
    }
}
