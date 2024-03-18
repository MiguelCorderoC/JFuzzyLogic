package com.myFuzzyProject;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Prediccion {

    static ArrayList<Datos> historial = new ArrayList<>();

    public static String recomendar(String txtCalificacion, String txtGenero, String txtAno) {
        double calificacion = 0;
        double genero = 0;
        double ano;

        try {
            calificacion = Double.parseDouble(txtCalificacion);
            genero = Double.parseDouble(txtGenero);
            ano = Double.parseDouble(txtAno);

        } catch (Exception e) {

        }

        String filename = "tipper.fcl";
        FIS fis = FIS.load(filename, true);

        if (fis == null) {
            System.err.println("Can't load file: '" + filename + "'");
            System.exit(1);
        }

        // Get default function block
        FunctionBlock fb = fis.getFunctionBlock(null);

        // Set inputs
        fb.setVariable("food", calificacion);
        fb.setVariable("service", genero);

        // Evaluate
        fb.evaluate();

        // Show output variable's chart
        fb.getVariable("tip").defuzzify();

        // Print ruleSet
        System.out.println(fb);
        System.out.println("Tip: " + fb.getVariable("tip").getValue());
        // JOptionPane.showMessageDialog(null, "Tip: " +
        // fb.getVariable("tip").getValue());

        return " " + fb.getVariable("tip").getValue();
    }

    /*
     * public static void grafica(String txtCalificacion, String txtGenero, String
     * txtAno, String txtResultado) {
     * double calificacion = 0;
     * double genero = 0;
     * double ano = 0;
     * double resultado = 0;
     * try {
     * calificacion = Double.parseDouble(txtCalificacion);
     * genero = Double.parseDouble(txtGenero);
     * ano = Double.parseDouble(txtAno);
     * resultado = Double.parseDouble(txtResultado);
     * 
     * } catch (NumberFormatException e) {
     * JOptionPane.showMessageDialog(null,
     * "Error: Ingresa valores numéricos válidos en las áreas de texto");
     * return; // Salir del método si hay un error
     * }
     * 
     * String filename = "tipper.fcl";
     * FIS fis = FIS.load(filename, true);
     * 
     * if (fis == null) {
     * System.err.println("Can't load file: '" + filename + "'");
     * JOptionPane.showMessageDialog(null,
     * "Error: No se puede cargar el archivo FCL");
     * return; // Salir del método si no se puede cargar el archivo FCL
     * }
     * 
     * // Get default function block
     * FunctionBlock fb = fis.getFunctionBlock(null);
     * 
     * // Set inputs
     * fb.setVariable("food", calificacion);
     * fb.setVariable("service", genero);
     * 
     * // Evaluate
     * fb.evaluate();
     * 
     * // Show output variable's chart
     * double tipValue = fb.getVariable("tip").getValue();
     * System.out.println("Tip: " + tipValue);
     * // JOptionPane.showMessageDialog(null, "Tip: " + tipValue);
     * 
     * // Create chart dataset
     * DefaultCategoryDataset dataset = new DefaultCategoryDataset();
     * dataset.addValue(tipValue, "Tip", "Output");
     * 
     * // Create the chart
     * JFreeChart chart = ChartFactory.createBarChart(
     * "Recomendacion",
     * "Salida",
     * "Tip Value",
     * dataset,
     * PlotOrientation.VERTICAL,
     * true,
     * true,
     * false);
     * 
     * // Show the chart in a frame
     * JFrame frame = new JFrame("Tip Output");
     * frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiado a
     * DISPOSE_ON_CLOSE
     * frame.getContentPane().add(new ChartPanel(chart));
     * frame.pack();
     * frame.setVisible(true);
     * 
     * Datos prediccion = new Datos(calificacion, genero, ano, resultado);
     * 
     * historial.add(prediccion);
     * 
     * }
     */

    public static void grafica(String txtCalificacion, String txtGenero, String txtAno, String txtResultado) {
        double calificacion = 0, genero = 0, ano = 0, resultado = 0;

        try {
            calificacion = Double.parseDouble(txtCalificacion);
            genero = Double.parseDouble(txtGenero);
            ano = Double.parseDouble(txtAno);
            resultado = Double.parseDouble(txtResultado);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al transformar los datos a numeros reales");
        }

        String fileName = "recomendacion.fcl";
        FIS fis = FIS.load(fileName, true);

        // Error while loading?
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Get the function block
        FunctionBlock functionBlock = fis.getFunctionBlock(null);

        // Show
        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        fis.setVariable("service", 7);
        fis.setVariable("food", 10);

        // Evaluate
        fis.evaluate();

        // Show output variable's chart
        Variable tip = functionBlock.getVariable("tip");
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);

        // Print ruleSet
        System.out.println(fis);

        Datos prediccion = new Datos(calificacion, genero, ano, resultado);

        historial.add(prediccion);

    }

}
