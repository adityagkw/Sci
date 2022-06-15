package aditya.sci.maths;
import java.util.*;
public class ExpAdd extends ExpOperator
{
    public ExpAdd(Expression... exp)
    {
        operands=exp;
    }
    public Complex eval(HashMap map)
    {
        Complex ans=new Complex(0,0);
        for (int i=0;i<operands.length;i++)
        {
            ans=Complex.add(ans,operands[i].eval(map));
        }
        return ans;
    }
    public String toString()
    {
        String str="(";
        for (int i=0;i<operands.length;i++)
        {
            if (i!=0)
            {
                str+="+";
            }
            str+=""+operands[i];
        }
        str+=")";
        return str;
    }
}
