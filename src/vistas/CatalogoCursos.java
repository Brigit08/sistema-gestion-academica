
package vistas;

import clases.Curso;
import controladores.CursoControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CatalogoCursos extends JFrame {

    private CursoControlador controlador = new CursoControlador();
    private JTextField id, codigo, nombre, creditos, ciclo, carrera;
    private DefaultTableModel modelo;

    public CatalogoCursos() {
        setTitle("Catálogo de Cursos");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(3, 4, 5, 5));

        id = new JTextField();
        codigo = new JTextField();
        nombre = new JTextField();
        creditos = new JTextField();
        ciclo = new JTextField();
        carrera = new JTextField();

        formulario.add(new JLabel("ID:"));
        formulario.add(id);
        formulario.add(new JLabel("Código:"));
        formulario.add(codigo);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(nombre);
        formulario.add(new JLabel("Créditos:"));
        formulario.add(creditos);
        formulario.add(new JLabel("Ciclo:"));
        formulario.add(ciclo);
        formulario.add(new JLabel("ID Carrera:"));
        formulario.add(carrera);

        JButton registrar = new JButton("Registrar");
        JButton listar = new JButton("Listar");
        JButton buscar = new JButton("Buscar");

        formulario.add(registrar);
        formulario.add(listar);
        formulario.add(buscar);

        modelo = new DefaultTableModel(
            new String[]{"ID", "Código", "Nombre", "Créditos", "Ciclo", "Carrera"}, 0
        );

        JTable tabla = new JTable(modelo);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        registrar.addActionListener(e -> registrar());
        listar.addActionListener(e -> listar());
        buscar.addActionListener(e -> buscar());
    }

    private void registrar() {
        try {
            String mensaje = controlador.registrarCurso(
                Integer.parseInt(id.getText()),
                codigo.getText(),
                nombre.getText(),
                Integer.parseInt(creditos.getText()),
                Integer.parseInt(ciclo.getText()),
                Integer.parseInt(carrera.getText())
            );

            JOptionPane.showMessageDialog(this, mensaje);
            listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Verifica los datos ingresados");
        }
    }

    private void listar() {
        modelo.setRowCount(0);

        for (Curso c : controlador.listarCursos()) {
            modelo.addRow(new Object[]{
                c.getIdCurso(), c.getCodigo(), c.getNombre(),
                c.getCreditos(), c.getCiclo(), c.getIdCarrera()
            });
        }
    }

    private void buscar() {
        try {
            Curso c = controlador.buscarCurso(Integer.parseInt(id.getText()));

            if (c == null) {
                JOptionPane.showMessageDialog(this, "Curso no encontrado");
                return;
            }

            JOptionPane.showMessageDialog(this,
                "Curso: " + c.getNombre() + "\nCódigo: " + c.getCodigo());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID válido");
        }
    }
}
