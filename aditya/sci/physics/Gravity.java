package aditya.sci.physics;
public class Gravity extends Property
{
    double G=6.67e-11;
    public Gravity()
    {
        
    }
    public void update()
    {
        double M=((Mass)body.getProperty(Mass.class)).get();
        Position bp=(Position)body.getProperty(Position.class);
        BodySystem bs=body.system;
        for (int i=0;i<bs.size();i++)
        {
            Body b=bs.getBody(i);
            if (b!=body)
            {
                Velocity vel=(Velocity)body.getProperty(Velocity.class);
                if (vel==null)
                {
                    vel=new Velocity();
                    b.addProperty(vel);
                }
                Mass bm=(Mass)b.getProperty(Mass.class);
                if (bm!=null)
                {
                    Position p=(Position)b.getProperty(Position.class);
                    Vector r=Vector.sub(p,bp);
                    double[] vc=vel.getVectorComponents();
                    Vector dir=r.getDirection();
                    double R=r.getMagnitude();
                    /*if (R<1 && R>=0)
                    {
                        R=1;
                    }
                    else if (R>-1 && R<0)
                    {
                        R=-1;
                    }*/
                    double v=G*M/(R*R);
                    if ((""+v).equals("NaN"))
                    {
                        v=0;
                        //System.out.println("p: "+p+" bp:"+bp);
                    }
                    for (int j=0;j<vc.length;j++)
                    {
                        vc[j]+=dir.getComponent(j)*v;
                        //System.out.println(vc[j]);
                    }
                }
            }
        }
    }
}
