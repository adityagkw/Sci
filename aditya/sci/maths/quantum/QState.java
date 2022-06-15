package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
public class QState implements Cloneable
{
    Complex[][] mat;
    int m,n;
    
    public QState(int m,int n)
    {
        this.m=m;
        this.n=n;
        mat=new Complex[m][n];
        for (int i=0;i<m;i++)
        {
            for (int j=0;j<n;j++)
            {
                mat[i][j]=new Complex(0,0);
            }
        }
    }
    public QState(Complex[][] s)
    {
        mat=s;
        m=s.length;
        n=s[0].length;
    }
    public QState(double[][] s)
    {
        Complex[][] c=new Complex[s.length][s[0].length];
        for (int i=0;i<c.length;i++)
        {
            for (int j=0;j<c[0].length;j++)
            {
                c[i][j]=new Complex(s[i][j]);
            }
        }
        mat=c;
        m=c.length;
        n=c[0].length;
    }
    public QState(Complex[] s)
    {
        mat=new Complex[s.length][1];
        for (int i=0;i<mat.length;i++)
        {
            mat[i][0]=s[i];
        }
        m=s.length;
        n=1;
    }
    public QState(double[] s)
    {
        Complex[][] c=new Complex[s.length][1];
        for (int i=0;i<c.length;i++)
        {
            c[i][0]=new Complex(s[i]);
        }
        mat=c;
        m=c.length;
        n=1;
    }
    public Complex get(int i,int j)
    {
        return mat[i][j];
    }
    public void set(int i,int j,Complex c)
    {
        mat[i][j]=c;
    }
    public QState clone()
    {
        QState q=new QState(m,n);
        for (int i=0;i<m;i++)
        {
            for (int j=0;j<n;j++)
            {
                q.set(i,j,get(i,j).clone());
            }
        }
        return q;
    }
    
    public static QState mul(QState a,QState b)
    {
        if (a.n==b.m)
        {
            QState m=new QState(a.m,b.n);
            for (int i=0;i<m.m;i++)
            {
                for (int j=0;j<m.n;j++)
                {
                    Complex s=new Complex(0,0);
                    for (int k=0;k<a.n;k++)
                    {
                        s=Complex.add(s,Complex.mul(a.get(i,k),b.get(k,j)));
                    }
                    m.set(i,j,s);
                }
            }
            return m;
            }
        return null;
    }
    public static QState tensorProd(QState a,QState b)
    {
        QState m=new QState(a.m*b.m,a.n*b.n);
        for (int i=0;i<a.m;i++)
        {
            for (int k=0;k<b.m;k++)
            {
                for (int j=0;j<a.n;j++)
                {
                    for (int l=0;l<b.n;l++)
                    {
                        m.set(i*b.m+k,j*b.n+l,Complex.mul(a.get(i,j),b.get(k,l)));
                    }
                }
            }
        }
        return m;
    }
    
    public boolean isValid()
    {
        for (int j=0;j<n;j++)
        {
            double p=-1;
            for (int i=0;i<m;i++)
            {
                p+=Math.pow(Complex.abs(get(i,j)),2);
            }
            if (p>0.01 || p<-0.01)
                return false;
        }
        return true;
    }
    public QState[] factorize()
    {
        Complex f0=get(0,0);
        for (int i=1;i<m/2;i++)
        {
            f0=Maths.gcd(f0,get(i,0));
        }
        Complex f1=get(m/2,0);
        for (int i=1;i<m/2;i++)
        {
            f1=Maths.gcd(f1,get(m/2+i,0));
        }
        Complex c=Complex.hypot(f0,f1);
        f0=Complex.div(f0,c);
        f1=Complex.div(f1,c);
        QState s=new QState(new Complex[]{f0,f1});
        QState s2=new QState(m/2,1);
        for (int i=0;i<m/2;i++)
        {
            Complex i1=get(i,0);
            Complex i2=get(i+m/2,0);
            if (!Complex.equals(Complex.div(i1,f0),Complex.div(i2,f1)))
            {
                if ( !(Complex.equals(f0,new Complex(0)) && Complex.equals(i1,new Complex(0))) && !(Complex.equals(f1,new Complex(0)) && Complex.equals(i2,new Complex(0))))
                    return null;
            }
            if (!f0.equals(f0,new Complex(0)))
                s2.set(i,0,Complex.div(i1,f0));
            else
                s2.set(i,0,Complex.div(i2,f1));
        }
        if (s.isValid() && s2.isValid())
        {    
            //System.out.println(s);
            //System.out.println(s2);
            return new QState[]{s,s2};
        }
        return null;
    }
    
    public String toString()
    {
        String s="";
        for (int i=0;i<m;i++)
        {
            if (i!=0){s+="\n";}
            if (i==0){s+="┌\t";}
            else if (i==m-1){s+="└\t";}
            else {s+="│\t";}
            for (int j=0;j<n;j++)
            {
                if (j!=0){s+="\t";}
                s+=get(i,j);
            }
            if (i==0){s+="\t┐";}
            else if (i==m-1){s+="\t┘";}
            else {s+="\t│";}
        }
        return s;
    }
    public static void main(String[] args)
    {
        double d=1/Math.sqrt(2);
        /*QState[] ss=new QState[]{new QState(new double[]{d,d}),new QState(new double[]{d,d}),new QState(new double[]{d,d})};
        QState s=ss[0].clone();
        for (int i=1;i<ss.length;i++)
        {
            s=QState.tensorProd(s,ss[i]);
        }*/
        QState s=new QState(new double[]{1,0,0,0});
        System.out.println(s);
        QState[] a=s.factorize();
        System.out.println(a!=null?a[0]+"\n"+a[1]:null);
    }
}
