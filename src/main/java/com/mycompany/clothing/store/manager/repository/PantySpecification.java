/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Panty;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author moise
 */
public interface PantySpecification {
    
    public static Specification<Panty> withFilters(Panty filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if(filter.getColor() != null && !filter.getColor().isBlank()) {
                predicates.add(builder.like(root.get("color"), "%" + filter.getColor() + "%"));
            }
            
            if(filter.getPrice() != -1) {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), filter.getPrice()));
            }
            
            if(filter.getQuantity() != -1) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("quantity"), filter.getQuantity()));
            } 
            
            if(filter.getClothingType() != null) {
                predicates.add(builder.equal(root.get("clothingType"), filter.getClothingType()));
            }
            
            if(filter.getFabric() != null && !filter.getFabric().isBlank()) {
                predicates.add(builder.like(root.get("fabric"), filter.getFabric()));
            }
            
            if(filter.getBrand() != null && !filter.getBrand().isBlank()) {
                predicates.add(builder.like(root.get("brand"), filter.getBrand()));
            }
            
            if(filter.getStyle() != null && !filter.getStyle().isBlank()) {
                predicates.add(builder.like(root.get("style"), filter.getStyle()));
            }
            
            if(filter.getGender() != null) {
                predicates.add(builder.equal(root.get("gender"), filter.getGender()));
            }
            
            if(filter.getPattern() != null && !filter.getPattern().isBlank()) {
                predicates.add(builder.like(root.get("pattern"), filter.getPattern()));
            }
            
            if(filter.getSize() != null) {
                predicates.add(builder.equal(root.get("size"), filter.getSize()));
            }
            
            if(filter.getWaist() != null) {
                predicates.add(builder.equal(root.get("waist"), filter.getWaist()));
            }
            
            if(filter.getLining() != null) {
                predicates.add(builder.equal(root.get("lining"), filter.getLining()));
            }
            
            if(filter.getCut() != null) {
                predicates.add(builder.equal(root.get("cut"), filter.getCut()));
            }
            
            if(filter.getDetail() != null) {
                predicates.add(builder.equal(root.get("detail"), filter.getDetail()));
            }
            
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
