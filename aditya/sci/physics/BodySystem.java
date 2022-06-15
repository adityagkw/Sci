package aditya.sci.physics;
import java.util.*;
public class BodySystem
{
    private ArrayList<Body> bodies;
    public String name;
    public BodySystem()
    {
        bodies=new ArrayList<Body>();
        name="Simple System";
    }
    public BodySystem(String name)
    {
        bodies=new ArrayList<Body>();
        this.name=name;
    }
    public int size()
    {
        return bodies.size();
    }
    public void addBody(Body b)
    {
        b.system=this;
        bodies.add(b);
    }
    public Body getBody(int i)
    {
        return bodies.get(i);
    }
    public Body getBody(String name)
    {
        for (int i=0;i<size();i++)
        {
            if (getBody(i).name.equals(name))
                return getBody(i);
        }
        return null;
    }
    public void removeBody(Body b)
    {
        bodies.remove(b);
        b.system=null;
    }
    public void removeBody(int i)
    {
        removeBody(getBody(i));
    }
    public void removeBody(String name)
    {
        removeBody(getBody(name));
    }
    public int update()
    {
        int mt=0;
        for (int i=0;i<size();i++)
        {
            Body b=getBody(i);
            int t=b.update();
            if (t>mt)
                mt=t;
        }
        return mt;
    }
    public void autoUpdate()
    {
        (new Thread()
        {
            public void run()
            {
                while(true)
                {
                    try
                    {
                        Thread.sleep(update());
                    }catch(Exception e){}
                }
            }
        }).start();
    }
}
