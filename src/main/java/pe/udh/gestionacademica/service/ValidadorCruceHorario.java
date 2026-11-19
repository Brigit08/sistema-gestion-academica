package pe.udh.gestionacademica.service;

import java.util.Collection;
import java.util.List;
import pe.udh.gestionacademica.exception.CruceHorarioException;
import pe.udh.gestionacademica.model.HorarioSeccion;
import pe.udh.gestionacademica.model.Seccion;
import pe.udh.gestionacademica.util.Validaciones;

public class ValidadorCruceHorario {

    public void validar(List<Seccion> nuevas, Collection<Seccion> existentes) {
        Validaciones.noNulo(nuevas, "secciones");
        for (int i = 0; i < nuevas.size(); i++) {
            for (int j = i + 1; j < nuevas.size(); j++) {
                validarPar(nuevas.get(i), nuevas.get(j));
            }
        }
        if (existentes == null) {
            return;
        }
        for (Seccion nueva : nuevas) {
            for (Seccion existente : existentes) {
                validarPar(nueva, existente);
            }
        }
    }

    public boolean hayCruce(Seccion primera, Seccion segunda) {
        for (HorarioSeccion horarioPrimera : primera.getHorarios()) {
            for (HorarioSeccion horarioSegunda : segunda.getHorarios()) {
                if (horarioPrimera.seSolapaCon(horarioSegunda)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void validarPar(Seccion primera, Seccion segunda) {
        if (primera.getIdSeccion() == segunda.getIdSeccion()) {
            return;
        }
        if (hayCruce(primera, segunda)) {
            throw new CruceHorarioException(primera.getIdSeccion(), segunda.getIdSeccion());
        }
    }
}
