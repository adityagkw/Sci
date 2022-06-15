package aditya.sci.maths;
/**
Complex number class to manipulate complex numbers.
The complex numbers are accurate to 1E-15 so round it to 1E-15 or use parseComplex() to automatically do it.
@author Aditya Gaikwad
@version 1.0
 */
public class Complex extends Number implements java.io.Serializable,Cloneable
{
    public static Complex ZERO=new Complex(0);
    public static Complex ONE=new Complex(1);
    public static Complex MINUS_ONE=new Complex(-1);
    public static Complex I=new Complex(0,1);
    public static Complex MINUS_I=new Complex(0,-1);
    public static Complex E=new Complex(Math.E);
    public static Complex PI=new Complex(Math.PI);
    public static Complex TAU=new Complex(2*Math.PI);
    /**
    Real value of Complex Number.
    <br>The number can be updated at runtime.
     */
    public double real;
    /**
    Imaginary value of Complex Number.
    <br>The number can be updated at runtime.
     */
    public double imag;
    /**
    Creates Complex number with real part of r and imaginary part of i.
     */
    public Complex(double r,double i)
    {
        real=r;
        imag=i;
    }
    /**
    Creates Complex number with real part of r and imaginary part of 0.
     */
    public Complex(double r)
    {
        real=r;
        imag=0;
    }
    /**
    Creates Complex number from polar form with distance from origin as r, anti-clockwise angle from positive X-axis as a.
    @return Complex number from polar form.
     */
    public static Complex fromPolar(double r,double a)
    {
        return new Complex(r*Math.cos(a),r*Math.sin(a));
    }
    /**
    Clones the Complex number.
    @return Complex number clone of this complex number.
     */
    public Complex clone()
    {
        return new Complex(real,imag);
    }
    /**
    Checks if Complex number a and b are equal.
    @return boolean of weather a is equal to b.
     */
    public static boolean equals(Complex a,Complex b)
    {
        return (a.real==b.real)&(a.imag==b.imag);
    }
    /**
    Adds the Complex numbers.
    <br>a+b
    @return Complex number containing the sum.
     */
    public static Complex add(Complex a,Complex b)
    {
        return new Complex(a.real+b.real,a.imag+b.imag);
    }
    /**
    Subtracts the Complex numbers.
    <br>a-b
    @return Complex number containing the difference.
     */
    public static Complex sub(Complex a,Complex b)
    {
        return new Complex(a.real-b.real,a.imag-b.imag);
    }
    /**
    Multiplies the Complex numbers.
    <br>a*b
    @return Complex number containing the product.
     */
    public static Complex mul(Complex a,Complex b)
    {
        return new Complex((a.real*b.real)-(a.imag*b.imag),(a.real*b.imag)+(a.imag*b.real));
    }
    /**
    Divides the Complex numbers.
    <br>a/b
    @return Complex number containing the quotient.
     */
    public static Complex div(Complex a,Complex b)
    {
        return new Complex(((a.real*b.real)+(a.imag*b.imag))/((b.real*b.real)+(b.imag*b.imag)),(((a.imag*b.real)-(a.real*b.imag))/((b.real*b.real)+(b.imag*b.imag))));
    }
    /**
    Modulor of the Complex numbers.
    <br>a%b
    @return Complex number containing the remainder.
     */
    public static Complex mod(Complex a,Complex b)
    {
        Complex d=Complex.div(a,b);
        Complex c=new Complex(d.real-Math.floor(d.real),d.imag-Math.floor(d.imag));
        return Complex.mul(c,b);
    }
    /**
    Raises Euler's number to the Complex number.
    <br>e^a
    @return Complex number containing the e^a.
     */
    public static Complex exp(Complex a)
    {
        return new Complex(Math.exp(a.real)*Math.cos(a.imag),Math.exp(a.real)*Math.sin(a.imag));
    }
    /**
    Returns the distance of Complex number from the origin.
    @return double containing the absolute value of a.
     */
    public static double abs(Complex a)
    {
        return Math.sqrt((a.real*a.real)+(a.imag*a.imag));
    }
    /**
    Returns the anti-clockwise angle of complex number from positive x axis.
    @return double containing the argument of a.
     */
    public static double arg(Complex a)
    {
        /*if (a.real==0)
            return (a.imag>0?Math.PI/2:-Math.PI/2);
        return Math.atan(a.imag/a.real);*/
        return Math.atan2(a.imag,a.real); 
    }
    /**
    Returns the Complex number which is natural log of n including negative numbers.
    <br>Negative numbers will give log ln(|real|)+i*&#x03c0
    @return Complex number containing the ln of a.
     */
    public static Complex lnReal(double n)
    {
        Complex c=null;
        if (n>0)
        {
            c=new Complex(Math.log(n),0);
        }
        else if (n<0)
        {
            c=new Complex(Math.log(-n),Math.PI);
        }
        return c;
    }
    /**
    Returns the Complex number which is natural log of a.
    @return Complex number containing the ln of a.
     */
    public static Complex ln(Complex a)
    {
        return new Complex(Math.log(abs(a)),arg(a));
    }
    /**
    Returns the Complex number which is log of a with base 10.
    @return Complex number containing the log of a with base 10.
     */
    public static Complex log(Complex a)
    {
        return Complex.div(ln(a),ln(new Complex(10,0)));
    }
    /**
    Returns the Complex number which is  log of a with base b.
    @return Complex number containing the log of a with base b.
     */
    public static Complex log(Complex a,Complex b)
    {
        return Complex.div(ln(a),ln(b));
    }
    /**
    Raises a to the power of b.
    <br>a^b
    @return Complex number containing the value of a^b.
     */
    public static Complex pow(Complex a,Complex b)
    {
        if (a.real==0 && a.imag==0)
            return new Complex(0);
        double s=(a.real*a.real)+(a.imag*a.imag),t=(b.real*arg(a))+(0.5*b.imag*Math.log(s));
        double m=Math.pow(s,b.real/2)*Math.exp(-b.imag*arg(a));
        double rwm=Math.cos(t);
        double iwm=Math.sin(t);
        Complex c=new Complex(m*rwm,m*iwm);
        return c;
    }
    /**
    Finds hypotenuse of triangle with a and b as sides.
    <br>(a^2+b^2)^(1/2)
    @return Complex number containing the value of (a^2+b^2)^(1/2).
     */
    public static Complex hypot(Complex a,Complex b)
    {
        return Complex.pow(Complex.add(Complex.pow(a,new Complex(2)),Complex.pow(b,new Complex(2))),new Complex(0.5));
    }
    /**
    Parses a String to a complex number.
    @return Complex number containing the value of s.
     */
    public static Complex parseComplex(String s)
    {
        String[] sa=s.replace("(","").replace(")","").replace("-","+-").split("[+]");
        double r=0,i=0;
        for (int a=0;a<sa.length;a++)
        {
            if (sa[a].replaceAll("\\d","d").contains("d"))
            {
                if (sa[a].contains("i"))
                    i+=Double.parseDouble(sa[a].replace("i",""));
                else
                    r+=Double.parseDouble(sa[a]);
            }
        }
        return new Complex(r,i);
    }
    /**
    Returns the distance of the Complex number from the origin.
    @return double containing the absolute value of the Complex number.
     */
    public double abs()
    {
        return Math.sqrt((real*real)+(imag*imag));
    }
    public boolean isNotDefined()
    {
        return "NaN".equals(""+real) || "NaN".equals(""+imag) || "Infinity".equals(""+real) || "Infinity".equals(""+imag) || "-Infinity".equals(""+real) || "-Infinity".equals(""+imag);
    }
    @Override
    public String toString()
    {
        return ("( "+((long)(real*1e15))/1.0e15+" + "+((long)(imag*1e15))/1.0e15+"i )");
    }
    @Override
    public double doubleValue()
    {
        return real;
    }
    @Override
    public float floatValue()
    {
        return (float)real;
    }
    @Override
    public long longValue()
    {
        return Math.round(real);
    }
    @Override
    public int intValue()
    {
        return (int)Math.round(real);
    }
    @Override
    public short shortValue()
    {
        return (short)Math.round(real);
    }
    @Override
    public byte byteValue()
    {
        return (byte)Math.round(real);
    }
}
