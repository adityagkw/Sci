package aditya.sci.maths;
public class Matrix
{
    public double[][] mat;
    public int m,n;
    public Matrix(int m,int n)
    {
        mat=new double[m][n];
        this.m=m;
        this.n=n;
    }
    public Matrix(double[][] mat)
    {
        this.mat=mat;
        m=mat.length;
        n=mat[0].length;
    }
    public void set(int m,int n,double v)
    {
        mat[m][n]=v;
    }
    public double get(int m,int n)
    {
        return mat[m][n];
    }
    public void setValueAt(int m,int n,double v)
    {
        mat[m-1][n-1]=v;
    }
    public double getValueAt(int m,int n)
    {
        return mat[m-1][n-1];
    }
    public Determinant toDeterminant()
    {
        Determinant d=null;
        if (m==n)
        {
            d=new Determinant(m);
            for (int i=0;i<m;i++)
            {
                for (int j=0;j<m;j++)
                {
                    d.set(i,j,get(i,j));
                }
            }
        }
        return d;
    }
    public double[][] get2DDoubleArray()
    {
        return mat;
    }
    public static Matrix add(Matrix a,Matrix b)
    {
        if (a.m==b.m && a.n==b.n)
        {
            Matrix m=new Matrix(a.m,a.n);
            for (int i=0;i<a.m;i++)
            {
                for (int j=0;j<a.n;j++)
                {
                    m.set(i,j,a.get(i,j)+b.get(i,j));
                }
            }
            return m;
        }
        return null;
    }
    public static Matrix sub(Matrix a,Matrix b)
    {
        if (a.m==b.m && a.n==b.n)
        {
            Matrix m=new Matrix(a.m,a.n);
            for (int i=0;i<a.m;i++)
            {
                for (int j=0;j<a.n;j++)
                {
                    m.set(i,j,a.get(i,j)-b.get(i,j));
                }
            }
            return m;
        }
        return null;
    }
    public static Matrix mul(double a,Matrix b)
    {
        Matrix m=new Matrix(b.m,b.n);
        for (int i=0;i<m.m;i++)
        {
            for (int j=0;j<m.n;j++)
            {
                m.set(i,j,a*b.get(i,j));
            }
        }
        return m;
    }
    public static Matrix mul(Matrix a,Matrix b)
    {
        if (a.n==b.m)
        {
            Matrix m=new Matrix(a.m,b.n);
            for (int i=0;i<m.m;i++)
            {
                for (int j=0;j<m.n;j++)
                {
                    double s=0;
                    for (int k=0;k<a.n;k++)
                    {
                        s+=a.get(i,k)*b.get(k,j);
                    }
                    m.set(i,j,s);
                }
            }
            return m;
            }
        return null;
    }
    public static Matrix transpose(Matrix a)
    {
        Matrix m=new Matrix(a.n,a.m);
        for (int i=0;i<a.n;i++)
        {
            for (int j=0;j<a.m;j++)
            {
                m.set(i,j,a.get(j,i));
            }
        }
        return m;
    }
    public static Matrix identity(int o)
    {
        Matrix m=new Matrix(o,o);
        for (int i=0;i<m.m;i++)
        {
            m.set(i,i,1);
        }
        return m;
    }
    public Matrix getAdjoint()
    {
        Matrix mt=null;
        if (m==n && m>1)
        {
            Determinant d=toDeterminant();
            mt=new Matrix(m,m);
            for (int i=0;i<m;i++)
            {
                for (int j=0;j<m;j++)
                {
                    mt.set(i,j,d.getMinor(i,j).getValue()*d.getCofactorSign(i,j));
                }
            }
            mt=Matrix.transpose(mt);
        }
        return mt;
    }
    public Matrix inverse()
    {
        Matrix mt=null;
        if (m==n)
        {
            double d=toDeterminant().getValue();
            mt=Matrix.mul(1/d,getAdjoint());
        }
        return mt;
    }
    public String toString()
    {
        String s="";
        for (int i=0;i<m;i++)
        {
            if (i!=0){s+="\n";}
            if (i==0){s+="???\t";}
            else if (i==m-1){s+="???\t";}
            else {s+="???\t";}
            for (int j=0;j<n;j++)
            {
                if (j!=0){s+="\t";}
                s+=get(i,j);
            }
            if (i==0){s+="\t???";}
            else if (i==m-1){s+="\t???";}
            else {s+="\t???";}
        }
        return s;
    }
}
