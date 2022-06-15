package aditya.sci.maths.quantum;
/**
Controlled Not (CNOT,CX) 
*/
public class CNOT extends QGate
{
    public CNOT(QBit c,QBit b)
    {
        operand=new QBit[]{c,b};
        double d=1/Math.sqrt(2);
        operation=new QState(new double[][]{
        {1,0,0,0},
        {0,1,0,0},
        {0,0,0,1},
        {0,0,1,0}
        });
    }
    public String toString(int i)
    {
        if (i==1)return "+";
        return "O";
    }
}
