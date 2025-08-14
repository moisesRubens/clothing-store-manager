/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.Panty;
import com.mycompany.clothing.store.manager.domain.enums.ClothingType;
import com.mycompany.clothing.store.manager.domain.enums.CutType;
import com.mycompany.clothing.store.manager.domain.enums.DetailsPantie;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.LiningType;
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
public interface IPantyRepository extends JpaRepository<Panty, Integer>, JpaSpecificationExecutor<Panty>{
    
    @Query("""
    SELECT p FROM Panty p
    WHERE p.color = :color
    AND p.clothingType = :clothingType
    AND (:fabric IS NULL OR p.fabric = :fabric)
    AND p.brand = :brand
    AND (:style IS NULL OR p.style = :style)
    AND p.gender = :gender
    AND (:pattern IS NULL OR p.pattern = :pattern)
    AND (:lining IS NULL OR p.lining = :lining)
    AND p.size = :size
    AND p.waist = :waist
    AND (:cut IS NULL OR p.cut = :cut)
    AND (:detail IS NULL OR p.detail = :detail)
    """)
    Optional<Panty> findExistingPant(
            @Param("color") String color,
            @Param("clothingType") ClothingType clothingType,
            @Param("fabric") String fabric,
            @Param("brand") String brand,
            @Param("gender") Gender gender,
            @Param("pattern") String pattern,
            @Param("style") String style,
            @Param("details") DetailsPantie detail,
            @Param("size") Size size, 
            @Param("lining") LiningType lining,
            @Param("cut") CutType cut,
            @Param("waist") WaistType waist
    );
}
