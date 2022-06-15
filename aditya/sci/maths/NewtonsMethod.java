package aditya.sci.maths;

public class NewtonsMethod
{
    double dx=0.0001;
    protected double f(double x)
    {
        return x*x-3;
    }
    public double df(double x)
    {
        return (f(x+dx)-f(x))/dx;
    }
    public double iterate(double px)
    {
        return px-f(px)/df(px);
    }
    public double iterate(double ix,int i)
    {
        double x=ix;
        while(i>0)
        {
            i--;
            x=iterate(x);
        }
        return x;
    }
}
