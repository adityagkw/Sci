package aditya.sci.maths.quantum;
/**
Toffoli (CCNOT,CCX,TOFF) 
*/
public class CCNOT extends QGate
{
    public CCNOT(QBit c1,QBit c2,QBit b)
    {
        operand=new QBit[]{c1,c2,b};
        double d=1/Math.sqrt(2);
        operation=new QState(new double[][]{
        {1,0,0,0,0,0,0,0},
        {0,1,0,0,0,0,0,0},
        {0,0,1,0,0,0,0,0},
        {0,0,0,1,0,0,0,0},
        {0,0,0,0,1,0,0,0},
        {0,0,0,0,0,1,0,0},
        {0,0,0,0,0,0,0,1},
        {0,0,0,0,0,0,1,0}
        });
    }
    public String toString(int i)
    {
        if (i==2)return "+";
        return "O";
    }
}
