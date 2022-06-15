package aditya.sci.physics;
public class Tensor extends Property
{
    public class NArray
    {
        double[] t;
        int[] shape;
        public NArray(int... shape)
        {
            int to=1;
            this.shape=shape;
            for (int i:shape)
            {
                to*=i;
            }
            //@SuppressWarnings("unchecked")
            t=new double[to];
        }
        private int getIndex(int[] i)
        {
            int in=0;
            for (int a=0;a<shape.length-1;a++)
            {
                int mi=1;
                for (int b=a+1;b<shape.length;b++)
                {
                    mi*=shape[b];
                }
                in+=mi*i[a];
            }
            in+=i[i.length-1];
            return in;
        }
        public double get(int... i)
        {
            return t[getIndex(i)];
        }
        public void set(double obj,int... i)
        {
            t[getIndex(i)]=obj;
        }
    }
    
    public final int dimensions;
    public final int rank;
    
    public Tensor()
    {
        value=new NArray(3);
        dimensions=3;
        rank=1;
        name="Tensor";
    }
    public Tensor(int dim,int r)
    {
        int[] shape=new int[r];
        for (int i=0;i<shape.length;shape[i]=dim,i++);
        if (r==0)
        {
            value=new NArray(1);
            dim=1;
        }
        else
        {
            value=new NArray(shape);
        }
        dimensions=dim;
        rank=r;
        name="Tensor";
    }
    public Tensor(Tensor t)
    {
        value=t.value;
        dimensions=t.dimensions;
        rank=t.rank;
        name=t.name;
    }
    public double getComponent(int... i)
    {
        return ((NArray)value).get(i);
    }
    public void setComponent(double v,int... i)
    {
        ((NArray)value).set(v,i);
    }
    public static Tensor add(Tensor a,Tensor b)
    {
        Tensor c=null;
        if (a.rank==0 && b.rank==0)
        {
            c=new Scaler(a.getComponent(0)+b.getComponent(0));
        }
        else if (a.rank==1 && b.rank==1 && a.dimensions==b.dimensions)
        {
            c=new Vector(a.dimensions);
            for (int i=0;i<a.dimensions;i++)
            {
                c.setComponent(a.getComponent(i)+b.getComponent(i),i);
            }
        }
        else if (a.rank==b.rank && a.dimensions==b.dimensions)
        {
            c=new Tensor(a.dimensions,a.rank);
            int r=a.rank;
            int d=a.dimensions;
            int[] ind=new int[r];
            for (int i=0;i<Math.pow(d,r);i++)
            {
                for (int j=0;j<ind.length;ind[j]=0,j++);
                int i2=i;
                int p=0;
                while (i2!=0)
                {
                    ind[p]=i2%d;
                    p++;
                    i2/=d;
                }
                c.setComponent(a.getComponent(ind)+b.getComponent(ind),ind);
            }
        }
        return c;
    }
    public static Tensor sub(Tensor a,Tensor b)
    {
        Tensor c=null;
        if (a.rank==0 && b.rank==0)
        {
            c=new Scaler(a.getComponent(0)-b.getComponent(0));
        }
        else if (a.rank==1 && b.rank==1 && a.dimensions==b.dimensions)
        {
            c=new Vector(a.dimensions);
            for (int i=0;i<a.dimensions;i++)
            {
                c.setComponent(a.getComponent(i)-b.getComponent(i),i);
            }
        }
        else if (a.rank==b.rank && a.dimensions==b.dimensions)
        {
            c=new Tensor(a.dimensions,a.rank);
            int r=a.rank;
            int d=a.dimensions;
            int[] ind=new int[r];
            for (int i=0;i<Math.pow(d,r);i++)
            {
                for (int j=0;j<ind.length;ind[j]=0,j++);
                int i2=i;
                int p=0;
                while (i2!=0)
                {
                    ind[p]=i2%d;
                    p++;
                    i2/=d;
                }
                c.setComponent(a.getComponent(ind)-b.getComponent(ind),ind);
            }
        }
        return c;
    }
    public static Tensor mul(Tensor a,Tensor b)
    {
        Tensor c=null;
        if (a.rank==0 && b.rank==0)
        {
            c=new Scaler(a.getComponent(0)*b.getComponent(0));
        }
        else if (a.rank==1 && b.rank==1 && a.dimensions==b.dimensions)
        {
            c=new Vector(a.dimensions);
            for (int i=0;i<a.dimensions;i++)
            {
                c.setComponent(a.getComponent(i)*b.getComponent(i),i);
            }
        }
        else if (a.rank==b.rank && a.dimensions==b.dimensions)
        {
            c=new Tensor(a.dimensions,a.rank);
            int r=a.rank;
            int d=a.dimensions;
            int[] ind=new int[r];
            for (int i=0;i<Math.pow(d,r);i++)
            {
                for (int j=0;j<ind.length;ind[j]=0,j++);
                int i2=i;
                int p=0;
                while (i2!=0)
                {
                    ind[p]=i2%d;
                    p++;
                    i2/=d;
                }
                c.setComponent(a.getComponent(ind)*b.getComponent(ind),ind);
            }
        }
        return c;
    }
    public static Tensor div(Tensor a,Tensor b)
    {
        Tensor c=null;
        if (a.rank==0 && b.rank==0)
        {
            c=new Scaler(a.getComponent(0)/b.getComponent(0));
        }
        else if (a.rank==1 && b.rank==1 && a.dimensions==b.dimensions)
        {
            c=new Vector(a.dimensions);
            for (int i=0;i<a.dimensions;i++)
            {
                c.setComponent(a.getComponent(i)/b.getComponent(i),i);
            }
        }
        else if (a.rank==b.rank && a.dimensions==b.dimensions)
        {
            c=new Tensor(a.dimensions,a.rank);
            int r=a.rank;
            int d=a.dimensions;
            int[] ind=new int[r];
            for (int i=0;i<Math.pow(d,r);i++)
            {
                for (int j=0;j<ind.length;ind[j]=0,j++);
                int i2=i;
                int p=0;
                while (i2!=0)
                {
                    ind[p]=i2%d;
                    p++;
                    i2/=d;
                }
                c.setComponent(a.getComponent(ind)/b.getComponent(ind),ind);
            }
        }
        return c;
    }
    public static Tensor tensorProduct(Tensor a,Tensor b)
    {
        Tensor c=null;
        if (a.rank==b.rank)
        {
            int r=a.rank==0?1:a.rank;
            int d=a.rank==0?1:(a.dimensions * b.dimensions);
            int od=a.rank==0?1:b.dimensions;
            c=new Tensor(d,a.rank);
            int[] ind=new int[r];
            int[] indd=new int[r];
            int[] indm=new int[r];
            for (int i=0;i<Math.pow(d,r);i++)
            {
                for (int j=0;j<ind.length;ind[j]=0,j++);
                for (int j=0;j<indd.length;indd[j]=0,j++);
                for (int j=0;j<indm.length;indm[j]=0,j++);
                int i2=i;
                int p=0;
                while(i2!=0)
                {
                    ind[p]=i2%d;
                    indd[p]=(i2%d)/od;
                    indm[p]=(i2%d)%od;
                    p++;
                    i2/=d;
                }
                c.setComponent(a.getComponent(indd)*b.getComponent(indm),ind);
            }
        }
        return c;
    }
}
