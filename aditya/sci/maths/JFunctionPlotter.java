package aditya.sci.maths;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
public class JFunctionPlotter extends JPanel
{
    private ArrayList<FunctionPlot> f=new ArrayList<FunctionPlot>();
    private double xi=-10,xf=10,yi=-10,yf=10,xs=0.1,ys=0.1,x,y;
    private Color col,bgc=Color.WHITE;
    public static byte EQUALS_TO=1;
    public static byte LESSER_THAN=2;
    public static byte GREATER_THAN=4;
    public interface FunctionPlot
    {
        public double[] run(JFunctionPlotter f);
    }
    public JFunctionPlotter()
    {
        super();
    }
    public void paint(Graphics g)
    {
        int w=(int)getSize().getWidth(),h=(int)getSize().getHeight();
        g.setColor(bgc);
        g.fillRect(0,0,w,h);
        for (int i=0;i<f.size();i++)
        {
            FunctionPlot fn=f.get(i);
            for (double x=xi;x<=xf;x+=xs)
            {
                for (double y=yi;y<yf;y+=ys)
                {
                    this.x=x;
                    this.y=y;
                    double[] z=fn.run(this);
                    if (isOnFunction(z[0],z[1]))
                    {
                        System.out.println(x+" "+y);
                        if (z.length>2)
                        {
                            if (z.length>5)
                            {
                                
                            }
                            /*g.setColor(new Color((int));
                            g.fillRect((int)(w/(xf-xi)*(x-xi)),h-(int)(h/(yf-yi)*(y-yi)),1,1);*/
                        }
                        else
                        {
                            g.setColor(col);
                            g.fillRect((int)(w/(xf-xi)*(x-xi)),h-(int)(h/(yf-yi)*(y-yi)),1,1);
                        }
                    }
                }
            }
        }
        
    }
    public void addFunction(FunctionPlot f)
    {
        this.f.add(f);
    }
    public void removeFunction(FunctionPlot f)
    {
        this.f.remove(f);
    }
    public void setGraphOrigin(double x,double y)
    {
        double xi2=xi,xf2=xf,yi2=yi,yf2=yf;
        xi=-x-(xf2-xi2)/2;
        xf=-x+(xf2-xi2)/2;
        yi=-y-(yf2-yi2)/2;
        yf=-y+(yf2-yi2)/2;
    }
    public void setGraphScale(double x,double y)
    {
        double xi2=xi,xf2=xf,yi2=yi,yf2=yf;
        double x2=xi2+(xf2-xi2)/2,y2=yi2+(yf2-yi2)/2;
        //double cxs=xf2-xi2,cys=yf2-yi2;
        xi=x2-x/2;
        xf=x2+x/2;
        yi=y2-y/2;
        yf=y2+y/2;
        //System.out.println((xi2+x2)+" "+(yi2+y2)+" , "+xi+" "+xf+" "+yi+" "+yf);
    }
    public void setDrawScale(double x,double y)
    {
        xs=x;
        ys=y;
    }
    public void setDrawColor(int r,int g,int b,int a)
    {
        col=new Color(r,g,b,a);
    }
    public void setBackgroundColor(int r,int g,int b,int a)
    {
        bgc=new Color(r,g,b,a);
    }
    public double getXCoord()
    {
        return x;
    }
    public double getYCoord()
    {
        return y;
    }
    private boolean isOnFunction(double d,double t)
    {
        boolean b=false;
        double rd=((int)(d*10))/10.0;
        if (((byte)t & EQUALS_TO) >0 && rd==0)
            b=true;
        if (((byte)t & LESSER_THAN) >0 && rd<0)
            b=true;
        if (((byte)t & GREATER_THAN) >0 && rd>0)
            b=true;
        return b;
    }
}
