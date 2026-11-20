import clases.Constancia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConstanciaControlador {

    private List<Constancia> constancias = new ArrayList<>();

    public String registrarConstancia(String codigoEstudiante, String tipo) {
        int id = constancias.size() + 1;
        String codigoVerificacion = "CONST-" + id;
        Constancia constancia = new Constancia(id, codigoEstudiante, tipo,
                                                new Date(), codigoVerificacion);
        constancias.add(constancia);
        return "Constancia registrada: " + codigoVerificacion;
    }

    public List<Constancia> listarConstancias() {
        return constancias;
    }

    public Constancia buscarConstancia(int id) {
        for (Constancia constancia : constancias) {
            if (constancia.getIdConstancia() == id) {
                return constancia;
            }
        }
        return null;
    }
}
