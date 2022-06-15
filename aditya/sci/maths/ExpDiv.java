package aditya.sci.maths;
import java.util.*;
public class ExpDiv extends ExpOperator
{
    public ExpDiv(Expression l,Expression r)
    {
        operands=new Expression[]{l,r};
    }
    public Complex eval(HashMap map)
    {
        return Complex.div(operands[0].eval(map),operands[1].eval(map));
    }
    public String toString()
    {
        return "("+operands[0]+"/"+operands[1]+")";
    }
}
