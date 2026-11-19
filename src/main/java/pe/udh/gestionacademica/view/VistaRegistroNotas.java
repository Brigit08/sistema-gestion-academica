package pe.udh.gestionacademica.view;

import java.util.List;
import pe.udh.gestionacademica.model.Nota;

public class VistaRegistroNotas {

    public void mostrar(List<Nota> notas, double promedio) {
        for (Nota nota : notas) {
            System.out.println("  Nota #" + nota.getIdNota()
                    + " | " + nota.getTipoEvaluacion()
                    + " | valor: " + nota.getValor()
                    + " | peso: " + nota.getPeso() + "%");
        }
        System.out.println("  Promedio ponderado: " + promedio);
    }
}
