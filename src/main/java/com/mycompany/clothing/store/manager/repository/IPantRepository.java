/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.HemType;
import com.mycompany.clothing.store.manager.domain.enums.PantLengthType;
import com.mycompany.clothing.store.manager.domain.enums.Size;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author moise
 */
public interface IPantRepository extends JpaRepository<Pant, Integer>, JpaSpecificationExecutor<Pant> {
    
    @Query("""
    select p.id from Pant p where p.color = :color and
        p.clothingType = :clothingType and p.fabric = :fabric and
        p.brand = :brand and p.style = :style and
        p.gender = :gender and p.pattern = :pattern and
        p.pocket = :pocket and p.closureType = :closureType and
        p.size = :size and p.length = :length and p.hemType = :hemType and 
        p.waistType = :waistType and p.size = :size""")
    Integer getId(@Param("color") String color, @Param("clothingType") ClothingType clothingType,
            @Param("fabric") String fabric, @Param("brand") String brand,
            @Param("style") String style, @Param("gender") Gender gender,
            @Param("pattern") String pattern, @Param("pocket") Integer pocket,
            @Param("closureType") String closureType, @Param("size") Integer size,
            @Param("hemType") HemType hemType, @Param("waistType") WaistType waistType,
            @Param("length") PantLengthType length
    );

    @Query("""
    SELECT p FROM Pant p
    WHERE p.color = :color
    AND p.clothingType = :clothingType
    AND (:fabric IS NULL OR p.fabric = :fabric)
    AND p.brand = :brand
    AND (:style IS NULL OR p.style = :style)
    AND p.gender = :gender
    AND (:pattern IS NULL OR p.pattern = :pattern)
    AND p.pocket = :pocket
    AND (:closureType IS NULL OR p.closureType = :closureType)
    AND p.size = :size
    AND p.length = :length
    AND p.hemType = :hemType
    AND p.waistType = :waistType
    """)
    Optional<Pant> findExistingPant(
            @Param("color") String color,
            @Param("clothingType") ClothingType clothingType,
            @Param("fabric") String fabric,
            @Param("brand") String brand,
            @Param("style") String style,
            @Param("gender") Gender gender,
            @Param("pattern") String pattern,
            @Param("pocket") Integer pocket,
            @Param("closureType") String closureType,
            @Param("size") Integer size, 
            @Param("hemType") HemType hemType,
            @Param("length") PantLengthType length,
            @Param("waistType") WaistType waistType
    );
}
