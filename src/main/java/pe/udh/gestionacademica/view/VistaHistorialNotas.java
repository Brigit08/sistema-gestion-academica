package pe.udh.gestionacademica.view;

import java.util.List;
import pe.udh.gestionacademica.model.HistorialNota;
import pe.udh.gestionacademica.util.FechaUtil;

public class VistaHistorialNotas {

    public void mostrar(List<HistorialNota> historial) {
        for (HistorialNota registro : historial) {
            System.out.println("  #" + registro.getIdHistorial()
                    + " | Nota " + registro.getIdNota()
                    + " | " + registro.getValorAnterior() + " -> " + registro.getValorNuevo()
                    + " | Usuario: " + registro.getIdUsuario()
                    + " | " + FechaUtil.formatear(registro.getFechaCambio())
                    + " | " + registro.getMotivo());
        }
    }
}
