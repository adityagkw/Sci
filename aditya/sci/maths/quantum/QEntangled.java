package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
public class QEntangled
{
    QState state;
    int[] index;
    QCircuit parent;
    public static QEntangled createEntangled(QBit q)
    {
        QEntangled e=new QEntangled();
        if (q.e==null)
        {
            q.e=e;
            q.entangled=true;
            e.state=q.state;
            e.index=new int[1];
            e.index[0]=q.index;
            e.parent=q.parent;
        }
        else
        {
            e=q.e;
        }
        return e;
    }
    public void entangle(QBit q)
    {
        if (q.isEntangled())
        {
            if (q.e!=this)
            {
                int il=q.e.index.length;
                int[] index2=new int[index.length+il];
                for (int i=0;i<index.length;i++)
                {
                    index2[i]=index[i];
                }
                for (int i=0;i<il;i++)
                {
                    index2[i+index.length]=q.e.index[i];
                }
                state=QState.tensorProd(state,q.e.state);
                boolean f=false;
                for (int i=0;i<il;i++)
                {
                    if (index2[index.length+i]!=q.index)
                        parent.getQBit(index2[index.length+i]).e=this;
                    else
                        f=true;
                }
                if (f)
                {
                    parent.getQBit(q.index).e=this;
                }
                index=index2;
            }
        }
        else
        {
            int[] index2=new int[index.length+1];
            index2[index.length]=q.index;
            for (int i=0;i<index.length;i++)
            {
                index2[i]=index[i];
            }
            q.entangled=true;
            q.e=this;
            state=QState.tensorProd(state,q.state);
            index=index2;
        }
    }
    public void swap(int i,int j)
    {
        int t=index[i];
        index[i]=index[j];
        index[j]=t;
        
        QState s=new QState(state.m,1);
        int sl=index.length-1;
        //System.out.println(sl);
        for (int a=0;a<s.m;a++)
        {
            int bi=(a>>(sl-i))%2;
            int bj=(a>>(sl-j))%2;
            int b=a& ~( (1<<(sl-i)) | (1<<(sl-j)) );
            int c=b|(bi<<(sl-j))|(bj<<(sl-i));
            //System.out.println(a+" "+c);
            s.set(c,0,state.get(a,0));
        }
        state=s;
    }
    public void factorize()
    {
        int il=index.length;
        for (int i=0;i<il;i++)
        {
            swap(0,i);
            QState[] s=state.factorize();
            //System.out.println(state);
            //System.out.println(s[0]);
            //System.out.println(s[1]);
            if (s!=null)
            {
                QBit q=parent.getQBit(index[0]);
                q.e=null;
                q.state=s[0];
                q.entangled=false;
                int[] ind=new int[il-1];
                for (int j=0;j<ind.length;j++)
                {
                    ind[j]=index[j+1];
                }
                index=ind;
                state=s[1];
                il--;
                i--;
                if (il<=1)
                {
                    il=0;
                    QBit q2=parent.getQBit(index[0]);
                    q2.e=null;
                    q2.state=state;
                    q2.entangled=false;
                    index=null;
                }
            }
        }
        for (int i=il-1;i>0;i--)
        {
            swap(0,i);
        }
    }
    public void measure()
    {
        double r=Math.random();
        int i;
        for (i=0;i<state.m;i++)
        {
            double p=Math.pow(Complex.abs(state.get(i,0)),2);
            if (r<p)
            {
                break;
            }
            r-=p;
        }
        for (int j=0;j<index.length;j++)
        {
            int b=(i>>(index.length-1-j))%2;
            if (b==1)
            {
                parent.getQBit(index[j]).state=new QState(new double[]{0,1});
                parent.getQBit(index[j]).entangled=false;
            }
            else
            {
                parent.getQBit(index[j]).state=new QState(new double[]{1,0});
                parent.getQBit(index[j]).entangled=false;
            }
        }
    }
    public static void main(String[] args)
    {
        QEntangled q=new QEntangled();
        q.state=new QState(new double[]{0,1,2,3,4,5,6,7});
        q.index=new int[3];
        System.out.println(q.state);
        q.swap(0,1);
        System.out.println(q.state);
    }
}
