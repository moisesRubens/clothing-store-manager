package main;

import com.mycompany.clothing.store.manager.controller.IClothingController;
import com.mycompany.clothing.store.manager.controller.ShirtController;
import com.mycompany.clothing.store.manager.factory.ShirtFactory;
import com.mycompany.clothing.store.manager.repository.ClothingRepository;
import com.mycompany.clothing.store.manager.repository.ShirtRepository;
import com.mycompany.clothing.store.manager.service.IClothingService;
import com.mycompany.clothing.store.manager.service.ShirtService;
import com.mycompany.clothing.store.manager.service.mapper.ShirtMapper;
import com.mycompany.clothing.store.manager.view.MainWindow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private EntityManagerFactory emf;
    private EntityManager em;

    public void initialize() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        emf = Persistence.createEntityManagerFactory("meuPU");
        em = emf.createEntityManager();
        ClothingRepository shirtRepository = new ShirtRepository(em);
        IClothingService shirtService = new ShirtService(shirtRepository, new ShirtFactory(), new ShirtMapper());
        IClothingController shirtController = new ShirtController(shirtService);

        MainWindow window = new MainWindow(shirtController);
        window.pack(); 
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (em.isOpen()) {
                    em.close();
                }
                if (emf.isOpen()) {
                    emf.close();
                }
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new Main().initialize();
    }
}
