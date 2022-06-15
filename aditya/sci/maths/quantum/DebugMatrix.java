package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
public class DebugMatrix extends QGate
{
    String name;
    boolean view=false;
    public DebugMatrix(QBit b,String name)
    {
        operand=new QBit[]{b};
        this.name=name;
    }
    public DebugMatrix(QBit b,String name,boolean view)
    {
        operand=new QBit[]{b};
        this.name=name;
        this.view=view;
    }
    public void run()
    {
        QState state=operand[0].state;
        if (operand[0].e!=null)
        {
            state=operand[0].e.state;
        }
        Complex[][] c=state.mat;
        if (!view)
            parent.measurement.put(name,c);
        else
        {
            String measure="";
            for (int i=0;i<c.length;i++)
            {
                measure+="|";
                for (int j=0;j<c[i].length;j++)
                {
                    measure+=(j==0?"":" ")+c[i][j];
                }
                measure+="|"+(i==c.length-1?"":"\n");
            }
            parent.measurement.put(name,measure);
        }
    }
    public String toString(int i)
    {
        return ""+name+"";
    }
}
