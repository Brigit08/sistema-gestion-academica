import vistas.Inicio;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio ventana = new Inicio();
            ventana.setVisible(true);
        });
    }
}