/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaJaExistenteException;
import com.mycompany.clothing.store.manager.configuration.exception.RoupaNaoExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Pant;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.dto.ClothingRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.PantRequestDTO;
import com.mycompany.clothing.store.manager.domain.dto.ShirtRequestDTO;
import com.mycompany.clothing.store.manager.domain.enums.ClothingPiece;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.HemType;
import com.mycompany.clothing.store.manager.domain.enums.PantieLengthType;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import com.mycompany.clothing.store.manager.domain.enums.WaistType;
import com.mycompany.clothing.store.manager.service.ClothingMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
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
        List<Clothing> list = new ArrayList<>();
        try {
            list = em.createQuery(query).getResultList();
        } catch (PersistenceException e) {
            handleException(e);
            throw e;
        }
        return list;
    }

    public void incrementClothing(Integer id, ClothingRequestDTO data) {
        try {
            em.getTransaction().begin();
            if(data instanceof ShirtRequestDTO) {
            em.createQuery("UPDATE Clothing c SET c.quantity = c.quantity + :quantity WHERE c.id = :id", Shirt.class)
                    .setParameter("quantity", data.quantity())
                    .setParameter("id", id).executeUpdate();
            } else if(data instanceof PantRequestDTO) {
                em.createQuery("UPDATE Clothing c SET c.quantity = c.quantity + :quantity WHERE c.id = :id", Pant.class)
                    .setParameter("quantity", data.quantity())
                    .setParameter("id", id).executeUpdate();
            }
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
        Integer id = -1;
        
        if (clothing instanceof ShirtRequestDTO shirt) {
        TypedQuery<Shirt> query;
        
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
                id = query.getResultList().get(0).getId();
            }
        } else if(clothing instanceof PantRequestDTO pantie) {
        TypedQuery<Pant> query;
            queryStr = "SELECT p FROM Pant p WHERE p.size = :size"
                    + " AND p.length = :length"
                    + " AND p.brand = :brand"
                    + " AND p.color = :color"
                    + " AND p.pocket = :pocket"
                    + " AND p.gender = :gender";
            
            if (!isBlank(pantie.fabric())) {
                queryStr += " AND p.fabric LIKE :fabric";
            } else {
                queryStr += " AND p.fabric IS NULL";
            }
            if (!isBlank(pantie.style())) {
                queryStr += " AND p.style LIKE :style";
            } else {
                queryStr += " AND p.style IS NULL";
            }
            if (!isBlank(pantie.pattern())) {
                queryStr += " AND p.pattern LIKE :pattern ";
            } else {
                queryStr += " AND p.pattern  IS NULL";
            }
            if (!isBlank(pantie.closureType())) {
                queryStr += " AND p.closureType LIKE :closureType ";
            } else {
                queryStr += " AND p.closureType  IS NULL";
            }
            if (EnumSet.allOf(HemType.class).contains(pantie.hemType())) {
                queryStr += " AND p.hemType = :hemType";
            } else {
                queryStr += " AND p.hemType  IS NULL";
            }
            if (EnumSet.allOf(WaistType.class).contains(pantie.waistType())) {
                queryStr += " AND p.waistType = :waistType";
            } else {
                queryStr += " AND p.waistType  IS NULL";
            }
            if (EnumSet.allOf(PantieLengthType.class).contains(pantie.length())) {
                queryStr += " AND p.length = :length";
            } else {
                queryStr += " AND p.waistType  IS NULL";
            }
            query = em.createQuery(queryStr, Pant.class);

            query.setParameter("size", pantie.size())
                    .setParameter("length", pantie.length())
                    .setParameter("brand", pantie.brand())
                    .setParameter("color", pantie.color())
                    .setParameter("gender", pantie.gender())
                    .setParameter("pocket", pantie.pocket());
            
            if (!isBlank(pantie.fabric())) {
                query.setParameter("fabric", pantie.fabric());
            }
            if (!isBlank(pantie.style())) {
                query.setParameter("style", pantie.style());
            }
            if (!isBlank(pantie.pattern())) {
                query.setParameter("pattern", pantie.pattern());
            }
            if (!isBlank(pantie.closureType())) {
                query.setParameter("closureType", pantie.closureType());
            }
            if (pantie.hemType() != null) {
                query.setParameter("hemType", pantie.hemType());
            }
            if (pantie.waistType() != null) {
                query.setParameter("waistType", pantie.waistType());
            }
            if (!query.getResultList().isEmpty()) {
                id = query.getResultList().get(0).getId();
            }
        }

        return id;
    }

    private List<Clothing> consultAux(ClothingRequestDTO data, String query) throws Exception {
        List list = null;
        System.out.println(query);
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
            if (!shirtData.fabric().isBlank()) {
                queryShirt.setParameter("fabric", shirtData.fabric());
            }
            if (!shirtData.color().isBlank()) {
                queryShirt.setParameter("color", "%" + shirtData.color() + "%");
            }
            if (!shirtData.brand().isBlank()) {
                queryShirt.setParameter("brand", shirtData.brand());
            }
            if (!shirtData.style().isBlank()) {
                queryShirt.setParameter("style", shirtData.style());
            }
            if (!shirtData.pattern().isBlank()) {
                queryShirt.setParameter("pattern", shirtData.pattern());
            }
            if (!shirtData.closureType().isBlank()) {
                queryShirt.setParameter("closureType", shirtData.closureType());
            }

            list = queryShirt.getResultList();
        } else if(data instanceof PantRequestDTO dataPantie) {
            TypedQuery<Pant> queryPantie;

            queryPantie = em.createQuery(query, Pant.class);
            
            
            if (EnumSet.allOf(Gender.class).contains(dataPantie.gender())) {
                queryPantie.setParameter("gender", dataPantie.gender());
            }
            if (EnumSet.allOf(HemType.class).contains(dataPantie.hemType())) {
                queryPantie.setParameter("hemType", dataPantie.hemType());
            }
            if (EnumSet.allOf(WaistType.class).contains(dataPantie.waistType())) {
                queryPantie.setParameter("waistType", dataPantie.waistType());
            }
            if (EnumSet.allOf(PantieLengthType.class).contains(dataPantie.length())
                    && query.contains(":length")) {
                queryPantie.setParameter("length", dataPantie.length());
            }
            if (dataPantie.pocket() != -1) {
                queryPantie.setParameter("pocket", dataPantie.pocket());
            }
            if (dataPantie.price() != -1) {
                queryPantie.setParameter("price", dataPantie.price());
            }
            if (dataPantie.quantity() != -1) {
                queryPantie.setParameter("quantity", dataPantie.quantity());
            }
            if (dataPantie.size() != -1) {
                queryPantie.setParameter("size", dataPantie.size());
            }
            if (!dataPantie.fabric().isBlank()) {
                queryPantie.setParameter("fabric", dataPantie.fabric());
            }
            if (!dataPantie.color().isBlank()) {
                queryPantie.setParameter("color", "%" + dataPantie.color() + "%");
            }
            if (!dataPantie.brand().isBlank()) {
                queryPantie.setParameter("brand", dataPantie.brand());
            }
            if (!dataPantie.style().isBlank()) {
                queryPantie.setParameter("style", dataPantie.style());
            }
            if (!dataPantie.pattern().isBlank()) {
                queryPantie.setParameter("pattern", dataPantie.pattern());
            }
            if (!dataPantie.closureType().isBlank()) {
                queryPantie.setParameter("closureType", dataPantie.closureType());
            }

            list = queryPantie.getResultList();
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
        } catch (RoupaNaoExistenteException e) {
            throw e;
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
        Clothing clothing;
        try {
            clothing = em.createQuery("SELECT s FROM Clothing s WHERE s.id = :id ", Clothing.class)
                .setParameter("id", id).getSingleResult();
        } catch(NoResultException e) {
            throw new RoupaNaoExistenteException("ROUPA NÃO EXISTENTE NO SISTEMA");
        }
        return clothing;
    }

    private void registerInDatabaseAux(Clothing clothing) throws Exception {
        em.persist(clothing);
    }

    private void handleException(Exception e) {
        logger.log(Level.SEVERE, "ERRO", e);
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    private boolean isBlank(String str) {
        return (str == null || str.isEmpty() || str.isBlank());
    }

    public Clothing getById(Integer id) throws Exception{
        try {
            return em.createQuery("SELECT s FROM Shirt s WHERE s.id = :id", Shirt.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            throw new RoupaNaoExistenteException();
        } catch (PersistenceException e) {
            handleException(e);
            throw e;
        }
    }
}
