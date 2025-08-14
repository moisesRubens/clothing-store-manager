package com.mycompany.clothing.store.manager.main;

import com.mycompany.clothing.store.manager.configuration.SpringConfig;
import com.mycompany.clothing.store.manager.interfaces.IClothingController;
import com.mycompany.clothing.store.manager.controller.PantController;
import com.mycompany.clothing.store.manager.controller.PantyController;
import com.mycompany.clothing.store.manager.controller.ShirtController;
import com.mycompany.clothing.store.manager.service.PantService;
import com.mycompany.clothing.store.manager.service.PantyService;
import com.mycompany.clothing.store.manager.view.MainWindow;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.mycompany.clothing.store.manager.service.ShirtService;

public class Main {
    public static void main(String[] args) {
        // Configura Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Inicia contexto Spring
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // Pega o service gerenciado pelo Spring
        ShirtService shirtService = context.getBean(ShirtService.class);
        ShirtController shirtController = context.getBean(ShirtController.class);
        
        PantService pantService = context.getBean(PantService.class);
        PantController pantController = context.getBean(PantController.class);
        
        PantyService pantyService = context.getBean(PantyService.class);
        PantyController pantyController = context.getBean(PantyController.class);
        
        Map controllers = new HashMap<String, IClothingController>();
        controllers.put("shirtController", shirtController);
        controllers.put("pantController", pantController);
        controllers.put("pantyController", pantyController);
        
        MainWindow window = new MainWindow(controllers);
        window.setVisible(true);

        // Fecha o contexto Spring ao fechar a janela
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                context.close();
            }
        });
    }
}