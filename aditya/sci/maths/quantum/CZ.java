package aditya.sci.maths.quantum;
/**
Controlled Z 
*/
public class CZ extends QGate
{
    public CZ(QBit c,QBit b)
    {
        operand=new QBit[]{c,b};
        double d=1/Math.sqrt(2);
        operation=new QState(new double[][]{
        {1,0,0,0},
        {0,1,0,0},
        {0,0,1,0},
        {0,0,0,-1}
        });
    }
    public String toString(int i)
    {
        if (i==1)return "Z";
        return "O";
    }
}
