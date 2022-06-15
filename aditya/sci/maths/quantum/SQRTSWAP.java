package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
/**
 Square root of SWAP
*/
public class SQRTSWAP extends QGate
{
    public SQRTSWAP(QBit c,QBit b)
    {
        operand=new QBit[]{c,b};
        double d=1/Math.sqrt(2);
        operation=new QState(new Complex[][]{
        {new Complex(1),new Complex(0, 0),new Complex(0, 0),new Complex(0)},
        {new Complex(0),new Complex(d, d),new Complex(d,-d),new Complex(0)},
        {new Complex(0),new Complex(d,-d),new Complex(d, d),new Complex(0)},
        {new Complex(0),new Complex(0, 0),new Complex(0, 0),new Complex(1)}
        });
    }
    public String toString(int i)
    {
        return "X(1/2)";
    }
}
