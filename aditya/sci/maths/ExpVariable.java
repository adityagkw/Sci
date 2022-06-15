package aditya.sci.maths;
import java.util.*;
public class ExpVariable extends Expression
{
    private String name;
    public ExpVariable(String n)
    {
        name=n;
    }
    public Complex eval(HashMap map)
    {
        return (Complex)map.get(name);
    }
    public String toString()
    {
        return name;
    }
}
