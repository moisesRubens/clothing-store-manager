/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaJaExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Shirt;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
        } catch(EntityExistsException e) {
            handleException(e);
            throw new RoupaJaExistenteException("ROUPA JA CADASTRADA NO BANCO DE DADOS");
        } catch(Exception e) {
            handleException(e);
            throw new RuntimeException("ERRO AO CADASTRAR", e);
        } finally {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }
    
    private void registerInDatabaseAux(EntityManager em, Clothing clothing) throws Exception {
        if(clothing instanceof Shirt shirt) {
            em.persist(shirt);
        }
    }
    
    private void handleException(Exception e) {
        logger.log(Level.WARNING, "ERRO", e);
    }
    
    private void shutDown() {
        emf.close();
    }
}
