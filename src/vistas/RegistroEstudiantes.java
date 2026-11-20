
package vistas;

import clases.Estudiante;
import controladores.EstudianteControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegistroEstudiantes extends JFrame {

    private EstudianteControlador controlador;

    private JTextField txtCodigo, txtNombres, txtApellidos;
    private JTextField txtDni, txtCorreo, txtCarrera;
    private JComboBox<String> cbEstado;
    private DefaultTableModel modelo;

    public RegistroEstudiantes(EstudianteControlador controlador) {
        this.controlador = controlador;

        setTitle("Gestión de Estudiantes");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(4, 4, 8, 8));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del estudiante"));

        txtCodigo = new JTextField();
        txtNombres = new JTextField();
        txtApellidos = new JTextField();
        txtDni = new JTextField();
        txtCorreo = new JTextField();
        txtCarrera = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});

        formulario.add(new JLabel("Código:"));
        formulario.add(txtCodigo);
        formulario.add(new JLabel("Nombres:"));
        formulario.add(txtNombres);

        formulario.add(new JLabel("Apellidos:"));
        formulario.add(txtApellidos);
        formulario.add(new JLabel("DNI:"));
        formulario.add(txtDni);

        formulario.add(new JLabel("Correo:"));
        formulario.add(txtCorreo);
        formulario.add(new JLabel("ID Carrera:"));
        formulario.add(txtCarrera);

        formulario.add(new JLabel("Estado:"));
        formulario.add(cbEstado);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnDesactivar = new JButton("Desactivar");
        JButton btnListar = new JButton("Listar");
        JButton btnLimpiar = new JButton("Limpiar");

        formulario.add(btnRegistrar);
        formulario.add(btnBuscar);
        formulario.add(btnActualizar);
        formulario.add(btnDesactivar);

        String[] columnas = {
            "Código", "Nombres", "Apellidos", "DNI",
            "Correo", "ID Carrera", "Estado"
        };

        modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);

        JPanel botones = new JPanel();
        botones.add(btnListar);
        botones.add(btnLimpiar);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> registrar());
        btnBuscar.addActionListener(e -> buscar());
        btnActualizar.addActionListener(e -> actualizar());
        btnDesactivar.addActionListener(e -> desactivar());
        btnListar.addActionListener(e -> listar());
        btnLimpiar.addActionListener(e -> limpiar());
    }

    private void registrar() {
        try {
            boolean registrado = controlador.registrarEstudiante(
                txtCodigo.getText().trim(),
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                txtDni.getText().trim(),
                txtCorreo.getText().trim(),
                Integer.parseInt(txtCarrera.getText().trim()),
                cbEstado.getSelectedItem().toString()
            );

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente");
                listar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Datos inválidos o estudiante duplicado");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID de carrera debe ser numérico");
        }
    }

    private void buscar() {
        Estudiante estudiante = controlador.buscarEstudiante(txtCodigo.getText().trim());

        if (estudiante == null) {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
            return;
        }

        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtDni.setText(estudiante.getDni());
        txtCorreo.setText(estudiante.getCorreo());
        txtCarrera.setText(String.valueOf(estudiante.getIdCarrera()));
        cbEstado.setSelectedItem(estudiante.getEstado());
    }

    private void actualizar() {
        try {
            boolean actualizado = controlador.actualizarEstudiante(
                txtCodigo.getText().trim(),
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                txtDni.getText().trim(),
                txtCorreo.getText().trim(),
                Integer.parseInt(txtCarrera.getText().trim()),
                cbEstado.getSelectedItem().toString()
            );

            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente");
                listar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar. Verifica los datos");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID de carrera debe ser numérico");
        }
    }

    private void desactivar() {
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Deseas desactivar este estudiante?",
            "Confirmar desactivación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean resultado = controlador.desactivarEstudiante(
                txtCodigo.getText().trim()
            );

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Estudiante desactivado correctamente");
                listar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
            }
        }
    }

    private void listar() {
        modelo.setRowCount(0);

        for (Estudiante estudiante : controlador.listarEstudiantes()) {
            modelo.addRow(new Object[]{
                estudiante.getCodigo(),
                estudiante.getNombres(),
                estudiante.getApellidos(),
                estudiante.getDni(),
                estudiante.getCorreo(),
                estudiante.getIdCarrera(),
                estudiante.getEstado()
            });
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        txtCorreo.setText("");
        txtCarrera.setText("");
        cbEstado.setSelectedIndex(0);
    }
}

