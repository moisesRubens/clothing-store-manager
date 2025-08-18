/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.enums.HemType;
import com.mycompany.clothing.store.manager.domain.enums.PantLengthType;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author moise
 */
public class PantSpecification {
    
    public static Specification<Pant> withFilters(Pant filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getColor() != null && !filter.getColor().isBlank()) {
                predicates.add(builder.like(root.get("color"), "%" + filter.getColor() + "%"));
            }

            if (filter.getPrice() != null && filter.getPrice() != -1) {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), filter.getPrice()));
            }

            if (filter.getQuantity() != null && filter.getQuantity() != -1) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("quantity"), filter.getQuantity()));
            }

            if (filter.getFabric() != null && !filter.getFabric().isBlank()) {
                predicates.add(builder.like(root.get("fabric"), "%" + filter.getFabric() + "%"));
            }

            if (filter.getBrand() != null && !filter.getBrand().isBlank()) {
                predicates.add(builder.like(root.get("brand"), "%" + filter.getBrand() + "%"));
            }

            if (filter.getStyle() != null && !filter.getStyle().isBlank()) {
                predicates.add(builder.like(root.get("style"), "%" + filter.getStyle() + "%"));
            }

            if (filter.getGender() != null) {
                predicates.add(builder.equal(root.get("gender"), filter.getGender()));
            }

            if (filter.getPattern() != null && !filter.getPattern().isBlank()) {
                predicates.add(builder.like(root.get("pattern"), "%" + filter.getPattern() + "%"));
            }

            if (filter.getPocket() != null && filter.getPocket() != -1) {
                predicates.add(builder.equal(root.get("pocket"), filter.getPocket()));
            }

            if (filter.getClosureType() != null && !filter.getClosureType().isBlank()) {
                predicates.add(builder.like(root.get("closureType"), "%" + filter.getClosureType() + "%"));
            }

            if (filter.getSize() != null && filter.getSize() != -1) {
                predicates.add(builder.equal(root.get("size"), filter.getSize()));
            }

            if (filter.getLength() != null) {
                predicates.add(builder.equal(root.get("length"), filter.getLength()));
            }
            
            if (filter.getHemType() != null) {
                predicates.add(builder.equal(root.get("hemType"), filter.getHemType()));
            }
            
            if (filter.getWaistType() != null) {
                predicates.add(builder.equal(root.get("waistType"), filter.getWaistType()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
