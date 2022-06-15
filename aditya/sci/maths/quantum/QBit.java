package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
public class QBit
{
    //public static QBit Q0=QBit.getQBit(new QState(new double[]{1,0}));
    //public static QBit Q1=QBit.getQBit(new QState(new double[]{0,1}));
    
    boolean value=false;
    boolean entangled=false;
    QState state=null;
    QEntangled e=null;
    QCircuit parent=null;
    int index=0;
    public QBit()
    {
        
    }
    public static QBit getQBit(QState state)
    {
        QBit q=new QBit();
        q.state=state;
        return q;
    }
    public boolean isEntangled()
    {
        return entangled;
    }
    public boolean measure()
    {
        if (isEntangled())
        {
            e.measure();
        }
        //if (!isEntangled())
        {
            //System.out.println(state);
            double p0=Math.pow(Complex.abs(state.get(0,0)),2);
            double p1=Math.pow(Complex.abs(state.get(1,0)),2);
            double r=Math.random();
            //System.out.println(p0+" "+p1+" "+r);
            if (r<p0)
            {
                state=new QState(new double[]{1,0});
                return false;
            }
            r=r-p0;
            if (r<p1)
            {
                state=new QState(new double[]{0,1});
                return true;
            }
        }
        return false;
    }
}
