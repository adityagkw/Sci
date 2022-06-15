package aditya.sci.physics;
public class Acceleration extends Vector
{
    public Acceleration()
    {
        this(new double[3]);
        name="Acceleration";
    }
    public Acceleration(double... p)
    {
        super(p);
        name="Acceleration";
    }
    public Acceleration(Vector v)
    {
        super(v);
        name=v.name;
    }
    public void update()
    {
        try
        {
        double[] v=getVectorComponents();
        Velocity ve=(Velocity)body.getProperty(Velocity.class);
        if (ve==null)
        {
            ve=new Velocity();
            body.addProperty(ve);
        }
        double[] vev=ve.getVectorComponents();
        for (int i=0;i<v.length;i++)
        {
            vev[i]+=v[i];
        }
        }catch(Exception e){}
    }
}
