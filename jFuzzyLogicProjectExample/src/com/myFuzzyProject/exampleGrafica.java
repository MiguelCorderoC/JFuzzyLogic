package com.myFuzzyProject;

import javax.swing.*;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class exampleGrafica {
    public static void main(String[] args) throws Exception {
        String filename = "tipper.fcl";
        FIS fis = FIS.load(filename, true);

        if (fis == null) {
            System.err.println("Can't load file: '" + filename + "'");
            System.exit(1);
        }

        // Get default function block
        FunctionBlock fb = fis.getFunctionBlock(null);

        // Set inputs
        fb.setVariable("food", 8.5);
        fb.setVariable("service", 7.5);

        // Evaluate
        fb.evaluate();

        // Show output variable's chart
        double tipValue = fb.getVariable("tip").getValue();
        System.out.println("Tip: " + tipValue);
        // JOptionPane.showMessageDialog(null, "Tip: " + tipValue);

        // Create chart dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(tipValue, "Tip", "Output");

        // Create the chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Recomendacion",
                "Salida",
                "Tip Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        // Show the chart in a frame
        JFrame frame = new JFrame("Tip Output");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}