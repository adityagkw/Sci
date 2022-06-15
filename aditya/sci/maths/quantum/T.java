package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
/**
PI/8
*/
public class T extends QGate
{
    public T(QBit b)
    {
        operand=new QBit[]{b};
        operation=new QState(new Complex[][]{
        {new Complex(1,0),new Complex(0,0)},
        {new Complex(0,0),Complex.exp(new Complex(0,Math.PI/4))}
        });
    }
    public String toString(int i)
    {
        return "T";
    }
}
