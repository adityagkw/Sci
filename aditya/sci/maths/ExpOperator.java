package aditya.sci.maths;
import java.util.*;
public abstract class ExpOperator extends Expression
{
    Expression[] operands;
    public abstract Complex eval(HashMap map);
}
