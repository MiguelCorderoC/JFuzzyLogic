package com.myFuzzyProject;

import java.util.ArrayList;

import javax.swing.JOptionPane;
//Importo las librerias que necesitare, esto gracias al jar que descargamos 
//llamado jfuzzyLogic.jar y colocamos en la carpeta lib
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Prediccion {

    // ArrayList para almacenar objetos que tendran los datos de las predicciones
    // hechas anteriormente
    static ArrayList<Datos> historial = new ArrayList<>();

    // Metodo para poder dar el resultado, recibe como parametro los campos de texto
    // que son los datos de entrada
    public static String recomendar(String txtCalificacion, String txtGenero, String txtAno) {
        double calificacion = 0;
        double genero = 0;
        double ano;

        // Convierto los textos a datos tipo Double
        try {
            calificacion = Double.parseDouble(txtCalificacion);
            genero = Double.parseDouble(txtGenero);
            ano = Double.parseDouble(txtAno);

        } catch (Exception e) {

        }

        // Creo un objeto para poder leer el archivo fcl, que contiene las reglas
        // difusas
        String filename = "tipper.fcl";
        FIS fis = FIS.load(filename, true);

        // En caso de no encontrar el archivo
        if (fis == null) {
            System.err.println("Can't load file: '" + filename + "'");
            System.exit(1);
        }

        // Obtengo las funciones por default de este objeto
        FunctionBlock fb = fis.getFunctionBlock(null);

        // Cambio y doy valores a las variables de entrada
        fb.setVariable("food", calificacion);
        fb.setVariable("service", genero);

        // funcion para evaluar
        fb.evaluate();

        // Muestro las variables de salida
        fb.getVariable("tip").defuzzify();

        // Imprimo las reglas difusas
        System.out.println(fb);
        System.out.println("Tip: " + fb.getVariable("tip").getValue());

        // Retorno el resultado de todo para mostrarlo en el campo de texto Resultado
        return " " + fb.getVariable("tip").getValue();
    }

    // Funcion para obtener las graficas
    public static void grafica(String txtCalificacion, String txtGenero, String txtAno, String txtResultado) {
        // Hago lo mismo que en el metodo anterior, convierto esto los string en numeros
        // reales
        double calificacion = 0, genero = 0, ano = 0, resultado = 0;

        try {
            calificacion = Double.parseDouble(txtCalificacion);
            genero = Double.parseDouble(txtGenero);
            ano = Double.parseDouble(txtAno);
            resultado = Double.parseDouble(txtResultado);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al transformar los datos a numeros reales");
        }
        // Igual que el anterior cargo el archivo fcl
        String fileName = "recomendacion.fcl";
        FIS fis = FIS.load(fileName, true);

        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        FunctionBlock functionBlock = fis.getFunctionBlock(null);

        // Funcion para mostrar las graficas
        JFuzzyChart.get().chart(functionBlock);

        fis.setVariable("service", 7);
        fis.setVariable("food", 10);

        fis.evaluate();

        Variable tip = functionBlock.getVariable("tip");
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);

        System.out.println(fis);

        // Creo un objeto para almacenarlo en el historial
        Datos prediccion = new Datos(calificacion, genero, ano, resultado);

        historial.add(prediccion);

    }

}
