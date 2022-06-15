package aditya.sci.maths;
public class Determinant
{
    private int o;
    private double[][] dt;
    public Determinant(int order)
    {
        dt=new double[order][order];
        o=order;
    }
    public void set(int m,int n,double v)
    {
        dt[m][n]=v;
    }
    public double get(int m,int n)
    {
        return dt[m][n];
    }
    public void setValueAt(int m,int n,double v)
    {
        dt[m-1][n-1]=v;
    }
    public double getValueAt(int m,int n)
    {
        return dt[m-1][n-1];
    }
    public Matrix toMatrix()
    {
        Matrix m=null;
        m=new Matrix(o,o);
        for (int i=0;i<o;i++)
        {
            for (int j=0;j<o;j++)
            {
                m.set(i,j,get(i,j));
            }
        }
        return m;
    }
    public Determinant getMinor(int m,int n)
    {
        if (o>0)
        {
            Determinant d=new Determinant(o-1);
            int i=0,j=0;
            for (int m2=0;m2<o;m2++)
            {
                j=0;
                if (m2!=m)
                {
                    for (int n2=0;n2<o;n2++)
                    {
                        if (n2!=n)
                        {
                            d.set(i,j,get(m2,n2));
                            j++;
                        }
                    }
                    i++;
                }
            }
            return d;
        }
        return null;
    }
    public Determinant getMinorAt(int m,int n)
    {
        if (o>0)
        {
            Determinant d=new Determinant(o-1);
            int i=0,j=0;
            for (int m2=0;m2<o;m2++)
            {
                j=0;
                if (m2!=m-1)
                {
                    for (int n2=0;n2<o;n2++)
                    {
                        if (n2!=n-1)
                        {
                            d.set(i,j,get(m2,n2));
                            j++;
                        }
                    }
                    i++;
                }
            }
            return d;
        }
        return null;
    }
    public double getValue()
    {
        double d=Math.sqrt(-1);
        if (o==1)
        {
            d= dt[0][0];
        }
        else if (o>1)
        {
            d=0;
            for (int i=0;i<o;i++)
            {
                double d2=getMinor(0,i).getValue()*get(0,i);
                d+=d2*getCofactorSign(0,i);
            }
        }
        return d;
    }
    public static int getCofactorSign(int m,int n)
    {
        return ((m+n)%2==0?1:-1);
    }
    public static int getCofactorSignAt(int m,int n)
    {
        return ((m+n)%2==0?1:-1);
    }
    public String toString()
    {
        String s="";
        for (int i=0;i<o;i++)
        {
            if (i!=0){s+="\n";}
            s+="│\t";
            for (int j=0;j<o;j++)
            {
                if (j!=0){s+="\t";}
                s+=get(i,j);
            }
            
            s+="\t│";
        }
        return s;
    }
}
