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
Estructura principal del proyecto (clases y vistas, sin persistencia). En el Hito 1 solo se definió la estructura.

## Hito 2: avance funcional (Matrícula y Notas)
Los dos módulos funcionales básicos ya están implementados y se ejecutan desde una misma aplicación de consola:

### Módulo de Matrícula
- Registro de matrícula con varias secciones.
- Validación de período de matrícula abierto y matrícula duplicada.
- Validación de prerrequisitos por curso.
- Validación de cruce de horarios del estudiante.
- Control de cupos por sección con reserva atómica (probado con matrículas concurrentes).
- Listado/consulta de matrículas y anulación con liberación de cupos.
- Reglas académicas básicas por facultad (máximo de créditos, máximo de cursos y nota mínima).

### Módulo de Notas con auditoría
- Registro de notas por evaluación y peso.
- Cálculo del promedio ponderado.
- Modificación de una nota con motivo obligatorio.
- Historial auditable de cada cambio: valor anterior, valor nuevo, usuario, fecha y motivo.
- Las notas no se eliminan para conservar la trazabilidad; se corrigen mediante el historial.

### Cómo ejecutar
Requisitos: JDK 17 o superior y Maven 3.9 o superior.

```bash
# 1. Ejecutar las pruebas automatizadas
mvn test

# 2. Ejecutar la demo completa sin pedir datos por teclado
mvn exec:java -Dexec.args="--demo"

# 3. Abrir el menú interactivo
mvn exec:java
```

El menú permite elegir el módulo de Matrícula, el módulo de Notas o ejecutar ambos. La demo muestra:
- una matrícula exitosa,
- un rechazo por prerrequisito,
- un rechazo por cruce de horario,
- un rechazo por cupo agotado,
- el registro de notas, el promedio ponderado y el historial de auditoría.

Si `mvn` no está en el `PATH`, configura `JAVA_HOME` con el JDK y usa la ruta completa de Maven. El proyecto no usa base de datos todavía: los datos viven en memoria y se reinician en cada ejecución.

### Resultado esperado de la demo
```text
=== MODULO DE MATRICULA ===
1) Ana se matricula en Base de Datos y Redes:
Matricula #1 | Estudiante: 20210001 | Periodo: 1 | Creditos: 7 | Estado: REGISTRADA

2) Luis intenta POO sin aprobar Introduccion a la Programacion:
  Rechazado: No se cumple el prerrequisito: el curso 2 requiere aprobar el curso 1

3) Carla intenta dos secciones que se cruzan el lunes:
  Rechazado: La seccion 10 se cruza con la seccion 40 en el horario del estudiante

4) Seccion 10 con cupo para 2 estudiantes:
  Rechazado: La seccion 10 ya no tiene cupos disponibles

=== MODULO DE NOTAS CON AUDITORIA ===
Promedio ponderado: 16.8
Promedio ponderado: 17.6
Historial auditable del parcial: 0.0 -> 15.0 -> 17.0
```

### Evidencias y documentación
- Informe del Hito 2: [`docs/informes/Hito2_Informe_Grupo6.docx`](docs/informes/Hito2_Informe_Grupo6.docx)
- Commits de la rama `vanessa`: <https://github.com/Brigit08/sistema-gestion-academica/commits/vanessa>
- Pruebas: `src/test/java/pe/udh/gestionacademica/service/MatriculaServiceTest.java` y `NotaServiceTest.java`.

### Funcionalidades pendientes
- Persistencia en base de datos.
- Interfaz web o de escritorio.
- Asistencia, constancias, reportes y actas.
- Login, roles y auditoría general del sistema.
- Pruebas de carga con miles de usuarios.

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
