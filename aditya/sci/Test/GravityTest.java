package aditya.sci.Test;
import aditya.sci.physics.*;
public class GravityTest
{
    public static void main(String[] args)
    {
        BodySystem bs=new BodySystem();
        Body b1=new Body();
        Position p1=new Position(new double[]{-1,0,0,0});
        b1.addProperty(p1);
        Velocity v1=new Velocity(new double[]{0,0,0});
        b1.addProperty(v1);
        Mass m1=new Mass(1);
        b1.addProperty(m1);
        Gravity g1=new Gravity();
        b1.addProperty(g1);
        bs.addBody(b1);
        Body b2=new Body();
        Position p2=new Position(new double[]{1,0,0,0});
        b2.addProperty(p2);
        Velocity v2=new Velocity(new double[]{0,0,0});
        b2.addProperty(v2);
        Mass m2=new Mass(1);
        b2.addProperty(m2);
        Gravity g2=new Gravity();
        b2.addProperty(g2);
        bs.addBody(b2);
        while(true)
        {
            System.out.print("\u000c");
            System.out.println("p1: "+p1);
            System.out.println("p2: "+p2);
            System.out.println("v1: "+v1);
            System.out.println("v2: "+v2);
            try
            {
                Thread.sleep((int)(bs.update()*0.01));
            }catch(Exception e){}
        }
    }
}
