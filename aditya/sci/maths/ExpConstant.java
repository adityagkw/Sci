package aditya.sci.maths;
import java.util.*;
public class ExpConstant extends Expression
{
    private Complex val;
    public ExpConstant(Complex v)
    {
        val=v;
    }
    public ExpConstant(double v)
    {
        val=new Complex(v,0);
    }
    public Complex eval(HashMap map)
    {
        return val;
    }
    public String toString()
    {
        return ""+val;
    }
}
