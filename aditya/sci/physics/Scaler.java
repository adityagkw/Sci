package aditya.sci.physics;

public class Scaler extends Vector
{
    public Scaler()
    {
        this(0);
        name="Scaler";
    }
    public Scaler(double v)
    {
        super(new double[]{v});
        name="Scaler";
    }
    public Scaler(Scaler s)
    {
        this(s.get());
        name=s.name;
    }
    public double get()
    {
        return getComponent(0);
    }
    public void set(double v)
    {
        setComponent(v,0);
    }
    public static Scaler add(Scaler a,Scaler b)
    {
        return (Scaler)Tensor.add(a,b);
    }
    public static Scaler sub(Scaler a,Scaler b)
    {
        return (Scaler)Tensor.sub(a,b);
    }
    public static Scaler mul(Scaler a,Scaler b)
    {
        return (Scaler)Tensor.mul(a,b);
    }
    public static Scaler div(Scaler a,Scaler b)
    {
        return (Scaler)Tensor.div(a,b);
    }
}
