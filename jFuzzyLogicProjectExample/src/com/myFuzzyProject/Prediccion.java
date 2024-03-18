package com.myFuzzyProject;

import javax.swing.JOptionPane;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class Prediccion {

    public static double recomendar(double calificacion, double genero, double ano) {
        String filename = "recomendador.fcl";
        FIS fis = FIS.load(filename, true);

        if (fis == null) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo fcl");
            System.exit(1);
        }

        // Get default function block
        FunctionBlock fb = fis.getFunctionBlock(null);

        // Obtenemos los datos de entrada desde el la App
        fb.setVariable("calificacion", calificacion);
        fb.setVariable("genero", genero);
        fb.setVariable("ano", ano);

        // Funcion para evaluar el resultado
        fb.evaluate();

        // Obtenemos el dato de salida
        fb.getVariable("recomendacion").defuzzify();

        // Aqui solo imprimo el resultado como prueba
        System.out.println(fb);
        System.out.println("Tip: " + fb.getVariable("recomendacion").getValue());
        JOptionPane.showMessageDialog(null, "Tip: " + fb.getVariable("recomendacion").getValue());

        // retorno el resultado para imprimirlo en mi App
        return fb.getVariable("recomendacion").defuzzify();
    }

    public static void grafica() {

    }

}
