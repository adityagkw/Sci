package aditya.sci.comp;
public class List<T>
{
    class ListNode<t>
    {
        t obj;
        ListNode<t> next;
        ListNode(t obj)
        {
            this.obj=obj;
            next=null;
        }
    }
    private ListNode<T> begin;
    public List()
    {
        begin=null;
    }
    public void add(T obj)
    {
        ListNode<T> pn=null;
        ListNode<T> cn=begin;
        while (cn!=null)
        {
            pn=cn;
            cn=cn.next;
        }
        cn=new ListNode<T>(obj);
        if (pn!=null)
        {
            pn.next=cn;
        }
        else
        {
            begin=cn;
        }
    }
    public void remove(T obj)
    {
        ListNode<T> pn=null;
        ListNode<T> cn=begin;
        while ((obj==null && cn.obj==null)||!obj.equals(cn.obj))
        {
            pn=cn;
            cn=cn.next;
        }
        if (pn!=null)
        {
            pn.next=cn.next;
        }
        else
        {
            begin=cn.next;
        }
        cn=null;
    }
    public T get(int ind)
    {
        T obj=null;
        ListNode<T> cn=begin;
        try
        {
            for (int i=ind;i>0;cn=cn.next,i--);
            obj=cn.obj;
        }catch(NullPointerException e)
        {
            throw new RuntimeException("Index out of bounds: "+ind);
        }
        return obj;
    }
    public void set(int ind,T obj)
    {
        ListNode<T> cn=begin;
        try
        {
            for (int i=ind;i>0;cn=cn.next,i--);
            cn.obj=obj;
        }catch(Exception e)
        {
            throw new RuntimeException("Index out of bounds: "+ind);
        }
    }
    public int getIndex(T obj)
    {
        int ind=0;
        ListNode<T> cn=begin;
        try
        {
            while((obj==null && cn.obj==null)||!obj.equals(cn.obj))
            {
                cn=cn.next;
                ind++;
            }
        }catch(Exception e)
        {
            return -1;
        }
        return ind;
    }
    public int size()
    {
        int l=0;
        ListNode<T> cn=begin;
        while(cn!=null)
        {
            cn=cn.next;
            l++;
        }
        return l;
    }
    public int length()
    {
        return size();
    }
    public Object[] toArray(Class c)
    {
        int l=size();
        //Class c=(Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        //Class c=(Class)getClass().getGenericSuperclass();
        T[] t=(T[])java.lang.reflect.Array.newInstance(c,l);
        int i=0;
        ListNode<T> cn=begin;
        while(cn!=null)
        {
            t[i]=cn.obj;
            cn=cn.next;
            i++;
        }
        return (T[])t;
    }
    public String toString()
    {
        String str="[";
        ListNode<T> cn=begin;
        while(cn!=null)
        {
            str+=cn.obj.toString()+(cn.next==null?"":",");
            cn=cn.next;
        }
        str+="]";
        return str;
    }
}
