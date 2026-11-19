package pe.udh.gestionacademica.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Seccion {

    private final int idSeccion;
    private final int idCurso;
    private final int idPeriodo;
    private final int idDocente;
    private final String codigoSeccion;
    private final int cupoMaximo;
    private final AtomicInteger cupoOcupado;
    private final List<HorarioSeccion> horarios;

    public Seccion(int idSeccion, int idCurso, int idPeriodo, int idDocente,
                   String codigoSeccion, int cupoMaximo) {
        this.idSeccion = idSeccion;
        this.idCurso = idCurso;
        this.idPeriodo = idPeriodo;
        this.idDocente = idDocente;
        this.codigoSeccion = Objects.requireNonNull(codigoSeccion, "El codigo de seccion es obligatorio");
        if (cupoMaximo <= 0) {
            throw new IllegalArgumentException("El cupo maximo debe ser mayor que cero");
        }
        this.cupoMaximo = cupoMaximo;
        this.cupoOcupado = new AtomicInteger();
        this.horarios = new ArrayList<>();
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public int getCupoOcupado() {
        return cupoOcupado.get();
    }

    public int getCupoDisponible() {
        return cupoMaximo - cupoOcupado.get();
    }

    public boolean hayCupo() {
        return cupoOcupado.get() < cupoMaximo;
    }

    public boolean reservarCupo() {
        while (true) {
            int ocupado = cupoOcupado.get();
            if (ocupado >= cupoMaximo) {
                return false;
            }
            if (cupoOcupado.compareAndSet(ocupado, ocupado + 1)) {
                return true;
            }
        }
    }

    public void liberarCupo() {
        cupoOcupado.updateAndGet(actual -> actual > 0 ? actual - 1 : 0);
    }

    public void agregarHorario(HorarioSeccion horario) {
        horarios.add(Objects.requireNonNull(horario, "El horario es obligatorio"));
    }

    public List<HorarioSeccion> getHorarios() {
        return Collections.unmodifiableList(horarios);
    }
}
