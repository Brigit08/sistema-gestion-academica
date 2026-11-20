
package vistas;

import clases.Seccion;
import controladores.SeccionControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionSecciones extends JFrame {

    private SeccionControlador controlador = new SeccionControlador();
    private JTextField id, curso, periodo, docente, codigo, cupo;
    private DefaultTableModel modelo;

    public GestionSecciones() {
        setTitle("Gestión de Secciones");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(4, 4, 5, 5));

        id = new JTextField();
        curso = new JTextField();
        periodo = new JTextField();
        docente = new JTextField();
        codigo = new JTextField();
        cupo = new JTextField();

        formulario.add(new JLabel("ID Sección:"));
        formulario.add(id);
        formulario.add(new JLabel("ID Curso:"));
        formulario.add(curso);
        formulario.add(new JLabel("ID Periodo:"));
        formulario.add(periodo);
        formulario.add(new JLabel("ID Docente:"));
        formulario.add(docente);
        formulario.add(new JLabel("Código:"));
        formulario.add(codigo);
        formulario.add(new JLabel("Cupo máximo:"));
        formulario.add(cupo);

        JButton registrar = new JButton("Registrar");
        JButton listar = new JButton("Listar");
        JButton consultar = new JButton("Consultar cupos");

        formulario.add(registrar);
        formulario.add(listar);
        formulario.add(consultar);

        modelo = new DefaultTableModel(
            new String[]{"ID", "Curso", "Periodo", "Cupo máximo", "Cupo ocupado"}, 0
        );

        JTable tabla = new JTable(modelo);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        registrar.addActionListener(e -> registrar());
        listar.addActionListener(e -> listar());
        consultar.addActionListener(e -> consultarCupos());
    }

    private void registrar() {
        try {
            String mensaje = controlador.registrarSeccion(
                Integer.parseInt(id.getText()),
                Integer.parseInt(curso.getText()),
                Integer.parseInt(periodo.getText()),
                Integer.parseInt(docente.getText()),
                codigo.getText(),
                Integer.parseInt(cupo.getText())
            );

            JOptionPane.showMessageDialog(this, mensaje);
            listar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifica los datos numéricos");
        }
    }

    private void listar() {
        modelo.setRowCount(0);

        for (Seccion s : controlador.listarSecciones()) {
            modelo.addRow(new Object[]{
                s.getIdSeccion(),
                s.getIdCurso(),
                s.getIdPeriodo(),
                s.getCupoMaximo(),
                s.getCupoOcupado()
            });
        }
    }

    private void consultarCupos() {
        try {
            int disponibles = controlador.consultarCupos(
                Integer.parseInt(id.getText())
            );

            if (disponibles < 0) {
                JOptionPane.showMessageDialog(this, "Sección no encontrada");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Cupos disponibles: " + disponibles);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID válido");
        }
    }
}
