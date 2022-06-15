package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
/**
Phase(S,P)
*/
public class S extends QGate
{
    public S(QBit b)
    {
        operand=new QBit[]{b};
        operation=new QState(new Complex[][]{
        {new Complex(1,0),new Complex(0,0)},
        {new Complex(0,0),new Complex(0,1)}
        });
    }
    public String toString(int i)
    {
        return "S";
    }
}
