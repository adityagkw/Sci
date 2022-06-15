package aditya.sci.maths.quantum;
import aditya.sci.maths.*;
public class EditMatrix extends QGate
{
    Complex[][] mat;
    public EditMatrix(QBit b,Complex[][] mat)
    {
        operand=new QBit[]{b};
        this.mat=mat;
    }
    public void run()
    {
        if (operand[0].e!=null)
        {
            operand[0].e.state.mat=mat;
        }
        else
        {
            operand[0].state.mat=mat;
        }
    }
    public String toString(int i)
    {
        return "EditMatrix";
    }
}
