/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author moise
 */
public class ShirtRepository extends ClothingRepository {

    public ShirtRepository(EntityManager em) {
        super(em);
    }

    @Override
    public Clothing getClothingById(Integer id) {
        return null;
    }

    @Override
    public Integer existsClothing(Clothing clothing, String query) throws Exception {
        em.getTransaction().begin();
        Integer id = -1;
        try {
            TypedQuery<Shirt> queryShirt;
            Shirt shirt = (Shirt) clothing;

            queryShirt = em.createQuery(query, Shirt.class)
                    .setParameter("color", shirt.getColor())
                    .setParameter("brand", shirt.getBrand())
                    .setParameter("size", shirt.getSize())
                    .setParameter("price", query)
                    .setParameter("sleeve", shirt.getSleeve())
                    .setParameter("collar", shirt.getCollar())
                    .setParameter("pocket", shirt.getPocket());

            if (!isBlank(shirt.getFabric())) {
                queryShirt.setParameter("fabric", shirt.getFabric());
            }
            if (!isBlank(shirt.getStyle())) {
                queryShirt.setParameter("style", shirt.getStyle());
            }
            if (!isBlank(shirt.getPattern())) {
                queryShirt.setParameter("pattern", shirt.getPattern());
            }
            if (!isBlank(shirt.getClosureType())) {
                queryShirt.setParameter("closureType", shirt.getClosureType());
            }

            if (!queryShirt.getResultList().isEmpty()) {
                id = queryShirt.getResultList().get(0).getId();
            }
        } catch (Exception e) {
            handleException(e);
        } finally {
            return id;
        }
    }
}
