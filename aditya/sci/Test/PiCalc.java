package aditya.sci.Test;
import aditya.sci.maths.*;
public class PiCalc
{
    public static void main(String args[])
    {
        double dx=0.000000001;
        //System.out.println(Complex.mul(Maths.S(new Complex(-1,0),new Complex(1,0),PiCalc::pi,new Complex(dx,0)),new Complex(2,0)));
        double ans=0;
        for (double x=-1;x<=1;ans=ans+Math.sqrt(1-x*x)*dx,x=x+dx);
        System.out.println(ans);
    }
    public static Complex pi(Complex c)
    {
        return new Complex(Math.sqrt(1-Math.pow(c.real,2)),0);
    }
}
