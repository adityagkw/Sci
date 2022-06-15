package aditya.sci.physics;
/**

 */
public class Property implements Cloneable,java.io.Serializable
{
    public Object value;
    public Body body;
    public String name;
    public Property()
    {
        name="Property";
    }
    public Object getValue()
    {
        return value;
    }
    public void setValue(Object o)
    {
        value=o;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String n)
    {
        name=n;
    }
    public void update(){}
}
