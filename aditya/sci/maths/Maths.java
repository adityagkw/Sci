package aditya.sci.maths;
public class Maths
{
    public static long factorial(long n)
    {
        long out=1;
        for (long i=2;i<=n;out*=i,i++);
        return out;
    }
    public static Complex fib(Complex n)
    {
        return Complex.div(Complex.sub(Complex.pow(new Complex(Constant.PHI,0),n),Complex.pow(new Complex(-Constant.PHIm1,0),n)),new Complex(Math.sqrt(5),0));
    }
    public static double benford(long n,long b)
    {
        return Math.log(1+1/n)/Math.log(b);
    }
    public static double[] benfordCheck(int b)
    {
        double a=0;
        int n[]=new int[b-1];
        for (int i=0;i<100;i++)
        {
            int r=0;
            while (r==0)
                r=(int)(Math.random()*Math.pow(2,(int)(Math.random()*10)));
            while(r>b-1)
                r/=b;
            System.out.println(r);
            n[r-1]++;
        }
        double[] d=new double[b-1];
        int s=0;
        for (int i=0;i<n.length;s+=n[i],i++);
        for (int i=0;i<n.length;d[i]=((double)n[i])/(double)s,i++);
        return d;
    }
    public static long pi(long n)
    {
        long out=1;
        for (long p=2;p<=n;out*=p,p=nextPrime(p));
        return out;
    }
    public static long P(long n,long r)
    {
        return (factorial(n)/factorial(n-r));
    }
    public static long C(long n,int r)
    {
        return (P(n,r)/factorial(r));
    }
    public static long nextPrime(long n)
    {
        for (boolean b=false;!b;n++,b=isPrime(n));
        return n;
    }
    public static long previousPrime(long n)
    {
        for (boolean b=false&& n>1;!b;n--,b=isPrime(n));
        return n;
    }
    public static long nextComposite(long n)
    {
        for (boolean b=false;!b;n++,b=isComposite(n));
        return n;
    }
    public static long previousComposite(long n)
    {
        for (boolean b=false&& n>1;!b;n--,b=isComposite(n));
        return n;
    }
    public static boolean isPrime(long n)
    {
        boolean b=true;
        for (int i=2;i<n;i++)
        {
            if (n%i==0)
            {
                b=false;
                break;
            }
        }
        return b;
    }
    public static boolean isComposite(long n)
    {
        boolean b=false;
        for (int i=2;i<n;i++)
        {
            if (n%i==0)
            {
                b=true;
                break;
            }
        }
        return b;
    }
    public static long[] factorize(long n)
    {
        long[] a=new long[(int)countPrimes(2,n)];
        for (long i=0, p=2;p<n;p=nextPrime(p),i++)
        {
            for(long n2=n;n2%p==0;n2/=p,a[(int)i]++);
        }
        return a;
    }
    public static long countPrimes(long a,long b)
    {
        long n=0;
        for (long i=a;i<=b;i++)
        {
            if (isPrime(i))
            {
                n++;
            }
        }
        return n;
    }
    public static long approxPrimesBefore(long n)
    {
        double ans=0;
        ans=(double)n/Math.log(n);
        return (long)ans;
    }
    public static long approxPrimesBefore2(long n)
    {
        double ans=0;
        ans=(double)n/(Math.log(n)-1);
        return (long)ans;
    }
    public static long approxPrimesBefore3(long n)
    {
        double ans=0;
        for (long i=2;i<n;i++)
        {
            ans+=1/Math.log(i);
        }
        //ans=(double)n/Math.log(n);
        return (long)ans;
    }
    public static double Sigma(double[] n)
    {
        double out=0;
        for (int i=0;i<n.length;out+=n[i],i++);
        return out;
    }
    public static double Pi(double[] n)
    {
        double out=1;
        for (int i=0;i<n.length;out*=n[i],i++);
        return out;
    }
    public static Complex Sigma(Complex[] n)
    {
        Complex out=new Complex(0,0);
        for (int i=0;i<n.length;out=Complex.add(out,n[i]),i++);
        return out;
    }
    public static Complex Pi(Complex[] n)
    {
        Complex out=n[0];
        for (int i=1;i<n.length;out=Complex.mul(out,n[i]),i++);
        return out;
    }
    public static Complex Sigma(MathFunction f,Complex low,Complex high,Complex step)
    {
        Complex out=new Complex(0,0);
        for (Complex c=low;c.imag<=high.imag&&c.real<=high.real;Complex.add(out,c),Complex.add(c,step));
        return out;
    }
    public static Complex Pi(MathFunction f,Complex low,Complex high,Complex step)
    {
        Complex out=new Complex(1,0);
        
        for (Complex c=low;c.imag<=high.imag&&c.real<=high.real;Complex.mul(out,c),c=Complex.add(c,step));
        return out;
    }
    public static Complex d_dx(MathFunction f,Complex x,Complex dx)
    {
        Complex out;
        out=Complex.sub(f.f(Complex.add(x,dx)),f.f(x));
        out=Complex.div(out,dx);
        return out;
    }
    public static Complex S(Complex low,Complex high,MathFunction f,Complex dx)
    {
        Complex out=new Complex(0,0);
        for (Complex a=low;a.imag<=high.imag&&a.real<=high.real;out=Complex.add(out,Complex.mul(f.f(a),dx)),a=Complex.add(a,dx));
        return out;
    }
    public static Complex ei(double x)
    {
        return Complex.pow(new Complex(Math.E,0),new Complex(0,x));
    }
    public static Complex sin(Complex c)
    {
        Complex epc=Complex.pow(new Complex(Math.E,0),new Complex(-c.imag,c.real));
        Complex epi=Complex.pow(new Complex(Math.E,0),new Complex(c.imag,-c.real));
        Complex add=Complex.sub(epc,epi);
        return Complex.div(add,new Complex(0,2));
    }
    public static Complex cos(Complex c)
    {
        Complex epc=Complex.pow(new Complex(Math.E,0),new Complex(-c.imag,c.real));
        Complex epi=Complex.pow(new Complex(Math.E,0),new Complex(c.imag,-c.real));
        Complex add=Complex.add(epc,epi);
        return Complex.div(add,new Complex(2,0));
    }
    public static Complex tan(Complex c)
    {
        return Complex.div(sin(c),cos(c));
    }
    public static Complex W(Complex c)
    {
        Complex p=c;
        if (Complex.abs(c)>0.1)
        {
            p=Complex.ln(c);
        }
        for (int i=0;i<100;i++)
        {
            p=Complex.sub(p,Complex.div(Complex.sub(Complex.mul(p,Complex.exp(p)),c),Complex.mul(Complex.add(p,new Complex(1)),Complex.exp(p))));
        }
        return p;
    }
    public static Complex gcd(Complex a,Complex b)
    {
        if (a.real==0 && a.imag==0)
            return b;
        return gcd(Complex.mod(b,a),a);
    }
}
