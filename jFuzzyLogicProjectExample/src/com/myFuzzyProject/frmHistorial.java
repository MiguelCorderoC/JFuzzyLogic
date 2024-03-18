package com.myFuzzyProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class frmHistorial extends JFrame {
    private JTable table;
    static ArrayList<Datos> historial;

    public frmHistorial() {
        setTitle("Historial");
        setSize(600, 400);

        // Obtener los datos del historial desde la clase Prediccion
        historial = Prediccion.historial;

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Calificacion");
        model.addColumn("Genero");
        model.addColumn("Año");
        model.addColumn("Resultado");
        model.addColumn("Grafica");

        // Agregar los datos del historial al modelo de tabla
        for (Datos dato : historial) {
            Object[] rowData = { dato.getCalificacion(), dato.getGenero(), dato.getAno(), dato.getResultado(),
                    "Grafica" };
            model.addRow(rowData);
        }

        // Crear la tabla con el modelo
        table = new JTable(model);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndex("Grafica");
                int row = table.getSelectedRow();

                // Verificar si se hizo clic en la columna "Grafica"
                if (table.columnAtPoint(e.getPoint()) == column && row != -1) {
                    // Obtener los valores de la fila seleccionada
                    String calificacion = table.getValueAt(row, 0).toString();
                    String genero = table.getValueAt(row, 1).toString();
                    String ano = table.getValueAt(row, 2).toString();
                    String resultado = table.getValueAt(row, 3).toString();

                    // Llamar al método Prediccion.grafica() con los valores obtenidos
                    Prediccion.grafica(calificacion, genero, ano, resultado);
                    Prediccion.historial.remove(Prediccion.historial.size() - 1);
                }
            }
        });

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar el JScrollPane al JFrame
        add(scrollPane);

        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new frmHistorial();
    }
}