package aditya.sci.maths;
import java.lang.reflect.*;
public class NArray<T>
{
    Object[] t;
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
        t=new Object[to];
    }
    private int getIndex(int[] i)
    {
        int in=0;
        for (int a=0;a<shape.length;a++)
        {
            int mi=1;
            for (int b=a+1;b<shape.length;b++)
            {
                mi*=shape[b];
            }
            in+=mi*i[a];
        }
        //in+=i[i.length-1];
        return in;
    }
    private int[] getIndexArray(int i)
    {
        int[] ind=new int[shape.length];
        int i2=i;
        for (int a=0;a<shape.length;a++)
        {
            int mi=1;
            for (int b=a+1;b<shape.length;b++)
            {
                mi*=shape[b];
            }
            ind[a]=i2/mi;
            i2%=mi;
        }
        for (int a=0;a<shape.length;a++)
        {
            if (ind[a]>=shape[a])
                return null;
        }
        return ind;
    }
    public int[] getNextIndex(int[] i)
    {
        int i2=getIndex(i);
        int[] ind=getIndexArray(i2+1);
        return ind;
    }
    public T get(int... i)
    {
        return (T)t[getIndex(i)];
    }
    public void set(T obj,int... i)
    {
        t[getIndex(i)]=obj;
    }
}
