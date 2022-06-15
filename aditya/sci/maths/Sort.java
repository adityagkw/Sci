package aditya.sci.maths;
import java.util.*;
import java.lang.reflect.*;
public class Sort<T extends Comparable>
{
    public T[] linear(T[] in)
    {
        T[] out=in.clone();
        for (int i=0;i<out.length;i++)
        {
            for (int j=i;j<out.length;j++)
            {
                if (out[i].compareTo(out[j])>0)
                {
                    T t=out[i];
                    out[i]=out[j];
                    out[j]=t;
                }
            }
        }
        return out;
    }
    public T[] selection(T[] in)
    {
        T[] out=in.clone();
        for (int i=0;i<out.length;i++)
        {
            int ind=i;
            for (int j=i;j<out.length;j++)
            {
                if (out[i].compareTo(out[j])>0)
                {
                    ind=j;
                }
            }
            T t=out[i];
            out[i]=out[ind];
            out[ind]=t;
        }
        return out;
    }
    public T[] bubble(T[] in)
    {
        T[] out=in.clone();
        for (int i=out.length-1;i>0;i--)
        {
            for (int j=0;j<i;j++)
            {
                if (out[j].compareTo(out[j+1])>0)
                {
                    T t=out[j];
                    out[j]=out[j+1];
                    out[j+1]=t;
                }
            }
        }
        return out;
    }
    public T[] quick(T[] in)
    {
        if (in.length<=1)
        {
            return in;
        }
        T[] out=(T[])Array.newInstance(in[0].getClass(),in.length);
        T[] ul=(T[])Array.newInstance(in[0].getClass(),in.length);
        T[] ug=(T[])Array.newInstance(in[0].getClass(),in.length);
        T c=in[in.length/2];
        int ci=0;
        for (int i=0;i<out.length;i++)
        {
            if (in.length/2!=i && c.compareTo(in[i])>=0)
            {
                ul[ci]=in[i];
                ci++;
            }
        }
        T[] l=(T[])Array.newInstance(in[0].getClass(),ci);
        for (int i=0;i<l.length;l[i]=ul[i],i++);
        ci=0;
        for (int i=0;i<in.length;i++)
        {
            if (c.compareTo(in[i])<0)
            {
                ug[ci]=in[i];
                ci++;
            }
        }
        T[] g=(T[])Array.newInstance(in[0].getClass(),ci);
        for (int i=0;i<g.length;g[i]=ug[i],i++);
        T[] sl=quick(l);
        T[] sg=quick(g);
        for (int i=0;i<sl.length;out[i]=sl[i],i++);
        out[sl.length]=c;
        for (int i=0;i<sg.length;out[i+sl.length+1]=sg[i],i++);
        return out;
    }
    public static void main(String[] args)
    {
        Integer[] i=new Integer[10000];
        for (int a=0;a<i.length;a++)
        {
            i[a]=(int)(100*Math.random());
        }
        Sort s=new Sort<Integer>();
        Integer[] c=(Integer[])s.quick(i);
        for (Integer a:c)
        {
            System.out.println(a);
        }
    }
}
