package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
/**
Pauli-Y
*/
public class Y extends QGate
{
    public Y(QBit b)
    {
        operand=new QBit[]{b};
        operation=new QState(new Complex[][]{
        {new Complex(0,0),new Complex(0,-1)},
        {new Complex(0,1),new Complex(0,0)}
        });
    }
    public String toString(int i)
    {
        return "Y";
    }
}
