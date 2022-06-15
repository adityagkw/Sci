package aditya.sci.maths.solver;
import aditya.sci.maths.*;
public class Differentiate
{
    public static Complex dx = new Complex(0.0001);
    public static Complex diff(ComplexFunction f,Complex[] parameter,int i)
    {
        Complex[] p = ComplexFunction.clone(parameter);
        Complex a1 = f.run(p);
        p[i]=Complex.add(p[i],dx);
        Complex a2 = f.run(p);
        Complex d=Complex.div(Complex.sub(a2,a1),dx);
        return d;
    }
    public static Complex diff(ComplexFunction f,Complex[] parameter,int i,Complex[][] roots,int j)
    {
        Complex[] p = ComplexFunction.clone(parameter);
        Complex a1 = ComplexFunction.runRoot(f,roots,j,p);
        p[i]=Complex.add(p[i],dx);
        Complex a2 = ComplexFunction.runRoot(f,roots,j,p);
        Complex d=Complex.div(Complex.sub(a2,a1),dx);
        return d;
    }
    public static Complex diff(ComplexFunction f,Complex[] parameter,int i,int order)
    {
        if (order<=0)
        {
            return f.run(parameter);
        }
        else if (order==1)
        {
            return diff(f,parameter,i);
        }
        Complex[] p = ComplexFunction.clone(parameter);
        Complex a1 = diff(f,p,i,order-1);
        p[i]=Complex.add(p[i],dx);
        Complex a2 = diff(f,p,i,order-1);
        Complex d=Complex.div(Complex.sub(a2,a1),dx);
        return d;
    }
    
    public static void main(String[] args)
    {
        ComplexFunction f = new ComplexFunction()
        {
            public Complex run(Complex... x)
            {
                return Complex.pow(x[0],new Complex(3));
            }
        };
        Complex[] p = new Complex[]{new Complex(1)};
        System.out.println(diff(f,p,0,2));
    }
}
