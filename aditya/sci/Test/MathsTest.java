package aditya.sci.Test;
import aditya.sci.maths.*;
public class MathsTest
{
    public static void main(String args[])
    {
        /*long[] f=Maths.factorize(100);
        for (long i=0,n=2;i<f.length;i++)
        {
            System.out.println(n+"\t"+f[(int)i]);
            n=Maths.nextPrime(n);
        }
        //System.out.println(Maths.tan(new Complex(Math.PI/4,0)));
        System.out.println(Maths.ei(Math.PI));*/
        System.out.println(Maths.d_dx(MathsTest::f,new Complex(1,0),new Complex(0.0000000001,0)));
        System.out.println(Maths.S(new Complex(0,0),new Complex(1,0),MathsTest::f,new Complex(0.0001,0)));
    }
    public static Complex f(Complex x)
    {
        return Complex.pow(x,new Complex(2,0));
    }
}
