package pe.udh.gestionacademica.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pe.udh.gestionacademica.exception.DatosInvalidosException;
import pe.udh.gestionacademica.exception.RecursoNoEncontradoException;
import pe.udh.gestionacademica.model.Carrera;
import pe.udh.gestionacademica.model.Facultad;
import pe.udh.gestionacademica.util.Validaciones;

public class CatalogoService {

    private final Map<Integer, Facultad> facultades = new ConcurrentHashMap<>();
    private final Map<Integer, Carrera> carreras = new ConcurrentHashMap<>();

    public void registrarFacultad(Facultad facultad) {
        Validaciones.noNulo(facultad, "facultad");
        if (facultades.putIfAbsent(facultad.getIdFacultad(), facultad) != null) {
            throw new DatosInvalidosException("Ya existe una facultad con id "
                    + facultad.getIdFacultad());
        }
    }

    public void registrarCarrera(Carrera carrera) {
        Validaciones.noNulo(carrera, "carrera");
        buscarFacultad(carrera.getIdFacultad());
        if (carreras.putIfAbsent(carrera.getIdCarrera(), carrera) != null) {
            throw new DatosInvalidosException("Ya existe una carrera con id "
                    + carrera.getIdCarrera());
        }
    }

    public Facultad buscarFacultad(int idFacultad) {
        Facultad facultad = facultades.get(idFacultad);
        if (facultad == null) {
            throw new RecursoNoEncontradoException("No existe la facultad " + idFacultad);
        }
        return facultad;
    }

    public Carrera buscarCarrera(int idCarrera) {
        Carrera carrera = carreras.get(idCarrera);
        if (carrera == null) {
            throw new RecursoNoEncontradoException("No existe la carrera " + idCarrera);
        }
        return carrera;
    }

    public int obtenerIdFacultadDeCarrera(int idCarrera) {
        return buscarCarrera(idCarrera).getIdFacultad();
    }

    public List<Facultad> listarFacultades() {
        return facultades.values().stream()
                .sorted((a, b) -> Integer.compare(a.getIdFacultad(), b.getIdFacultad()))
                .toList();
    }

    public List<Carrera> listarCarreras() {
        return carreras.values().stream()
                .sorted((a, b) -> Integer.compare(a.getIdCarrera(), b.getIdCarrera()))
                .toList();
    }
}
