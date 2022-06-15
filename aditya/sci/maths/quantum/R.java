package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
/**
Phase shift(R phi)
*/
public class R extends QGate
{
    double ang;
    public R(QBit b,double phi)
    {
        phi=((phi+180)%360)-180;
        operand=new QBit[]{b};
        operation=new QState(new Complex[][]{
        {new Complex(1,0),new Complex(0,0)},
        {new Complex(0,0),Complex.exp(new Complex(0,phi))}
        });
        ang=phi;
    }
    public String toString(int i)
    {
        String a=""+ang;
        if (a.length()>4) a=a.substring(0,4);
        return "R("+a+")";
    }
}
