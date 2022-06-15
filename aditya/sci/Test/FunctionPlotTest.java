package aditya.sci.Test;
import javax.swing.*;
import aditya.sci.maths.*;
import java.awt.*;
public class FunctionPlotTest
{
    public static void main(String args[])
    {
        JFrame f=new JFrame("Function Plot Test");
        f.setSize(500,500);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setUndecorated(true);
        
        JFunctionPlotter p=new JFunctionPlotter();
        p.setGraphOrigin(0,0);
        p.setGraphScale(10,10);
        p.setDrawScale(0.001,0.001);
        p.setBackgroundColor(128,255,128,255);
        p.addFunction(FunctionPlotTest::f);
        f.add(p,BorderLayout.CENTER);
        
        f.setVisible(true);
    }
    public static double[] f(JFunctionPlotter p)
    {
        double x=p.getXCoord(),y=p.getYCoord();
        p.setDrawColor(255,200,128,255);
        double[] d=new double[2];
        d[1]=JFunctionPlotter.LESSER_THAN;
        d[0]=Graph.Circle(x,y,0,0,0.1);
        return d;
    }
}
