/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaJaExistenteException;
import com.mycompany.clothing.store.manager.configuration.exception.RoupaNaoExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingPiece;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.service.ClothingMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.QueryHint;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moise
 */
public class ClothingRepository {

    private static final Logger logger = Logger.getLogger(ClothingRepository.class.getName());
    EntityManager em;

    public ClothingRepository(EntityManager em) {
        this.em = em;
    }

    public Integer getTotalQuantity() {
        List<Clothing> list = em.createQuery("SELECT s FROM Shirt s").getResultList();

        return list.size();
    }

    public List<Clothing> getAll(String query) {
        return em.createQuery(query).getResultList();
    }

    public void incrementClothing(Integer id, Integer quantity) {
        try {
            em.getTransaction().begin();
            em.createQuery("UPDATE Clothing c SET c.quantity = c.quantity + :quantity WHERE c.id = :id")
                    .setParameter("quantity", quantity)
                    .setParameter("id", id).executeUpdate();

            em.getTransaction().commit();
            em.clear();
        } catch (PersistenceException e) {
            handleException(e);
            throw e;
        }
    }

    public void registerInDatabase(Clothing clothing) throws Exception {
        em.getTransaction().begin();
        try {
            registerInDatabaseAux(clothing);
            em.getTransaction().commit();
        } catch (EntityExistsException e) {
            handleException(e);
            throw new RoupaJaExistenteException("ROUPA JA CADASTRADA NO BANCO DE DADOS");
        } catch (Exception e) {
            handleException(e);
            throw new RuntimeException("ERRO AO CADASTRAR", e);
        }
    }

    public List<Clothing> consult(ClothingRequestDTO data, String query) throws Exception {
        try {
            return consultAux(data, query);
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public Integer existClothing(ClothingRequestDTO clothing) {
        String queryStr;
        TypedQuery<Shirt> query;
        Integer id = -1;

        if (clothing instanceof ShirtRequestDTO shirt) {
            queryStr = "SELECT s FROM Shirt s WHERE s.sleeve = :sleeve"
                    + " AND s.collar = :collar"
                    + " AND s.pocket = :pocket"
                    + " AND s.color = :color"
                    + " AND s.brand = :brand"
                    + " AND s.size = :size"
                    + " AND s.gender = :gender";

            if (!isBlank(shirt.fabric())) {
                queryStr += " AND s.fabric LIKE :fabric";
            } else {
                queryStr += " AND s.fabric IS NULL";
            }
            if (!isBlank(shirt.style())) {
                queryStr += " AND s.style LIKE :style";
            } else {
                queryStr += " AND s.style IS NULL";
            }
            if (!isBlank(shirt.pattern())) {
                queryStr += " AND s.pattern LIKE :pattern ";
            } else {
                queryStr += " AND s.pattern  IS NULL";
            }
            if (!isBlank(shirt.closureType())) {
                queryStr += " AND s.closureType LIKE :closureType ";
            } else {
                queryStr += " AND s.closureType  IS NULL";
            }

            query = em.createQuery(queryStr, Shirt.class);

            query.setParameter("sleeve", shirt.sleeve())
                    .setParameter("collar", shirt.collar())
                    .setParameter("pocket", shirt.pocket())
                    .setParameter("color", shirt.color())
                    .setParameter("gender", shirt.gender())
                    .setParameter("size", shirt.size())
                    .setParameter("brand", shirt.brand());

            if (!isBlank(shirt.fabric())) {
                query.setParameter("fabric", shirt.fabric());
            }
            if (!isBlank(shirt.style())) {
                query.setParameter("style", shirt.style());
            }
            if (!isBlank(shirt.pattern())) {
                query.setParameter("pattern", shirt.pattern());
            }
            if (!isBlank(shirt.closureType())) {
                query.setParameter("closureType", shirt.closureType());
            }

            if (!query.getResultList().isEmpty()) {
                System.out.println(query.getResultList().get(0));
                id = query.getResultList().get(0).getId();
            }
        }

        return id;
    }

    private List<Clothing> consultAux(ClothingRequestDTO data, String query) throws Exception {
        List list = null;

        if (data instanceof ShirtRequestDTO shirtData) {
            TypedQuery<Shirt> queryShirt;

            queryShirt = em.createQuery(query, Shirt.class);

            if (shirtData.sleeve() != -1) {
                queryShirt.setParameter("sleeve", shirtData.sleeve());
            }
            if (shirtData.collar() != -1) {
                queryShirt.setParameter("collar", shirtData.collar());
            }
            if (shirtData.pocket() != -1) {
                queryShirt.setParameter("pocket", shirtData.pocket());
            }
            if (shirtData.price() != -1) {
                queryShirt.setParameter("price", shirtData.price());
            }
            if (shirtData.quantity() != -1) {
                queryShirt.setParameter("quantity", shirtData.quantity());    
            }
            if (EnumSet.allOf(ShirtSize.class).contains(shirtData.size())) {
                queryShirt.setParameter("size", shirtData.size());
            }
            if (EnumSet.allOf(Gender.class).contains(shirtData.gender())) {
                queryShirt.setParameter("gender", shirtData.gender());
            }
            if(!shirtData.fabric().isBlank()) {
            queryShirt.setParameter("fabric", shirtData.fabric());
            }
            if(!shirtData.color().isBlank()) {
                queryShirt.setParameter("color", "%" + shirtData.color() + "%");
            }
            if(!shirtData.brand().isBlank()) {
            queryShirt.setParameter("brand", shirtData.brand());
            }
            if(!shirtData.style().isBlank()) {
            queryShirt.setParameter("style", shirtData.style());
            }
            if(!shirtData.pattern().isBlank()) {
            queryShirt.setParameter("pattern", shirtData.pattern());
            }
            if(!shirtData.closureType().isBlank()) {
            queryShirt.setParameter("closureType", shirtData.closureType());
            }
            
            list = queryShirt.getResultList();
        }

        if (list == null) {
            return null;
        }

        if (list.isEmpty()) {
            throw new RoupaNaoExistenteException("MODELO DE ROUPA INEXISTENTE");
        }
        return list;
    }

    public Clothing getClothingById(Integer id) throws Exception {
        try {
            return getClothingByIdAux(id);
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public void updateData(Clothing clothing) throws Exception {
        em.getTransaction().begin();
        try {
            updateDataAux(clothing);
            em.getTransaction().commit();
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    private void updateDataAux(Clothing clothing) throws Exception {
        em.merge(clothing);
        em.flush();
        em.createQuery("DELETE FROM Clothing WHERE quantity = 0")
                .executeUpdate();
    }

    private Clothing getClothingByIdAux(Integer id) throws Exception {
        List<Clothing> result = em.createQuery("SELECT s FROM Clothing s WHERE s.id = :id ", Clothing.class)
                .setParameter("id", id).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    private void registerInDatabaseAux(Clothing clothing) throws Exception {
        em.persist(clothing);
    }

    private void handleException(Exception e) {
        logger.log(Level.WARNING, "ERRO", e);
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    private boolean isBlank(String str) {
        return (str == null || str.isEmpty() || str.isBlank());
    }

    public List getById(Integer id) {
        return em.createQuery("SELECT s FROM Shirt s WHERE s.id = :id")
                .setParameter("id", id).getResultList();
    }
}
