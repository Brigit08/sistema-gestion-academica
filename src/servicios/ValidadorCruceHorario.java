import clases.Horario;

import java.util.List;
import java.sql.Time;

public class ValidadorCruceHorario {

    public boolean validar(int idSeccionNueva,
                           List<Integer> seccionesInscritas,
                           List<Horario> horarios) {

        for (Horario horarioNuevo : horarios) {

            if (horarioNuevo.getIdSeccion() == idSeccionNueva) {

                for (int idSeccion : seccionesInscritas) {

                    for (Horario horarioExistente : horarios) {

                        if (horarioExistente.getIdSeccion() == idSeccion
                            && horarioNuevo.getDiaSemana().equalsIgnoreCase(
                               horarioExistente.getDiaSemana())) {

                            Time inicioNuevo = horarioNuevo.getHoraInicio();
                            Time finNuevo = horarioNuevo.getHoraFim();

                            Time inicioExistente =
                                horarioExistente.getHoraInicio();

                            Time finExistente =
                                horarioExistente.getHoraFim();

                            boolean existeCruce =
                                inicioNuevo.before(finExistente)
                                && finNuevo.after(inicioExistente);

                            if (existeCruce) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
