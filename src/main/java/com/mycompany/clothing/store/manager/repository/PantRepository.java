/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.repository;

import com.mycompany.clothing.store.manager.configuration.exception.RoupaNaoExistenteException;
import com.mycompany.clothing.store.manager.domain.Clothing;
import com.mycompany.clothing.store.manager.domain.Pant;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moise
 */
public class PantRepository extends ClothingRepository {

    public PantRepository(EntityManager em) {
        super(em);
    }
    
    @Override
    public Integer getClothingId(Clothing clothing, String query) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Clothing getClothingById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Clothing> getAllClothing() throws Exception {
        try {
            return new ArrayList<> (
                    em.createQuery("SELECT p FROM Pant p", Pant.class).getResultList());
        } catch(Exception e) {
            handleException(e);
            throw e;
        }
    }

    @Override
    public List<Clothing> getClothings(Clothing clothing) throws Exception {
        try {
            String query = clothing.createQuery();
            List<Clothing> clothings = em.createNamedQuery(query).getResultList();

            if (clothings.isEmpty()) {
                throw new RoupaNaoExistenteException("Não há calças com estas caracteristicas");
            }
            return clothings;
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
    }
    
}
