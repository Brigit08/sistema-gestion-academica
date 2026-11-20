package controladores;

import vistas.RegistroAsistencia;

import java.util.ArrayList;
import java.util.List;

public class AsistenciaControlador {

    private final List<RegistroAsistencia> registros;

    public AsistenciaControlador() {
        this.registros = new ArrayList<>();
    }

    public boolean registrarAsistencia(RegistroAsistencia registro) {
        if (registro == null) {
            return false;
        }
        if (buscarPorId(registro.getId()) != null) {
            return false;
        }
        return registros.add(registro);
    }

    public List<RegistroAsistencia> listarAsistencias() {
        return new ArrayList<>(registros);
    }

    public RegistroAsistencia buscarPorId(int id) {
        for (RegistroAsistencia r : registros) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public List<RegistroAsistencia> buscarPorFecha(String fecha) {
        List<RegistroAsistencia> encontrados = new ArrayList<>();
        if (fecha == null) {
            return encontrados;
        }
        for (RegistroAsistencia r : registros) {
            if (fecha.equalsIgnoreCase(r.getFecha())) {
                encontrados.add(r);
            }
        }
        return encontrados;
    }

    public boolean actualizarEstado(int id, String nuevoEstado) {
        RegistroAsistencia registro = buscarPorId(id);
        if (registro == null || nuevoEstado == null || nuevoEstado.isBlank()) {
            return false;
        }
        registro.setEstado(nuevoEstado);
        return true;
    }

    public boolean eliminarAsistencia(int id) {
        RegistroAsistencia registro = buscarPorId(id);
        if (registro == null) {
            return false;
        }
        return registros.remove(registro);
    }
}