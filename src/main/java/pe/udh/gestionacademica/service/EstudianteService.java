package pe.udh.gestionacademica.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pe.udh.gestionacademica.exception.DatosInvalidosException;
import pe.udh.gestionacademica.exception.RecursoNoEncontradoException;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.util.Validaciones;

public class EstudianteService {

    private final Map<String, Estudiante> estudiantes = new ConcurrentHashMap<>();

    public void registrar(Estudiante estudiante) {
        Validaciones.noNulo(estudiante, "estudiante");
        Validaciones.requerido(estudiante.getCodigo(), "codigo");
        Validaciones.requerido(estudiante.getNombres(), "nombres");
        Validaciones.requerido(estudiante.getApellidos(), "apellidos");
        Validaciones.requerido(estudiante.getDni(), "dni");
        Validaciones.requerido(estudiante.getCorreo(), "correo");
        if (estudiantes.putIfAbsent(estudiante.getCodigo(), estudiante) != null) {
            throw new DatosInvalidosException("Ya existe un estudiante con codigo "
                    + estudiante.getCodigo());
        }
    }

    public Estudiante buscar(String codigo) {
        String codigoValidado = Validaciones.requerido(codigo, "codigo");
        Estudiante estudiante = estudiantes.get(codigoValidado);
        if (estudiante == null) {
            throw new RecursoNoEncontradoException("No existe el estudiante " + codigoValidado);
        }
        return estudiante;
    }

    public boolean existe(String codigo) {
        return codigo != null && estudiantes.containsKey(codigo.trim());
    }

    public List<Estudiante> listar() {
        return estudiantes.values().stream()
                .sorted((a, b) -> a.getCodigo().compareTo(b.getCodigo()))
                .toList();
    }

    public void aprobarCurso(String codigo, int idCurso) {
        Validaciones.enteroPositivo(idCurso, "idCurso");
        buscar(codigo).aprobarCurso(idCurso);
    }

    public boolean haAprobado(String codigo, int idCurso) {
        return buscar(codigo).haAprobado(idCurso);
    }
}
