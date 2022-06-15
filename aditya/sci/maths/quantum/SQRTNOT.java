package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
/**
Square root of NOT 
*/
public class SQRTNOT extends QGate
{
    public SQRTNOT(QBit b)
    {
        operand=new QBit[]{b};
        double d=1/Math.sqrt(2);
        operation=new QState(new Complex[][]{
        {new Complex(d, d),new Complex(d,-d)},
        {new Complex(d,-d),new Complex(d, d)}
        });
    }
    public String toString(int i)
    {
        return "X(1/2)";
    }
}
