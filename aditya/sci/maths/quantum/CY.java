package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
/**
Controlled Y
 */
public class CY extends QGate
{
    public CY(QBit c,QBit b)
    {
        operand=new QBit[]{c,b};
        operation=new QState(new Complex[][]{
        {new Complex(1,0),new Complex(0,0),new Complex(0,0),new Complex(0, 0)},
        {new Complex(0,0),new Complex(1,0),new Complex(0,0),new Complex(0,-1)},
        {new Complex(0,0),new Complex(0,0),new Complex(0,1),new Complex(0, 0)}
        });
    }
    public String toString(int i)
    {
        if (i==1) return "Y";
        return "O";
    }
}
