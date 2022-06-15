package aditya.sci.Test;
import aditya.sci.maths.*;
public class KMeansTest
{
    public static void main(String[] args)
    {
        
        double[][] c=
        {
            {0.5,0.5},
            {0.1,0.1},
            {0.9,0.9}
        };
        double[] r=
        {
            0.3,
            0.1,
            0.1
        };
        KMeans m=new KMeans(c.length);
        for (int i=0;i<c.length;i++)
        {
            for (int j=0;j<10;j++)
            {
                m.fit(circle(c[i],r[i]));
            }
        }
        m.train(100);
        int[] group=new int[c.length];
        for (int i=0;i<c.length;i++)
        {
            group[i]=m.predict(c[i]);
            System.out.println(group[i]+" "+string(c[i])+" "+r[i]);
        }
        for (int i=0;i<10;i++)
        {
            int j=i%c.length;
            double[] point=circle(c[j],r[j]);
            int p=m.predict(point);
            System.out.println("Required: "+group[j]+"\tPredicted: "+p+"\t Point: "+string(point));
        }
    }
    private static double[] circle(double[] center,double radius)
    {
        double[] point=new double[center.length];
        double h=2;
        while(h>1)
        {
            h=0;
            for (int i=0;i<point.length;i++)
            {
                point[i]=Math.random();
                h+=point[i]*point[i];
                point[i]=point[i]*radius+center[i];
            }
        }
        return point;
    }
    private static String string(double[] point)
    {
        String s="(";
        for (int i=0;i<point.length;i++)
        {
            s+=(i==0?"":",")+point[i];
        }
        return s+")";
    }
}
