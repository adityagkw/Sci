package aditya.sci.maths.quantum;
/**
Pauli-X
*/
public class X extends QGate
{
    public X(QBit b)
    {
        operand=new QBit[]{b};
        operation=new QState(new double[][]{
        {0,1},
        {1,0}
        });
    }
    public String toString(int i)
    {
        return "X";
    }
}
