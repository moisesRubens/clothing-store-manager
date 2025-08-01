/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clothing.store.manager.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author moise
 */

@Configuration
@ComponentScan(basePackages = "com.mycompany.clothing.store.manager")
@EnableJpaRepositories(basePackages = "com.mycompany.clothing.store.manager.repository")
public class SpringConfig {
    
}
