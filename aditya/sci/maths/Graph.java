package aditya.sci.maths;
public class Graph
{
    private static double nan=Math.sqrt(-1);
    public static double Exclude(double axis,double a,double b)
    {
        try
        {
            double add=(b+a)/2,l=(b-a)/2;
            return Math.sqrt(Math.abs(Math.abs(axis-add)-l)/(Math.abs(axis-add)-l));
        }catch (Exception e){}
        return nan;
    }
    public static double Include(double axis,double a,double b)
    {
        try
        {
            double add=(b+a)/2,l=(b-a)/2;
            return Math.sqrt(-Math.abs(Math.abs(axis-add)-l)/(Math.abs(axis-add)-l));
        }catch(Exception e){}
        return nan;
    }
    public static double Circle(double axis,double otheraxis,double h,double k,double r)
    {
        try
        {
            double v1=axis-h,v2=otheraxis-k;
            return ((v1*v1+v2*v2)/(r*r))-1;
        }catch(Exception e){}
        return nan;
    }
    public static double Eclipse(double axis,double otheraxis,double h,double k,double a,double b)
    {
        try
        {
            double v1=axis-h,v2=otheraxis-k;
            return (((v1*v1)/(a*a))+((v2*v2)/(b*b)))-1;
        }catch(Exception e){}
        return nan;
    }
    public static double Hyperbola(double axis,double otheraxis,double h,double k,double a,double b)
    {
        try
        {
            double v1=axis-h,v2=otheraxis-k;
            return (((v1*v1)/(a*a))-((v2*v2)/(b*b)))-1;
        }catch(Exception e){}
        return nan;
    }
    public static double Parabola(double axis,double otheraxis,double a,double b,double c)
    {
        try
        {
            return (a*axis*axis)+(b*axis)+c-otheraxis;
        }catch(Exception e){}
        return nan;
    }
}