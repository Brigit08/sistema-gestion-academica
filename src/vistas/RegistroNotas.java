package vistas;

import clases.Nota;
import controladores.NotaControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegistroNotas extends JFrame {

    private NotaControlador controlador = new NotaControlador();
    private JTextField id, detalle, tipo, valor, peso, nuevoValor;
    private DefaultTableModel modelo;

    public RegistroNotas() {
        setTitle("Registro de Notas");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(3, 4, 8, 8));

        id = new JTextField();
        detalle = new JTextField();
        tipo = new JTextField();
        valor = new JTextField();
        peso = new JTextField();
        nuevoValor = new JTextField();

        formulario.add(new JLabel("ID Nota:"));
        formulario.add(id);
        formulario.add(new JLabel("ID Detalle:"));
        formulario.add(detalle);
        formulario.add(new JLabel("Tipo evaluación:"));
        formulario.add(tipo);
        formulario.add(new JLabel("Valor:"));
        formulario.add(valor);
        formulario.add(new JLabel("Peso:"));
        formulario.add(peso);
        formulario.add(new JLabel("Nuevo valor:"));
        formulario.add(nuevoValor);

        JButton registrar = new JButton("Registrar");
        JButton listar = new JButton("Listar");
        JButton modificar = new JButton("Modificar");

        JPanel botones = new JPanel();
        botones.add(registrar);
        botones.add(listar);
        botones.add(modificar);

        modelo = new DefaultTableModel(
            new String[]{"ID Nota", "ID Detalle", "Valor"}, 0
        );

        JTable tabla = new JTable(modelo);

        registrar.addActionListener(e -> registrar());
        listar.addActionListener(e -> listar());
        modificar.addActionListener(e -> modificar());

        setLayout(new BorderLayout(10, 10));
        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    private void registrar() {
        try {
            controlador.registrarNota(
                Integer.parseInt(id.getText()),
                Integer.parseInt(detalle.getText()),
                tipo.getText(),
                Double.parseDouble(valor.getText()),
                Double.parseDouble(peso.getText())
            );

            JOptionPane.showMessageDialog(this, "Nota registrada");
            listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Verifica los datos ingresados");
        }
    }

    private void listar() {
        modelo.setRowCount(0);

        for (Nota n : controlador.listarNotas()) {
            modelo.addRow(new Object[]{
                n.getIdNota(),
                n.getIdDetalle(),
                n.getValor()
            });
        }
    }

    private void modificar() {
        try {
            controlador.modificarNota(
                Integer.parseInt(id.getText()),
                Double.parseDouble(nuevoValor.getText())
            );

            JOptionPane.showMessageDialog(this, "Nota modificada");
            listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo modificar");
        }
    }
}