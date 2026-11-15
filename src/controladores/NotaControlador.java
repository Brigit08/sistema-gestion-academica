
import clases.Nota;
import java.util.ArrayList;
import java.util.List;

public class NotaControlador {

    private List<Nota> notas = new ArrayList<>();

    public String registrarNota(int idNota, int idDetalle, String tipoEvaluacion,
                               double valor, double peso) {
        Nota nota = new Nota(idNota, idDetalle, tipoEvaluacion, valor, peso);
        notas.add(nota);

        return "Nota registrada correctamente.";
    }

    public List<Nota> listarNotas() {
        return notas;
    }

    public String modificarNota(int idNota, double nuevoValor) {
        for (Nota nota : notas) {
            if (nota.getIdNota() == idNota) {
                nota.setValor(nuevoValor);
                return "Nota modificada correctamente.";
            }
        }
        return "No se encontró la nota.";
    }
}

