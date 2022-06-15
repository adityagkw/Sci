package aditya.sci.maths.quantum;
public class QGate
{
    protected QState operation;
    protected QBit[] operand;
    protected QCircuit parent;
    public void run()
    {
        if (operand.length==1 && !operand[0].isEntangled())
        {
            //if (!operand[0].isEntangled())
            {
                operand[0].state=QState.mul(operation,operand[0].state);
                //System.out.println(operand[0].state);
            }
        }
        else
        {
            QEntangled e=null;
            for (int ei=0;ei<operand.length;ei++)
            {
                if (operand[ei].isEntangled())
                {
                    e=operand[ei].e;
                    break;
                }
            }
            if (e==null)
            {
                e=QEntangled.createEntangled(operand[0]);
                for (int i=0;i<operand.length;i++)
                {
                    e.entangle(operand[i]);
                }
                QState s=operation.clone();
                //System.out.println(e.state);
                e.state=QState.mul(s,e.state);
                //System.out.println(e.state);
            }
            else
            {
                for (int i=0;i<operand.length;i++)
                {
                    e.entangle(operand[i]);
                    for (int j=0;j<e.index.length;j++)
                    {
                        if (e.index[j]==operand[i].index)
                        {
                            e.swap(i,j);
                            break;
                        }
                    }
                }
                QState s=operation.clone();
                for (int i=0;i<e.index.length-operand.length;i++)
                {
                    s=QState.tensorProd(s,new QState(new double[][]{{1,0},{0,1}}));
                }
                e.state=QState.mul(s,e.state);
            }
            e.factorize();
        }
    }
    public String toString(int i)
    {
        return "?";
    }
}
