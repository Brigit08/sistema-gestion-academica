package vistas;

import clases.Estudiante;
import controladores.EstudianteControlador;
import servicios.EstudianteService;
import javax.swing.*;
import java.awt.*;

public class Inicio extends JFrame {

    public Inicio() {
        setTitle("Sistema de Gestión Académica - UDH");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel(
            "SISTEMA DE GESTIÓN ACADÉMICA",
            SwingConstants.CENTER
        );
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton estudiantes = new JButton("Gestión de estudiantes");
        JButton cursos = new JButton("Catálogo de cursos");
        JButton secciones = new JButton("Gestión de secciones");
        JButton notas = new JButton("Registro de notas");
        JButton asistencia = new JButton("Registro de asistencia");

        estudiantes.addActionListener(e -> {
            EstudianteService servicio = new EstudianteService();
            EstudianteControlador controlador =
                new EstudianteControlador(servicio);
            new RegistroEstudiantes(controlador).setVisible(true);
        });

        cursos.addActionListener(e ->
            new CatalogoCursos().setVisible(true)
        );

        secciones.addActionListener(e ->
            new GestionSecciones().setVisible(true)
        );

        notas.addActionListener(e ->
            new RegistroNotas().setVisible(true)
        );

        asistencia.addActionListener(e ->
            new RegistroAsistencia().setVisible(true)
        );

        JPanel botones = new JPanel(new GridLayout(5, 1, 12, 12));
        botones.add(estudiantes);
        botones.add(cursos);
        botones.add(secciones);
        botones.add(notas);
        botones.add(asistencia);

        setLayout(new BorderLayout(20, 20));
        add(titulo, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
    }
}