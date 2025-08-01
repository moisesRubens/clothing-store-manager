import com.mycompany.clothing.store.manager.configuration.SpringConfig;
import com.mycompany.clothing.store.manager.controller.IClothingController;
import com.mycompany.clothing.store.manager.factory.PantComponentFactory;
import com.mycompany.clothing.store.manager.factory.ShirtComponentFactory;
import com.mycompany.clothing.store.manager.service.ShirtService;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        // Pega as factories para criar controllers
        ShirtComponentFactory shirtFactory = context.getBean(ShirtComponentFactory.class);

        // Cria controllers passando os services (injeção manual aqui via factory)
        IClothingController shirtController = shirtFactory.createController(shirtService);
