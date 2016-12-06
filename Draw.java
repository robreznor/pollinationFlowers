/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pollinationFlowers;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rob
 */
public class Draw extends JFrame {
    
    public Draw(int[] x, int[] y, String title){
        Plot plot= new Plot(title);
        plot.Data("Óptimo",x,y);
        JPanel panel=plot.GetPanel();
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
    }
}
