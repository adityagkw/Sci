package aditya.sci.maths;
public class ComplexMatrix
{
    public Complex[][] mat;
    public int m,n;
    public ComplexMatrix(Complex[][] mat)
    {
        this.mat=mat;
        m=mat.length;
        n=mat[0].length;
    }
    public ComplexMatrix(int i,int j)
    {
        mat=new Complex[i][j];
        m=i;
        n=j;
        for (int a=0;a<i;a++)
        {
            for (int b=0;b<j;mat[a][b]=new Complex(0),b++);
        }
    }
    public void set(int i,int j,Complex value)
    {
        mat[i][j]=value;
    }
    public Complex get(int i,int j)
    {
        return mat[i][j];
    }
    
    public static ComplexMatrix add(ComplexMatrix a,ComplexMatrix b)
    {
        if (a.m==b.m && a.n==b.n)
        {
            ComplexMatrix c = new ComplexMatrix(a.m,a.n);
            for (int i=0;i<c.m;i++)
            {
                for (int j=0;j<c.n;j++)
                {
                    c.set(i,j,Complex.add(a.get(i,j),b.get(i,j)));
                }
            }
            return c;
        }
        return null;
    }
    public static ComplexMatrix sub(ComplexMatrix a,ComplexMatrix b)
    {
        if (a.m==b.m && a.n==b.n)
        {
            ComplexMatrix c = new ComplexMatrix(a.m,a.n);
            for (int i=0;i<c.m;i++)
            {
                for (int j=0;j<c.n;j++)
                {
                    c.set(i,j,Complex.sub(a.get(i,j),b.get(i,j)));
                }
            }
            return c;
        }
        return null;
    }
    public static ComplexMatrix mul(ComplexMatrix a,ComplexMatrix b)
    {
        if (a.n==b.m)
        {
            ComplexMatrix c = new ComplexMatrix(a.m,b.n);
            for (int i=0;i<c.m;i++)
            {
                for (int j=0;j<c.n;j++)
                {
                    Complex sum=new Complex(0);
                    for (int k=0;k<a.n;k++)
                    {
                        sum = Complex.add(sum,Complex.mul(a.get(i,k),b.get(k,j)));
                    }
                    c.set(i,j,sum);
                }
            }
            return c;
        }
        return null;
    }
    public static ComplexMatrix mul(Complex a,ComplexMatrix b)
    {
        ComplexMatrix cm = new ComplexMatrix(b.m,b.n);
        for (int i=0;i<b.m;i++)
        {
            for (int j=0;j<b.n;cm.set(i,j,Complex.mul(a,b.get(i,j))),j++);
        }
        return cm;
    }
    public static ComplexMatrix transpose(ComplexMatrix m)
    {
        ComplexMatrix cm = new ComplexMatrix(m.n,m.m);
        for (int i=0;i<m.m;i++)
        {
            for(int j=0;j<m.n;cm.set(j,i,m.get(i,j)),j++);
        }
        return cm;
    }
    public static ComplexMatrix getAdjoint(ComplexMatrix m)
    {
        if (m.m == m.n && m.m>1)
        {
            ComplexMatrix cm = new ComplexMatrix(m.m,m.n);
            for (int i=0;i<m.m;i++)
            {
                for(int j=0;j<m.n;j++)
                {
                    Complex v = m.getMinor(i,j).determinantValue();
                    if (i+j % 2 == 1)
                    {
                        v = Complex.mul(v,Complex.MINUS_ONE);
                    }
                    cm.set(j,i,v);
                }
            }
            return cm;
        }
        if (m.m==m.n && m.m==1)
        {
            return ComplexMatrix.identity(1);
        }
        return null;
    }
    public static ComplexMatrix inverse(ComplexMatrix m)
    {
        Complex d = m.determinantValue();
        //System.out.println(d);
        if (Complex.abs(d)>0.000001)
        {
            ComplexMatrix a = ComplexMatrix.getAdjoint(m);
            //System.out.println(a);
            return ComplexMatrix.mul(Complex.div(Complex.ONE,d),a);
        }
        return null;
    }
    public static ComplexMatrix rowMatrix(Complex[] c)
    {
        ComplexMatrix m=new ComplexMatrix(c.length,1);
        for (int i=0;i<c.length;m.set(i,0,c[i]),i++);
        return m;
    }
    public static ComplexMatrix columnMatrix(Complex[] c)
    {
        ComplexMatrix m=new ComplexMatrix(1,c.length);
        for (int i=0;i<c.length;m.set(0,i,c[i]),i++);
        return m;
    }
    
    public ComplexMatrix getMinor(int i,int j)
    {
        if (m==n)
        {
            int ind1=0,ind2=0;
            ComplexMatrix cm = new ComplexMatrix(m-1,n-1);
            for (int a=0;a<m;a++)
            {
                if (a!=i)
                {
                    ind2=0;
                    for (int b=0;b<n;b++)
                    {
                        if (b!=j)
                        {
                            cm.set(ind1,ind2,get(a,b));
                            ind2++;
                        }
                    }
                    ind1++;
                }
            }
            return cm;
        }
        return null;
    }
    public Complex determinantValue()
    {
        if (m==1 && n==1)
        {
            return get(0,0);
        }
        if (m==n)
        {
            ComplexMatrix dm;
            Complex sum = new Complex(0);
            for (int i=0;i<n;i++)
            {
                dm=getMinor(0,i);
                Complex d = dm.determinantValue();
                if (i%2==1)
                {
                    d = Complex.mul(d,new Complex(-1));
                }
                d = d.mul(d,get(0,i));
                sum = Complex.add(sum,d);
            }
            return sum;
        }
        return null;
    }
    public static ComplexMatrix identity(int n)
    {
        ComplexMatrix m = new ComplexMatrix(n,n);
        for (int i=0;i<n;m.set(i,i,Complex.ONE),i++);
        return m;
    }
    public static ComplexMatrix fill(int m,int n,Complex c)
    {
        ComplexMatrix cm = new ComplexMatrix(m,n);
        for (int i=0;i<m;i++)
        {
            for (int j=0;j<n;cm.set(i,j,c),j++);
        }
        return cm;
    }
    public String toString()
    {
        String str="";
        for (int i=0;i<m;i++)
        {
            str+="|";
            for (int j=0;j<n;j++)
            {
                str+=get(i,j)+" ";
            }
            str+="|\n";
        }
        return str;
    }
    
    public static void main(String[] args)
    {
        ComplexMatrix m = ComplexMatrix.identity(3);
        System.out.println(m);
        System.out.println(m.determinantValue());
    }
}
