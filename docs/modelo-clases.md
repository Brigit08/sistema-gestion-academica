# Modelo de clases (Hito 1)

Diagramas: `diagramas/diagrama-clases.drawio` y `diagramas/diagrama-casos-uso.drawio` (también en PNG).

## Clases y atributos
- **ReglaAcademica**: idRegla, idFacultad, tipo, valor, descripcion
- **Facultad**: idFacultad, nombre
- **Carrera**: idCarrera, nombre, idFacultad
- **Estudiante**: codigo, nombres, apellidos, dni, correo, idCarrera, estado
- **Constancia**: idConstancia, codigoEstudiante, tipo, fechaEmision, codigoVerificacion
- **Prerrequisito**: idCurso, idCursoRequisito
- **Curso**: idCurso, codigo, nombre, creditos, ciclo, idCarrera
- **PeriodoAcademico**: idPeriodo, nombre, fechaInicio, fechaFin, inicioMatricula, finMatricula
- **Matricula**: idMatricula, codigoEstudiante, idPeriodo, fecha, estado, totalCreditos
- **Usuario**: idUsuario, username, passwordHash, rol, activo
- **Docente**: idDocente, nombres, apellidos, correo, especialidad
- **Seccion**: idSeccion, idCurso, idPeriodo, idDocente, codigoSeccion, cupoMaximo, cupoOcupado
- **DetalleMatricula**: idDetalle, idMatricula, idSeccion, estado
- **HistorialNota**: idHistorial, idNota, valorAnterior, valorNuevo, idUsuario, fechaCambio, motivo
- **Horario**: idHorario, idSeccion, idAula, diaSemana, horaInicio, horaFin
- **Aula**: idAula, nombre, capacidad
- **Asistencia**: idAsistencia, idDetalle, fecha, estado
- **Nota**: idNota, idDetalle, tipoEvaluacion, valor, peso

## Relaciones
- Facultad (1) — Carrera (*): tiene
- Facultad (1) — ReglaAcademica (*): define
- Carrera (1) — Estudiante (*): tiene
- Carrera (1) — Curso (*): incluye
- Curso (1) — Prerrequisito (*): requiere
- Curso (1) — Seccion (*): se dicta en
- PeriodoAcademico (1) — Seccion (*): ofrece
- Docente (1) — Seccion (*): dicta
- Seccion (1) — Horario (*): tiene
- Aula (1) — Horario (*): se usa en
- Estudiante (1) — Matricula (*): realiza
- PeriodoAcademico (1) — Matricula (*): abre
- Matricula (1) — DetalleMatricula (*): contiene
- Seccion (1) — DetalleMatricula (*): recibe
- DetalleMatricula (1) — Nota (*): genera
- DetalleMatricula (1) — Asistencia (*): registra
- Nota (1) — HistorialNota (*): audita
- Usuario (1) — HistorialNota (*): realiza
- Estudiante (1) — Constancia (*): solicita

Sin persistencia por ahora: solo clases y vistas.
