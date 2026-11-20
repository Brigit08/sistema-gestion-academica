package vistas;

import clases.Estudiante;
import controladores.EstudianteControlador;
import servicios.EstudianteService;
import javax.swing.*;
import java.awt.*;

public class Inicio extends JFrame {

    public Inicio() {
        setTitle("Sistema de Gestión Académica - UDH");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel(
            "SISTEMA DE GESTIÓN ACADÉMICA",
            SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton btnEstudiantes = new JButton("Gestión de estudiantes");

        btnEstudiantes.addActionListener(e -> {
            EstudianteService servicio = new EstudianteService();
            EstudianteControlador controlador =
                new EstudianteControlador(servicio);

            RegistroEstudiantes ventana =
                new RegistroEstudiantes(controlador);

            ventana.setVisible(true);
        });

        setLayout(new BorderLayout(20, 20));
        add(titulo, BorderLayout.NORTH);
        add(btnEstudiantes, BorderLayout.CENTER);
    }
}