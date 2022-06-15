package aditya.sci.maths;
public class FourierSeries
{
    Complex[] c;
    int n;
    double dx=0.001;
    MathFunction f;
    public FourierSeries(int n)
    {
        this.n=n;
        c=new Complex[2*n+1];
    }
    public Complex constant(int a)
    {
        Complex s=new Complex(0);
        Complex dxc=new Complex(dx);
        for (double i=0;i<1;i+=dx)
        {
            s=s.add(s,s.mul(s.mul(f.f(new Complex(i)),s.exp(new Complex(0,-a*i*Math.PI*2))),dxc));
        }
        return s;
    }
    public void create()
    {
        for (int i=-n;i<=n;i++)
        {
            c[i+n]=constant(i);
            System.out.println(i+" "+c[i+n]);
        }
    }
    public Complex get(double v)
    {
        Complex a=new Complex(0);
        for (int i=-n;i<=n;i++)
        {
            a=a.add(a,a.mul(c[i+n],a.exp(new Complex(0,v*i*Math.PI*2))));
        }
        return a;
    }
    public static void main(String[] args)
    {
        MathFunction fun=new MathFunction()
        {
            public Complex f(Complex c)
            {
                //return new Complex(Math.cos(2*Math.PI*c.real));
                return Complex.exp(new Complex(0,c.real*2*Math.PI));
            }
        };
        FourierSeries f=new FourierSeries(10);
        f.f=fun;
        f.create();
        System.out.println(f.get(1.0/2.0));
    }
}
