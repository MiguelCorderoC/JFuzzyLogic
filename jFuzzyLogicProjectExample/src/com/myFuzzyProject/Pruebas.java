package com.myFuzzyProject;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
 * Test parsing an FCL file
 * 
 * @author pcingola@users.sourceforge.net
 */
public class Pruebas {
    public static void main(String[] args) throws Exception {
        // Load from 'FCL' file
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
    }
}