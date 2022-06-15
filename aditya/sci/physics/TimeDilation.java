package aditya.sci.physics;
public class TimeDilation extends Property
{
    public double c=299792458;
    public void update()
    {
        try
        {
        Velocity ve=(Velocity)body.getProperty(Velocity.class);
        double v=ve.getMagnitude();
        body.dtick=(int)(body.tick/Math.sqrt(1-((v*v)/(c*c))));
        }catch(Exception e){}
    }
}
