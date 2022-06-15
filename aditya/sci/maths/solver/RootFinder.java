package aditya.sci.maths.solver;
import aditya.sci.maths.*;
public class RootFinder
{
    public static int max_iterations=100;
    public static double min_change=0.00001;
    public static Complex Newton(ComplexFunction f,Complex[] parameter,int i)
    {
        Complex dx=Complex.ONE;
        Complex[] p=ComplexFunction.clone(parameter);
        Complex v,d;
        int t=0;
        while (Complex.abs(dx)>min_change && t<max_iterations)
        {
            t++;
            v = f.run(p);
            d = Differentiate.diff(f,p,i);
            dx = Complex.div(v,d);
            if (dx.isNotDefined())
            {
                p[i] = Complex.add(p[i],new Complex(Math.random()*2-1,Math.random()*2-1));
                continue;
            }
            p[i] = Complex.sub(p[i],dx);
        }
        return p[i];
    }
    public static Complex[] Newton(ComplexFunction[] f,Complex[] parameter,int[] i)
    {
        ComplexMatrix dx=new ComplexMatrix(f.length,1);
        Complex min_dx=Complex.ONE;
        Complex[] p=ComplexFunction.clone(parameter);
        ComplexMatrix j=new ComplexMatrix(f.length,i.length);
        ComplexMatrix eval=new ComplexMatrix(f.length,1);
        Complex v,d;
        int t=0;
        while (Complex.abs(min_dx)>min_change && t<max_iterations)
        {
            t++;
            for (int a=0;a<f.length;a++)
            {
                for (int b=0;b<i.length;b++)
                {
                    if (b==-1)
                    {
                        b=0;
                    }
                    d=Differentiate.diff(f[a],p,b);
                    if (Complex.div(Complex.ONE,d).isNotDefined())
                    {
                        p[b] = Complex.add(p[b],new Complex(Math.random()*2-1,Math.random()*2-1));
                        a=0;
                        b=-1;
                        continue;
                    }
                    j.set(a,b,d);
                }
            }
            //System.out.println("J: "+j);
            j = ComplexMatrix.inverse(j);
            for (int a=0;a<f.length;a++)
            {
                eval.set(a,0,f[a].run(p));
            }
            dx = ComplexMatrix.mul(j,eval);
            //System.out.println("J-1: "+j);
            //System.out.println("eval: "+eval);
            //System.out.println("dx: "+dx);
            for (int a=0;a<f.length;a++)
            {
                p[a] = Complex.sub(p[a],dx.get(a,0));
                //System.out.println(p[a]);
            }
            min_dx=dx.get(0,0);
            for (int a=1;a<f.length;a++)
            {
                if (Complex.abs(dx.get(a,0))<Complex.abs(min_dx))
                {
                    min_dx=dx.get(a,0);
                }
            }
        }
        return p;
    }
    public static Complex[] Aberth(ComplexFunction f,Complex[] parameter,int i,int roots)
    {
        Complex[][] p = new Complex[roots][parameter.length];
        for (int a=0;a<roots;p[a]=ComplexFunction.clone(parameter),a++);
        Complex min_dx=Complex.ONE;
        Complex[] dx=new Complex[roots];
        Complex v,d,dist;
        int t=0;
        while (Complex.abs(min_dx)>min_change && t<max_iterations)
        {
            t++;
            for (int a=0;a<roots;a++)
            {
                v = f.run(p[a]);
                d = Differentiate.diff(f,p[a],i);
                dx[a] = Complex.div(d,v);
                for (int b=0;b<roots;b++)
                {
                    if (b!=a)
                    {
                        dist = Complex.div(Complex.ONE,Complex.sub(p[a][i],p[b][i]));
                        dx[a] = Complex.sub(dx[a],dist);
                    }
                }
                dx[a]=Complex.div(Complex.ONE,dx[a]);
                if (dx[a].isNotDefined())
                {
                    p[a][i] = Complex.add(p[a][i],new Complex(Math.random()*2-1,Math.random()*2-1));
                    a--;
                    continue;
                }
                p[a][i] = Complex.sub(p[a][i],dx[a]);
            }
            min_dx=dx[0];
            for (int a=1;a<roots;a++)
            {
                if (Complex.abs(dx[a])<Complex.abs(min_dx))
                {
                    min_dx=dx[a];
                }
            }
        }
        Complex[] rs = new Complex[roots];
        for (int a=0;a<roots;rs[a]=p[a][i],a++);
        return rs;
    }
    public static Complex[][] Aberth(ComplexFunction[] f,Complex[] parameter,int[] i,int roots)
    {
        ComplexMatrix dx=new ComplexMatrix(f.length,1);
        Complex min_dx=Complex.ONE;;
        Complex[][] p=new Complex[roots][];
        for (int a=0;a<roots;p[a]=ComplexFunction.clone(parameter),a++);
        for (int a=0;a<roots;a++)
        {
            for (int b=0;b<parameter.length;b++)
            {
                p[a][b] = Complex.add(p[a][b],new Complex(Math.random()*2-1,Math.random()*2-1));
            }
        }
        ComplexMatrix j=new ComplexMatrix(f.length,i.length);
        ComplexMatrix eval=new ComplexMatrix(f.length,1);
        Complex v,d;
        int t=0;
        while (Complex.abs(min_dx)>min_change && t<max_iterations)
        {
            t++;
            for (int r=0;r<roots;r++)
            {
                for (int a=0;a<f.length;a++)
                {
                    for (int b=0;b<i.length;b++)
                    {
                        if (b==-1)
                        {
                            b=0;
                        }
                        d=Differentiate.diff(f[a],p[r],b,p,r);
                        /*if (Complex.div(Complex.ONE,d).isNotDefined())
                        {
                            p[r][b] = Complex.add(p[r][b],new Complex(Math.random()*2-1,Math.random()*2-1));
                            a=0;
                            b=-1;
                            continue;
                        }*/
                        //System.out.println(d);
                        j.set(a,b,d);
                    }
                }
                System.out.println("J: "+j);
                j = ComplexMatrix.inverse(j);
                /*if (j==null)
                {
                    for (int a=0;a<roots;a++)
                    {
                        for (int b=0;b<parameter.length;b++)
                        {
                            p[a][b] = Complex.add(p[a][b],new Complex(Math.random()*1-0.5,Math.random()*1-0.5));
                        }
                    }
                    j=new ComplexMatrix(f.length,i.length);
                    continue;
                }*/
                for (int a=0;a<f.length;a++)
                {
                    eval.set(a,0,ComplexFunction.runRoot(f[a],p,r,p[r]));
                }
                dx = ComplexMatrix.mul(j,eval);
                //System.out.println("J-1: "+j);
                //System.out.println("eval: "+eval);
                //System.out.println("dx: "+dx);
                for (int a=0;a<f.length;a++)
                {
                    p[r][a] = Complex.sub(p[r][a],dx.get(a,0));
                    System.out.println(p[r][a]);
                }
                for (int a=1;a<f.length;a++)
                {
                    if (Complex.abs(dx.get(a,0))<Complex.abs(min_dx))
                    {
                        min_dx=dx.get(a,0);
                    }
                }
            }
        }
        return p;
    }
    
    public static void main(String[] args)
    {
        ComplexFunction f = new ComplexFunction()
        {
            public Complex run(Complex... x)
            {
                return Complex.sub(Complex.pow(x[0],new Complex(4)),new Complex(1));
            }
        };
        Complex[] p = new Complex[]{new Complex(0)};
        System.out.println("Newton");
        System.out.println(Newton(f,p,0));
        System.out.println("Aberth");
        Complex[] a=Aberth(f,p,0,4);
        for (int i=0;i<a.length;i++)
        {
            System.out.println(a[i]);
        }
        System.out.println();
        
        ComplexFunction f1 = new ComplexFunction()
        {
            public Complex run(Complex... x)
            {
                return Complex.sub(Complex.add(x[0],Complex.mul(new Complex(2),x[1])),new Complex(2));
                //return Complex.sub(x[0],x[1]);
            }
        };
        ComplexFunction f2 = new ComplexFunction()
        {
            public Complex run(Complex... x)
            {
                return Complex.sub(Complex.add(Complex.pow(x[0],new Complex(2)),Complex.mul(new Complex(4),Complex.pow(x[1],new Complex(2)))),new Complex(4));
                //return Complex.add(x[0],x[1]);
            }
        };
        ComplexFunction[] cf=new ComplexFunction[]{f1,f2};
        p = new Complex[]{new Complex(-2),new Complex(-2)};
        int[] ind=new int[]{0,1};
        System.out.println("Newton");
        Complex[] n2=Newton(cf,p,ind);
        for (int i=0;i<n2.length;i++)
        {
            System.out.println(n2[i]);
        }
        /*System.out.println("Aberth");
        Complex[][] a2=Aberth(cf,p,ind,2);
        for (int i=0;i<a2.length;i++)
        {
            System.out.println(i);
            for (int j=0;j<a2[i].length;j++)
            {
                System.out.println(a2[i][j]);
            }
        }*/
    }
}
