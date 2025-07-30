/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaNaoExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.service.mapper.ShirtMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author moise
 */
public class ShirtRepository extends ClothingRepository {

    public ShirtRepository(EntityManager em) {
        super(em);
    }

    @Override
    public Shirt getClothingById(Integer id) throws Exception {
        try {
            return (Shirt) em.createQuery("SELECT s FROM Shirt s WHERE s.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            handleException(e);
            throw new RoupaNaoExistenteException("Erro ao consultar roupa. Roupa inexistente");
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    @Override
    public Integer getClothingId(Clothing clothing, String query) throws Exception {
        Integer id = -1;
        try {
            TypedQuery<Shirt> queryShirt;
            Shirt shirt = (Shirt) clothing;

            queryShirt = em.createQuery(query, Shirt.class)
                    .setParameter("color", shirt.getColor())
                    .setParameter("brand", shirt.getBrand())
                    .setParameter("size", shirt.getSize())
                    .setParameter("price", shirt.getPrice())
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
        }
        return id;
    }

    @Override
    public List<Clothing> getAllClothing() throws Exception {
        return new ArrayList<>(
                em.createQuery("SELECT s FROM Shirt s", Shirt.class).getResultList());
    }

    @Override
    public List<Clothing> getClothings(Clothing clothing) throws Exception {
        try {
            Shirt shirt = (Shirt) clothing;
            String query = clothing.createQuery();
            System.out.println(query);
            TypedQuery<Shirt> shirtQuery;
            
            System.out.println(shirt);
            shirtQuery = em.createQuery(query, Shirt.class);

            if (!isBlank(shirt.getColor())) {
                shirtQuery.setParameter("color", shirt.getColor());
            }
            if (!isBlank(shirt.getBrand())) {
                shirtQuery.setParameter("brand", shirt.getBrand());
            }
            if (EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())) {
                shirtQuery.setParameter("size", shirt.getSize());
            }
            if(shirt.getPrice() != -1) {
                shirtQuery.setParameter("price", shirt.getPrice());
            }
            if (shirt.getSleeve() != -1) {
                shirtQuery.setParameter("sleeve", shirt.getSleeve());
            }
            if (shirt.getCollar() != -1) {
                shirtQuery.setParameter("collar", shirt.getCollar());
            }
            if(shirt.getPocket() != -1) {
                shirtQuery.setParameter("pocket", shirt.getPocket());
            }
            if (!isBlank(shirt.getFabric())) {
                shirtQuery.setParameter("fabric", shirt.getFabric());
            }
            if (!isBlank(shirt.getStyle())) {
                shirtQuery.setParameter("style", shirt.getStyle());
            }
            if (!isBlank(shirt.getPattern())) {
                shirtQuery.setParameter("pattern", shirt.getPattern());
            }
            if (!isBlank(shirt.getClosureType())) {
                shirtQuery.setParameter("closureType", shirt.getClosureType());
            }
            
            return new ArrayList<Clothing>(shirtQuery.getResultList());
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }
}
