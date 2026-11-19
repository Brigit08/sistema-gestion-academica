package pe.udh.gestionacademica.view;

import pe.udh.gestionacademica.model.DetalleMatricula;
import pe.udh.gestionacademica.model.Matricula;
import pe.udh.gestionacademica.util.FechaUtil;

public class VistaMatricula {

    public void mostrar(Matricula matricula) {
        System.out.println("Matricula #" + matricula.getIdMatricula()
                + " | Estudiante: " + matricula.getCodigoEstudiante()
                + " | Periodo: " + matricula.getIdPeriodo()
                + " | Fecha: " + FechaUtil.formatear(matricula.getFecha())
                + " | Creditos: " + matricula.getTotalCreditos()
                + " | Estado: " + matricula.getEstado());
        for (DetalleMatricula detalle : matricula.getDetalles()) {
            System.out.println("  Seccion " + detalle.getIdSeccion()
                    + " | " + detalle.getEstado());
        }
    }
}
