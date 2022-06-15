package aditya.sci.physics;
public class Velocity extends Vector
{
    public Velocity()
    {
        this(new double[3]);
        name="Velocity";
    }
    public Velocity(double... p)
    {
        super(p);
        name="Velocity";
    }
    public Velocity(Vector v)
    {
        super(v);
        name=v.name;
    }
    public void update()
    {
        try
        {
        double[] v=getVectorComponents();
        Position p=(Position)body.getProperty(Position.class);
        if (p==null)
        {
            p=new Position();
            body.addProperty(p);
        }
        double[] pv=p.getVectorComponents();
        for (int i=0;i<v.length;i++)
        {
            pv[i]+=v[i];
        }
        }catch(Exception e){}
    }
}
