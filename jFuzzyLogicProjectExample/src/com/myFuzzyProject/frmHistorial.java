package com.myFuzzyProject;

//Importo componentes graficos
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class frmHistorial extends JFrame {
    private JTable table;
    // ArrayList para almacenar los objetos tipo Datos que son las predicciones
    // hechas previamente
    static ArrayList<Datos> historial;

    public frmHistorial() {
        setTitle("Historial");
        setSize(600, 400);

        // Obtengo los datos del historial, solo clono el Array de la clase Prediccion
        historial = Prediccion.historial;

        // Creo una tabla para visualizar
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Calificacion");
        model.addColumn("Genero");
        model.addColumn("Año");
        model.addColumn("Resultado");
        model.addColumn("Grafica");

        // Agrego los datos del arrayList historial, con un for each los recorro y
        // obtengo cada atributo de cada objeto y lo inserto en la tabla
        for (Datos dato : historial) {
            Object[] rowData = { dato.getCalificacion(), dato.getGenero(), dato.getAno(), dato.getResultado(),
                    "Grafica" };
            model.addRow(rowData);
        }

        // Crear la tabla con el modelo
        table = new JTable(model);

        // Agrego un evento en los elementos de la ultima columna Grafica
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndex("Grafica");
                int row = table.getSelectedRow();

                // Verificar si se hizo clic en la columna "Grafica"
                if (table.columnAtPoint(e.getPoint()) == column && row != -1) {
                    // Obtengo los atributos del objeto, del indice selecionado
                    String calificacion = table.getValueAt(row, 0).toString();
                    String genero = table.getValueAt(row, 1).toString();
                    String ano = table.getValueAt(row, 2).toString();
                    String resultado = table.getValueAt(row, 3).toString();

                    // Aqui lo unico que hago es volver a llamar al metodo Grafica de la clase
                    // prediccion, para que me muestre la grafica de nuevo
                    Prediccion.grafica(calificacion, genero, ano, resultado);
                    // Aqui borro el ultimo elemento del arrayList historial, ya que se clona por
                    // que en el metodo grafica este se duplica y esta es la solucion mas rapida que
                    // encontre
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