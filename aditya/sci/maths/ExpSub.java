package aditya.sci.maths;
import java.util.*;
public class ExpSub extends ExpOperator
{
    public ExpSub(Expression l,Expression r)
    {
        operands=new Expression[]{l,r};
    }
    public Complex eval(HashMap map)
    {
        return Complex.sub(operands[0].eval(map),operands[1].eval(map));
    }
    public String toString()
    {
        return "("+operands[0]+"-"+operands[1]+")";
    }
}
