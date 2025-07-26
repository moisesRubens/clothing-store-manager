/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaNaoExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moise
 */
public abstract class ClothingRepository {
    EntityManager em;
    private static final Logger logger = Logger.getLogger(ClothingRepository.class.getName());
    
    public ClothingRepository(EntityManager em) {
        this.em = em;
    }
    
    public abstract Integer existsClothing(Clothing clothing, String query) throws Exception;
    
    public abstract Clothing getClothingById(Integer id) throws Exception;
    
    public void saveClothing(Clothing clothing) {
        em.getTransaction().begin();
        try {
            em.persist(clothing);
            em.getTransaction().commit();
        } catch(Exception e) {
            handleException(e);
        }
        
    }
    
    public void removeClothingById(Integer id) throws Exception {
        em.getTransaction().begin();
        try {
            em.createQuery("DELETE FROM Clothing c WHERE c.id = :id")
                .setParameter("id", id).executeUpdate();
            em.getTransaction().commit();
        } catch(IllegalStateException e) {
            handleException(e);
            throw new RoupaNaoExistenteException("Erro ao remover roupa. Roupa inexistente");
        } catch(Exception e) {
            handleException(e);
        }
    }
    
    public abstract List<Clothing> getAllClothing() throws Exception;
    
    public void updateQuantityClothing(Integer id, Integer quantity) {
        try {
            em.getTransaction().begin();
            em.createQuery("UPDATE Clothing c SET c.quantity = c.quantity + :quantity WHERE c.id = :id ")
                    .setParameter("id", id)
                    .setParameter("quantity", quantity).executeUpdate();
            em.getTransaction().commit();
            em.clear();
        } catch(Exception e) {
            handleException(e);
        }
    }
    
    void handleException(Exception e) {
        logger.log(Level.SEVERE, "ERRO", e);
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    } 
    
    boolean isBlank(String str) {
        return (str == null || str.isEmpty() || str.isBlank());
    }
}
