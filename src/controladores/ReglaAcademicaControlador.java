import clases.ReglaAcademica;
import java.util.ArrayList;
import java.util.List;

public class ReglaAcademicaControlador {

    private List<ReglaAcademica> reglas = new ArrayList<>();

    public String registrarRegla(int id, int idFacultad, String tipo,
                                 double valor, String descripcion) {

        if (buscarRegla(id) != null) {
            return "La regla ya existe.";
        }

        ReglaAcademica regla = new ReglaAcademica(
            id, idFacultad, tipo, valor, descripcion
        );

        reglas.add(regla);
        return "Regla registrada correctamente.";
    }

    public List<ReglaAcademica> listarReglas() {
        return reglas;
    }

    public ReglaAcademica buscarRegla(int id) {
        for (ReglaAcademica regla : reglas) {
            if (regla.getIdRegla() == id) {
                return regla;
            }
        }
        return null;
    }

    public List<ReglaAcademica> buscarPorFacultad(int idFacultad) {
        List<ReglaAcademica> resultado = new ArrayList<>();

        for (ReglaAcademica regla : reglas) {
            if (regla.getIdFacultad() == idFacultad) {
                resultado.add(regla);
            }
        }

        return resultado;
    }

    public String modificarRegla(int id, double nuevoValor) {
        ReglaAcademica regla = buscarRegla(id);

        if (regla == null) {
            return "No se encontró la regla.";
        }

        regla.setValor(nuevoValor);
        return "Regla modificada correctamente.";
    }
}