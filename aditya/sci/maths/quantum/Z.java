package aditya.sci.maths.quantum;
/**
Pauli-z
*/
public class Z extends QGate
{
    public Z(QBit b)
    {
        operand=new QBit[]{b};
        operation=new QState(new double[][]{
        {1, 0},
        {0,-1}
        });
    }
    public String toString(int i)
    {
        return "Z";
    }
}
