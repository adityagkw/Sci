package aditya.sci.maths.quantum;
/**
Hadamard 
*/
public class H extends QGate
{
    public H(QBit b)
    {
        operand=new QBit[]{b};
        double d=1/Math.sqrt(2);
        operation=new QState(new double[][]{
        {d,d},
        {d,-d}
        });
    }
    public String toString(int i)
    {
        return "H";
    }
}
