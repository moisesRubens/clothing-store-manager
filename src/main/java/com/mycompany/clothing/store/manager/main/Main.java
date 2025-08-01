package com.mycompany.clothing.store.manager.main;

import com.mycompany.clothing.store.manager.configuration.SpringConfig;
import com.mycompany.clothing.store.manager.controller.IClothingController;
import com.mycompany.clothing.store.manager.controller.ShirtController;
import com.mycompany.clothing.store.manager.view.MainWindow;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ShirtService;

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
        Map controllers = new HashMap<String, IClothingController>();
        
        MainWindow window = new MainWindow(controllers);
        controllers.put("shirtController", shirtController);
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