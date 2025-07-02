/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaJaExistenteException;
import com.mycompany.clothing.store.manager.configuration.exception.RoupaNaoExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import com.mycompany.clothing.store.manager.domain.enums.Gender;
import com.mycompany.clothing.store.manager.domain.enums.ShirtSize;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.QueryHint;
import jakarta.persistence.TypedQuery;
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
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    public void registerInDatabase(Clothing clothing) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            registerInDatabaseAux(em, clothing);
            em.getTransaction().commit();
        } catch (EntityExistsException e) {
            handleException(em, e);
            throw new RoupaJaExistenteException("ROUPA JA CADASTRADA NO BANCO DE DADOS");
        } catch (Exception e) {
            handleException(em, e);
            throw new RuntimeException("ERRO AO CADASTRAR", e);
        } finally {
            em.close();
        }
    }

    public List consult(Clothing clothing, Boolean hasAtribute) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return consultAux(em, clothing, hasAtribute);
        } catch (Exception e) {
            handleException(em, e);
            throw e;
        } finally {
            em.close();
        }
    }

    private List consultAux(EntityManager em, Clothing clothing, Boolean hasAtributes) throws Exception {
        List list = null;

        if (clothing instanceof Shirt shirt) {
            String queryStr = "SELECT s FROM Shirt s WHERE";
            TypedQuery<Shirt> query;

            if (hasAtributes == true) {
                queryStr += " 1=1";

                queryStr += " AND s.sleeve = :sleeve";
                queryStr += " AND s.collar = :collar";
                if (shirt.getPrice() != -1) {
                    queryStr += " AND s.price <= :price";
                }
                if (shirt.getQuantity() != -1) {
                    queryStr += " AND s.quantity >= :quantity";
                }
                if (shirt.getPocket() != -1) {
                    queryStr += " AND s.pocket = :pocket";
                }
                if (!isEmptyOrBlank(shirt.getColor())) {
                    queryStr += " AND s.color LIKE :color";
                }
                if (!isEmptyOrBlank(shirt.getFabric())) {
                    queryStr += " AND s.fabric LIKE :fabric";
                }
                if (!isEmptyOrBlank(shirt.getBrand())) {
                    queryStr += " AND s.brand LIKE :brand";
                }
                if (!isEmptyOrBlank(shirt.getStyle())) {
                    queryStr += " AND s.style LIKE :style";
                }
                if (!isEmptyOrBlank(shirt.getPattern())) {
                    queryStr += " AND s.pattern LIKE :pattern";
                }
                if (!isEmptyOrBlank(shirt.getClosureType())) {
                    queryStr += " AND s.closureType LIKE :closureType";
                }
                if (EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())) {
                    queryStr += " AND s.size LIKE :" + shirt.getSize();
                }
                if (EnumSet.allOf(Gender.class).contains(shirt.getGender())) {
                    queryStr += " AND s.gender LIKE :" + shirt.getGender();
                }

                query = em.createQuery(queryStr, Shirt.class);

                query.setParameter("sleeve", shirt.getSleeve());
                query.setParameter("collar", shirt.getCollar());
                if (shirt.getPocket() != -1) {
                    query.setParameter("pocket", shirt.getPocket());
                }
                if (!isEmptyOrBlank(shirt.getColor())) {
                    query.setParameter("color", "%" + shirt.getColor() + "%");
                }
                if (!isEmptyOrBlank(shirt.getFabric())) {
                    query.setParameter("fabric", shirt.getFabric());
                }
                if (!isEmptyOrBlank(shirt.getBrand())) {
                    query.setParameter("brand", shirt.getBrand());
                }
                if (shirt.getPrice() != -1) {
                    query.setParameter("price", shirt.getPrice());
                }
                if (shirt.getQuantity() != -1) {
                    query.setParameter("quantity", shirt.getQuantity());
                }
                if (!isEmptyOrBlank(shirt.getBrand())) {
                    query.setParameter("style", shirt.getStyle());
                }
                if (!isEmptyOrBlank(shirt.getPattern())) {
                    query.setParameter("pattern", shirt.getPattern());
                }
                if (!isEmptyOrBlank(shirt.getClosureType())) {
                    query.setParameter("closureType", shirt.getClosureType());
                }
                if (EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())) {
                    query.setParameter(shirt.getSize().toString(), shirt.getSize());
                }
                if (EnumSet.allOf(Gender.class).contains(shirt.getGender())) {
                    query.setParameter(shirt.getGender().toString(), shirt.getGender());
                }
            } else {
                queryStr += " 1=0";
                query = em.createQuery(queryStr, Shirt.class);
            }
            list = query.getResultList();
        }
        if (list.isEmpty()) {
            throw new RoupaNaoExistenteException("MODELO DE ROUPA INEXISTENTE");
        }
        return list;
    }

    public Clothing getClothingById(Integer id) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            return getClothingByIdAux(em, id);
        } catch (Exception e) {
            handleException(em, e);
            throw e;
        } finally {
            em.close();
        }
    }

    public void updateData(Clothing clothing) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            updateDataAux(em, clothing);
            em.getTransaction().commit();
        } catch (Exception e) {
            handleException(em, e);
            throw e;
        } finally {
            em.close();
        }
    }

    private void updateDataAux(EntityManager em, Clothing clothing) throws Exception {
        em.merge(clothing);
        em.createQuery("DELETE FROM Clothing WHERE quantity = 0")
                .executeUpdate();
    }
        

    private Clothing getClothingByIdAux(EntityManager em, Integer id) throws Exception {
        return em.createQuery("SELECT s FROM Clothing s WHERE s.id = :id ", Clothing.class)
                .setParameter("id", id).getSingleResult();
    }

    private void registerInDatabaseAux(EntityManager em, Clothing clothing) throws Exception {
        if (clothing instanceof Shirt shirt) {
            em.persist(shirt);
        }
    }

    private void handleException(EntityManager em, Exception e) {
        logger.log(Level.WARNING, "ERRO", e);
        if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
        }
    }

    private void shutDown() {
        emf.close();
    }

    private boolean isEmptyOrBlank(String str) {
        return (str.isBlank() || str.isEmpty());
    }

    /*+
                                  "OR s.id = :id " +
                                  "OR s.brand LIKE :brand " +
                                  "OR s.pattern LIKE :pattern " +
                                  "OR s.clothingType = :clothingType " +
                                  "OR s.closureType LIKE :closureType " +
                                  "OR s.fabric LIKE :fabric " +
                                  "OR s.style LIKE :style" +  
                                  
                                  "OR s.shirtSize = :shirtSize"
    
    .setParameter("id", shirt.getId()).setParameter("brand", "%" + shirt.getBrand()+ "%")
                    .setParameter("pattern", "%" + shirt.getPattern()+ "%").setParameter("clothingType", shirt.getClothingType())
                    .setParameter("fabric", "%" + shirt.getFabric()+ "%").setParameter("style", "%" + shirt.getStyle()+ "%")
                    .setParameter("shirtSize", shirt.getSize())
                    .setParameter("closureType", "%" + shirt.getClosureType()+ "%")
     */
}
