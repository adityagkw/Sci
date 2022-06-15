package aditya.sci.Test;
import aditya.sci.maths.*;
public class RegressionTest
{
    public static void main(String[] args)
    {
        double[][] X=new double[100][1];
        for(int i=0;i<X.length;i++)
        {
            for (int j=0;j<X[i].length;j++)
            {
                X[i][j]=Math.random()*1;
            }
        }
        double[] Y=new double[X.length];
        for (int i=0;i<Y.length;Y[i]=f(X[i]),i++);
        Regression r=new Regression(5);
        r.fit(X,Y);
        r.train();
        System.out.println(r.mat);
        System.out.println(r.predict(new double[]{Math.PI/2}));
    }
    public static double f(double[] arg)
    {
        double x=arg[0];
        return Math.sin(x);
    }
}