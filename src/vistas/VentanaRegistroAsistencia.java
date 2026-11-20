package vistas;

import clases.Estudiante;
import controladores.AsistenciaControlador;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistroAsistencia extends JFrame {

    private AsistenciaControlador controlador = new AsistenciaControlador();

    private JTextField txtId = new JTextField();
    private JTextField txtCodigo = new JTextField();
    private JTextField txtNombres = new JTextField();
    private JTextField txtApellidos = new JTextField();
    private JTextField txtFecha = new JTextField();
    private JTextField txtEstado = new JTextField();

    private JTextArea area = new JTextArea();

    public VentanaRegistroAsistencia() {
        setTitle("Registro de Asistencia");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));

        panel.add(new JLabel("ID asistencia:"));
        panel.add(txtId);

        panel.add(new JLabel("Código estudiante:"));
        panel.add(txtCodigo);

        panel.add(new JLabel("Nombres:"));
        panel.add(txtNombres);

        panel.add(new JLabel("Apellidos:"));
        panel.add(txtApellidos);

        panel.add(new JLabel("Fecha:"));
        panel.add(txtFecha);

        panel.add(new JLabel("Estado:"));
        panel.add(txtEstado);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnListar = new JButton("Listar");
        JButton btnBuscar = new JButton("Buscar por ID");

        panel.add(btnRegistrar);
        panel.add(btnListar);

        add(panel, BorderLayout.NORTH);
        add(btnBuscar, BorderLayout.CENTER);
        add(new JScrollPane(area), BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> registrar());
        btnListar.addActionListener(e -> listar());
        btnBuscar.addActionListener(e -> buscar());
    }

    private void registrar() {
        try {
            int id = Integer.parseInt(txtId.getText());

            Estudiante estudiante = new Estudiante(
                txtCodigo.getText(),
                txtNombres.getText(),
                txtApellidos.getText(),
                "",
                "",
                0,
                "Activo"
            );

            RegistroAsistencia registro = new RegistroAsistencia(
                id,
                estudiante,
                txtFecha.getText(),
                txtEstado.getText()
            );

            if (controlador.registrarAsistencia(registro)) {
                JOptionPane.showMessageDialog(this, "Asistencia registrada.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "El ID ya está registrado.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido.");
        }
    }

    private void listar() {
        area.setText("");

        for (RegistroAsistencia r : controlador.listarAsistencias()) {
            area.append(
                "ID: " + r.getId()
                + " | Estudiante: " + r.getEstudiante().getNombres()
                + " " + r.getEstudiante().getApellidos()
                + " | Fecha: " + r.getFecha()
                + " | Estado: " + r.getEstado() + "\n"
            );
        }
    }

    private void buscar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            RegistroAsistencia r = controlador.buscarPorId(id);

            if (r != null) {
                area.setText(
                    "ID: " + r.getId()
                    + "\nEstudiante: " + r.getEstudiante().getNombres()
                    + " " + r.getEstudiante().getApellidos()
                    + "\nFecha: " + r.getFecha()
                    + "\nEstado: " + r.getEstado()
                );
            } else {
                area.setText("No se encontró la asistencia.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido.");
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtCodigo.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtFecha.setText("");
        txtEstado.setText("");
    }
}