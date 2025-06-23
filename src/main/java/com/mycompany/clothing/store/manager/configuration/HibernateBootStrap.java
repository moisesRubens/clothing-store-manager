/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.configuration;

import jakarta.persistence.Persistence;

/**
 *
 * @author moise
 */
public class HibernateBootStrap {
    public static void main(String[] args) {
        var emf = Persistence.createEntityManagerFactory("meuPU");
        var em = emf.createEntityManager();

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
