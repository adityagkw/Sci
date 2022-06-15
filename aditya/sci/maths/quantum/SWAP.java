package aditya.sci.maths.quantum;
/**
SWAP 
*/
public class SWAP extends QGate
{
    public SWAP(QBit c,QBit b)
    {
        operand=new QBit[]{c,b};
        double d=1/Math.sqrt(2);
        operation=new QState(new double[][]{
        {1,0,0,0},
        {0,0,1,0},
        {0,1,0,0},
        {0,0,0,1}
        });
    }
    public String toString(int i)
    {
        return "X";
    }
}
