package aditya.sci.maths;
public class Bezier
{
    strictfp public static double[] point(double n,double[][] points)
    {
        if (points.length==1)
            return points[0];
        double[][] p=new double[points.length-1][points[0].length];
        for (int i=0;i<p.length;i++)
        {
            for (int j=0;j<p[i].length;j++)
            {
                p[i][j]=points[i][j]*(1-n)+points[i+1][j]*n;
            }
        }
        return point(n,p);
    }
    public static void main(String[] args)
    {
        double[][] p=new double[][]{
        {0,0},
        {5,5},
        {7,7},
        {10,10}
        };
        for (double n=0;n<=1;n+=0.1)
        {
            double[] bp=Bezier.point(n,p);
            for (int i=0;i<bp.length;i++)
            {
                System.out.print(bp[i]+"\t");
            }
            System.out.println();
        }
    }
}
