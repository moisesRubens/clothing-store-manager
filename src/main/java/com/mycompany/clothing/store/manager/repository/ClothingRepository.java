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
import jakarta.persistence.PersistenceException;
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
    EntityManager em;

    public ClothingRepository(EntityManager em) {
        this.em = em;
    }

    public Integer getTotalQuantity() {
        List<Clothing> list = em.createQuery("SELECT s FROM Shirt s").getResultList();

        return list.size();
    }
    
    public void incrementClothing(Integer id, Integer quantity) {
        try {
        em.getTransaction().begin();
        em.createQuery("UPDATE Clothing c SET c.quantity = c.quantity + :quantity WHERE c.id = :id")
                .setParameter("quantity", quantity)
                .setParameter("id", id).executeUpdate();
        
        em.getTransaction().commit();
        em.clear();
        } catch(PersistenceException e) {
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

    public List consult(Clothing clothing, Boolean hasAtribute) throws Exception {
        try {
            return consultAux(clothing, hasAtribute);
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }

    public Integer existClothing(Clothing clothing) {
        String queryStr;
        TypedQuery<Shirt> query;
        Integer id = -1;

        if (clothing instanceof Shirt shirt) {
            queryStr = "SELECT s FROM Shirt s WHERE s.sleeve = :sleeve"
                    + " AND s.collar = :collar"
                    + " AND s.pocket = :pocket"
                    + " AND s.color = :color"
                    + " AND s.brand = :brand"
                    + " AND s.size = :size"
                    + " AND s.gender = :gender";
            
            if(!isBlank(shirt.getFabric())) {
                queryStr += " AND s.fabric LIKE :fabric";
            } else {
                queryStr += " AND s.fabric IS NULL";
            }
            if(!isBlank(shirt.getStyle())) {
                queryStr += " AND s.style LIKE :style";
            } else {
                queryStr += " AND s.style IS NULL";
            }
            if(!isBlank(shirt.getPattern())) {
                queryStr += " AND s.pattern LIKE :pattern ";
            } else {
                queryStr += " AND s.pattern  IS NULL";
            }
            if(!isBlank(shirt.getClosureType())) {
                queryStr += " AND s.closureType LIKE :closureType ";
            } else {
                queryStr += " AND s.closureType  IS NULL";
            }

            query = em.createQuery(queryStr, Shirt.class);

            query.setParameter("sleeve", shirt.getSleeve())
                    .setParameter("collar", shirt.getCollar())
                    .setParameter("pocket", shirt.getPocket())
                    .setParameter("color", shirt.getColor())
                    .setParameter("gender", shirt.getGender())
                    .setParameter("size", shirt.getSize())
                    .setParameter("brand", shirt.getBrand());
            
            if(!isBlank(shirt.getFabric())) {
                query.setParameter("fabric", shirt.getFabric());
            }
            if(!isBlank(shirt.getStyle())) {
                query.setParameter("style", shirt.getStyle());
            }
            if(!isBlank(shirt.getPattern())) {
                query.setParameter("pattern", shirt.getPattern());
            }
            if(!isBlank(shirt.getClosureType())) {
                query.setParameter("closureType", shirt.getClosureType());
            }
            
            if(!query.getResultList().isEmpty()) {
                System.out.println(query.getResultList().get(0));
                id = query.getResultList().get(0).getId();
            }
        }
        
        return id;
    }

    private List consultAux(Clothing clothing, Boolean hasAtributes) throws Exception {
        List list = null;

        if (clothing instanceof Shirt shirt) {
            String queryStr = "SELECT s FROM Shirt s WHERE";
            TypedQuery<Shirt> query;

            if (hasAtributes == true) {
                queryStr += " 1=1";

                if (shirt.getSleeve() != -1) {
                    queryStr += " AND s.sleeve <= :sleeve";
                }
                if (shirt.getCollar() != -1) {
                    queryStr += " AND s.collar <= :collar";
                }
                if (shirt.getPrice() != -1) {
                    queryStr += " AND s.price <= :price";
                }
                if (shirt.getQuantity() != -1) {
                    queryStr += " AND s.quantity >= :quantity";
                }
                if (shirt.getPocket() != -1) {
                    queryStr += " AND s.pocket = :pocket";
                }
                if (!isBlank(shirt.getColor())) {
                    queryStr += " AND s.color LIKE :color";
                }
                if (!isBlank(shirt.getFabric())) {
                    queryStr += " AND s.fabric LIKE :fabric";
                }
                if (!isBlank(shirt.getBrand())) {
                    queryStr += " AND s.brand LIKE :brand";
                }
                if (!isBlank(shirt.getStyle())) {
                    queryStr += " AND s.style LIKE :style";
                }
                if (!isBlank(shirt.getPattern())) {
                    queryStr += " AND s.pattern LIKE :pattern";
                }
                if (!isBlank(shirt.getClosureType())) {
                    queryStr += " AND s.closureType LIKE :closureType";
                }
                if (EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())) {
                    queryStr += " AND s.size = :size";
                }
                if (EnumSet.allOf(Gender.class).contains(shirt.getGender())) {
                    queryStr += " AND s.gender = :gender";
                }

                query = em.createQuery(queryStr, Shirt.class);

                if (shirt.getSleeve() != -1) {
                    query.setParameter("sleeve", shirt.getSleeve());
                }
                if (shirt.getCollar() != -1) {
                    query.setParameter("collar", shirt.getCollar());
                }
                if (shirt.getPocket() != -1) {
                    query.setParameter("pocket", shirt.getPocket());
                }
                if (!isBlank(shirt.getColor())) {
                    query.setParameter("color", "%" + shirt.getColor() + "%");
                }
                if (!isBlank(shirt.getFabric())) {
                    query.setParameter("fabric", shirt.getFabric());
                }
                if (!isBlank(shirt.getBrand())) {
                    query.setParameter("brand", "%" + shirt.getBrand() + "%");
                }
                if (shirt.getPrice() != -1) {
                    query.setParameter("price", shirt.getPrice());
                }
                if (shirt.getQuantity() != -1) {
                    query.setParameter("quantity", shirt.getQuantity());
                }
                if (!isBlank(shirt.getBrand())) {
                    query.setParameter("style", shirt.getStyle());
                }
                if (!isBlank(shirt.getPattern())) {
                    query.setParameter("pattern", shirt.getPattern());
                }
                if (!isBlank(shirt.getClosureType())) {
                    query.setParameter("closureType", shirt.getClosureType());
                }
                if (EnumSet.allOf(ShirtSize.class).contains(shirt.getSize())) {
                    query.setParameter("size", shirt.getSize());
                }
                if (EnumSet.allOf(Gender.class).contains(shirt.getGender())) {
                    query.setParameter("gender", shirt.getGender());
                }
            } else {
                System.out.println("DENTRO DO ELSE QUE DEFINE A QUERY");
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
        if (clothing instanceof Shirt shirt) {
            em.persist(shirt);
        }
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
