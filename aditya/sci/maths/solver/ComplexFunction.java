package aditya.sci.maths.solver;
import aditya.sci.maths.*;
public interface ComplexFunction
{
    public Complex run(Complex... x);
    public static Complex runRoot(ComplexFunction f,Complex[][] roots,int i,Complex... x)
    {
        Complex c = f.run(x);
        //System.out.println(c);
        Complex dist=Complex.ONE;
        for (int a=0;a<roots.length;a++)
        {
            if (a!=i)
            {
                for (int b=0;b<x.length;b++)
                {
                    dist = Complex.mul(dist,Complex.sub(x[b],roots[a][b]));
                }
            }
        }
        //System.out.println(c+"/"+dist);
        c = Complex.div(c,dist);
        //System.out.println(c);
        return c;
    }
    public static Complex[] clone(Complex[] parameter)
    {
        Complex[] c = new Complex[parameter.length];
        for (int i=0;i<c.length;c[i]=parameter[i].clone(),i++);
        return c;
    }
}
