package aditya.sci.maths;


public class FourierTransform
{
    public static Complex[] fft(Complex[] c)
    {
        int l = (int)Math.pow(2,Math.ceil(Math.log(c.length)/Math.log(2)));
        System.out.println(l);
        Complex[] x=new Complex[l];
        for (int i=0;i<c.length;i++)
        {
            x[i]=c[i];
        }
        for (int i=c.length;i<l;i++)
        {
            x[i]=Complex.ZERO;
        }
        Complex[] y=fft_(x);
        return y;
    }
    public static Complex[] inverse_fft(Complex[] c)
    {
        int l = (int)Math.pow(2,Math.ceil(Math.log(c.length)/Math.log(2)));
        Complex[] x=new Complex[l];
        for (int i=0;i<c.length;i++)
        {
            x[i]=c[i];
        }
        for (int i=c.length;i<l;i++)
        {
            x[i]=Complex.ZERO;
        }
        Complex[] y=ifft_(x);
        for (int i=0;i<y.length;i++)
        {
            y[i]=Complex.div(y[i],new Complex(l));
        }
        return y;
    }
    private static Complex[] fft_(Complex[] c)
    {
        int n=c.length;
        if (n==1)
        {
            return c;
        }
        Complex w = Complex.pow(Complex.E,new Complex(0,2*Math.PI/n));
        Complex[] xe = new Complex[n/2];
        Complex[] xo = new Complex[n/2];
        for (int i=0;i<n/2;i++)
        {
            xe[i]=c[2*i];
            xo[i]=c[2*i+1];
        }
        Complex[] e=fft_(xe);
        Complex[] o=fft_(xo);
        Complex[] y =new Complex[n];
        for (int i=0;i<n/2;i++)
        {
            y[i]=Complex.add(e[i],Complex.mul(Complex.pow(w,new Complex(i)),o[i]));
            y[i+n/2]=Complex.sub(e[i],Complex.mul(Complex.pow(w,new Complex(i)),o[i]));
        }
        return y;
    }private static Complex[] ifft_(Complex[] c)
    {
        int n=c.length;
        if (n==1)
        {
            return c;
        }
        Complex w = Complex.pow(Complex.E,new Complex(0,-2*Math.PI/n));
        Complex[] xe = new Complex[n/2];
        Complex[] xo = new Complex[n/2];
        for (int i=0;i<n/2;i++)
        {
            xe[i]=c[2*i];
            xo[i]=c[2*i+1];
        }
        Complex[] e=ifft_(xe);
        Complex[] o=ifft_(xo);
        Complex[] y =new Complex[n];
        for (int i=0;i<n/2;i++)
        {
            y[i]=Complex.add(e[i],Complex.mul(Complex.pow(w,new Complex(i)),o[i]));
            y[i+n/2]=Complex.sub(e[i],Complex.mul(Complex.pow(w,new Complex(i)),o[i]));
        }
        return y;
    }
    public static void main(String[] args)
    {
        Complex[] c = new Complex[]{Complex.ONE,Complex.ZERO,Complex.ONE};
        Complex[] a=fft(c);
        Complex[] b=inverse_fft(a);
        System.out.println("A");
        for (int i=0;i<a.length;i++)
        {
            System.out.println(a[i]);
        }
        System.out.println("B");
        for (int i=0;i<b.length;i++)
        {
            System.out.println(b[i]);
        }
    }
}
