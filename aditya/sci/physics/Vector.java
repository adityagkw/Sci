package aditya.sci.physics;

public class Vector extends Tensor
{
    private static class Determinant
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
        public double getValue()
        {
            double d=Math.sqrt(-1);
            //System.out.println(this);
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
        public int getCofactorSign(int m,int n)
        {
            return ((m+n)%2==0?1:-1);
        }
    }
    public Vector()
    {
        super(3,1);
        name="Vector";
    }
    public Vector(int c)
    {
        super(c,1);
        name="Vector";
    }
    public Vector(double[] c)
    {
        super(c.length,1);
        for (int i=0;i<c.length;setComponent(c[i],i),i++);
        name="Vector";
    }
    public Vector(Vector v)
    {
        this(v.getVectorComponents());
        name=v.name;
    }
    public double[] getVectorComponents()
    {
        //double[] c=new double[rank==0?1:dimensions];
        //for (int i=0;i<c.length;c[i]=getComponent(i),i++);
        return (double[])(((NArray)value).t);
    }
    public double getComponent(int i)
    {
        return super.getComponent(i);
    }
    public void setComponent(double v,int i)
    {
        super.setComponent(v,i);
    }
    public Vector getDirection()
    {
        Vector o=new Vector(dimensions);
        double h=getMagnitude();
        for (int i=0;i<dimensions;i++)
        {
            o.setComponent(getComponent(i)/h,i);
        }
        return o;
    }
    public static Vector add(Vector a,Vector b)
    {
        return (Vector)Tensor.add(a,b);
    }
    public static Vector sub(Vector a,Vector b)
    {
        return (Vector)Tensor.sub(a,b);
    }
    public static Vector mul(Vector a,Vector b)
    {
        return (Vector)Tensor.mul(a,b);
    }
    public static Vector div(Vector a,Vector b)
    {
        return (Vector)Tensor.div(a,b);
    }
    public static Vector getDotProduct(Vector a,Vector b)
    {
        double[] ac=a.getVectorComponents();
        double[] bc=b.getVectorComponents();
        Vector c=new Vector(new double[ac.length]);
        double[] vc=c.getVectorComponents();
        for (int i=0;i<vc.length;i++)
        {
            vc[i]=ac[i]*bc[i];
        }
        return c;
    }
    public static Vector getCrossProduct(Vector... v)
    {
        double[][] cs=new double[v.length][v[0].getVectorComponents().length];
        for (int i=0;i<v.length;i++)
        {
            cs[i]=v[i].getVectorComponents();
        }
        Vector c=new Vector(new double[cs[0].length]);
        double[] vc=c.getVectorComponents();
        Determinant dt=new Determinant(cs[0].length);
        for (int i=0;i<vc.length;i++)
        {
            dt.set(0,i,1);
        }
        for (int i=1;i<vc.length;i++)
        {
            for (int j=0;j<vc.length;j++)
            {
                dt.set(i,j,cs[i-1][j]);
            }
        }
        for (int i=0;i<vc.length;i++)
        {
            vc[i]=dt.getMinor(0,i).getValue()*dt.getCofactorSign(0,i);
        }
        return c;
    }
    public double getMagnitude()
    {
        double m=0;
        double[] ca=getVectorComponents();
        for(int i=0;i<ca.length;m+=ca[i]*ca[i],i++);
        m=Math.sqrt(m);
        return m;
    }
    public String toString()
    {
        String op="";
        double[] cs=getVectorComponents();
        op+="(";
        for (int i=0;i<cs.length;i++)
        {
            op+=cs[i]+(i==cs.length-1?"":",\t");
        }
        op+=")";
        return op;
    }
}
