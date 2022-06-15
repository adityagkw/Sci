package aditya.sci.physics;
public class Position extends Vector
{
    public Position()
    {
        this(new double[4]);
        name="Position";
    }
    public Position(double... p)
    {
        super(p);
        name="Position";
    }
    public Position(Vector v)
    {
        super(v);
        name=v.name;
    }
    public void update()
    {
        double[] v=getVectorComponents();
        v[3]+=1;
    }
}
