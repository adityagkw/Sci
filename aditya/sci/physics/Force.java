package aditya.sci.physics;
public class Force extends Vector
{
    public Force()
    {
        super(new double[3]);
        name="Force";
    }
    public Force(double... c)
    {
        super(c);
        name="Force";
    }
    public Force(Vector v)
    {
        super(v);
        name=v.name;
    }
    public void update()
    {
        double m=((Mass)body.getProperty(Mass.class)).getMagnitude();
        double[] fc=getVectorComponents();
        Velocity v=(Velocity)body.getProperty(Velocity.class);
        if (v==null)
        {
            v=new Velocity();
            body.addProperty(v);
        }
        double[] vv=v.getVectorComponents();
        for (int i=0;i<fc.length;vv[i]+=fc[i]/m,i++);
    }
}
