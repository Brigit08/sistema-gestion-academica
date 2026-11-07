# Sistema de Gestión Académica Universitaria

Proyecto del curso de Ingeniería de Software (docente: Omar Sulca), Grupo 6 - Universidad de Huánuco (UDH), P.A.P. Ingeniería de Sistemas e Informática, 2026-II.

## Descripción
Sistema de gestión académica universitaria que permite:
- Matrícula de estudiantes, con validación de prerrequisitos y cruces de horario.
- Control de cupos por sección (sin sobrepasar el cupo, incluso con miles de matrículas simultáneas).
- Registro de notas y asistencia.
- Emisión de constancias y reportes.
- Reglas académicas configurables por facultad.
- Historial auditable de todo cambio en las notas.

## Hito 1
Estructura principal del proyecto (clases y vistas, sin persistencia). Los archivos están vacíos a propósito: solo se definió la estructura.

## Estructura (patrón MVC)
- `model/`: clases del dominio.
- `view/`: vistas de la interfaz.
- `controller/`: controladores que conectan vistas y modelo.
- `service/`: reglas de negocio (prerrequisitos, horarios, cupos, auditoría).
- `util/` y `exception/`: apoyo y excepciones propias.
- `docs/`: documentación y diagramas.

## Ramas
- `main`: rama principal.
- Una rama secundaria por integrante, con su nombre (`brigit`, `vanessa`, `angelo`, `josep`, `benji`).

## Integrantes
| Integrante | Rama |
|---|---|
| Brigit Gallardo Torres (representante) | `brigit` |
| Vanessa Fernández Ponce | `vanessa` |
| Angelo Quispe Morales | `angelo` |
| Josep Lopez Castillo | `josep` |
| Benji Del Águila Villanueva | `benji` |
