package vistas;

import controladores.AsistenciaControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegistroAsistencia extends JFrame {

    private AsistenciaControlador controlador = new AsistenciaControlador();
    private JTextField estudiante, seccion, fecha, estado, buscar;
    private DefaultTableModel modelo;

    public RegistroAsistencia() {
        setTitle("Registro de Asistencia");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(2, 4, 8, 8));

        estudiante = new JTextField();
        seccion = new JTextField();
        fecha = new JTextField();
        estado = new JTextField();
        buscar = new JTextField();

        formulario.add(new JLabel("Código estudiante:"));
        formulario.add(estudiante);
        formulario.add(new JLabel("ID sección:"));
        formulario.add(seccion);
        formulario.add(new JLabel("Fecha:"));
        formulario.add(fecha);
        formulario.add(new JLabel("Estado:"));
        formulario.add(estado);

        JButton registrar = new JButton("Registrar");
        JButton listar = new JButton("Listar");
        JButton consultar = new JButton("Buscar estudiante");

        JPanel botones = new JPanel();
        botones.add(registrar);
        botones.add(listar);
        botones.add(new JLabel("Código:"));
        botones.add(buscar);
        botones.add(consultar);

        modelo = new DefaultTableModel(
            new String[]{"Código estudiante", "ID sección", "Fecha", "Estado"}, 0
        );

        JTable tabla = new JTable(modelo);

        registrar.addActionListener(e -> registrar());
        listar.addActionListener(e -> listar());
        consultar.addActionListener(e -> buscar());

        setLayout(new BorderLayout(10, 10));
        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    private void registrar() {
        controlador.registrarAsistencia(
            estudiante.getText(),
            Integer.parseInt(seccion.getText()),
            fecha.getText(),
            estado.getText()
        );

        JOptionPane.showMessageDialog(this, "Asistencia registrada");
        listar();
    }

    private void listar() {
        modelo.setRowCount(0);

        for (String[] a : controlador.listarAsistencias()) {
            modelo.addRow(a);
        }
    }

    private void buscar() {
        modelo.setRowCount(0);

        for (String[] a : controlador.buscarPorEstudiante(buscar.getText())) {
            modelo.addRow(a);
        }
    }
}