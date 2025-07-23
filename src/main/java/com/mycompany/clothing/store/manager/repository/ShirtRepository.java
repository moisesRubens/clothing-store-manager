/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.domain.Clothing;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author moise
 */
public class ShirtRepository implements IClothingRepository {
    private EntityManager em;

    public ShirtRepository(EntityManager em) {
        this.em = em;
    }
    
    
    @Override
    public void saveClothing(Clothing clothing) {
        
    }

    @Override
    public Clothing getClothingById(Integer id) {
        return null;
    }
    
    @Override
    public Integer existsClothing(String query) {
        return -1;
    }

    @Override
    public void updateQuantityClothing(Integer id, Integer quantity) {
        try {
            em.getTransaction().begin();
            em.createQuery("UPDATE Clothing c SET c.quantity = c.quantity + :quantity WHERE c.id = :id ")
                    .setParameter("id", id)
                    .setParameter("quantity", quantity).executeUpdate();
            em.getTransaction().commit();
            em.clear();
        } catch(Exception e) {
            
        }
    }
    
}
