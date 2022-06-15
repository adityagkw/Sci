package aditya.sci.physics;
public class Mass extends Scaler
{
    public Mass()
    {
        super();
        name="Mass";
    }
    public Mass(double m)
    {
        super(m);
        name="Mass";
    }
    public Mass(Scaler s)
    {
        super(s);
        name=s.name;
    }
}
