package aditya.sci.Test;
import aditya.sci.physics.*;
public class BodyTest
{
    public static void main(String[] args)
    {
        BodySystem bs=new BodySystem();
        Body b=new Body();
        Position p=new Position();
        b.addProperty(p);
        Velocity v=new Velocity();
        b.addProperty(v);
        Acceleration a=new Acceleration(1);
        b.addProperty(a);
        bs.addBody(b);
        while (true)
        {
            System.out.println(p);
            try
            {
                Thread.sleep(bs.update());
            }catch(Exception e){}
        }
    }
}
