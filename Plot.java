/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pollinationFlowers;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author rob
 */
public class Plot {
    
    JFreeChart plot;
    XYSeriesCollection data = new XYSeriesCollection();
    
    public Plot(String title){
        plot= ChartFactory.createXYLineChart(title, "Iteraciones", "Fitness", data, PlotOrientation.VERTICAL, true, true, true);
    }
    
    public void Data(String id,int[] x,int[] y){
        XYSeries s=new XYSeries(id);
        int n=x.length;
        for(int i=0;i<n;i++){
            System.out.println(""+x[i]+ " " +y[i]);
            s.add(x[i],y[i]);
        }
        data.addSeries(s);
    }
    
    public JPanel GetPanel(){
        return new ChartPanel(plot);
    }
}
