package aditya.sci.physics;
import java.util.*;
public class Body
{
    private ArrayList<Property> properties;
    public int tick=1000,dtick=1000;
    public String name;
    public BodySystem system=null;
    public Body()
    {
        properties=new ArrayList<Property>();
        name="Body";
        /*(new Thread()
        {
            public void run()
            {
                while(true)
                {
                    if (getProperty(TimeDilation.class)==null){dtick=tick;}
                    for (int i=0;i<properties.size();i++)
                    {
                        Property p=properties.get(i);
                        p.update();
                    }
                    try
                    {
                        Thread.sleep(dtick);
                    }catch(Exception e){}
                }
            }
        }).start();*/
    }
    public Body(String name)
    {
        properties=new ArrayList<Property>();
        this.name=name;
        /*(new Thread()
        {
            public void run()
            {
                while(true)
                {
                    if (getProperty(TimeDilation.class)==null){dtick=tick;}
                    for (int i=0;i<properties.size();i++)
                    {
                        Property p=properties.get(i);
                        p.update();
                    }
                    try
                    {
                        Thread.sleep(dtick);
                    }catch(Exception e){}
                }
            }
        }).start();*/
    }
    public void runAuto()
    {
        (new Thread()
        {
            public void run()
            {
                while(true)
                {
                    if (getProperty(TimeDilation.class)==null){dtick=tick;}
                    for (int i=0;i<properties.size();i++)
                    {
                        Property p=properties.get(i);
                        p.update();
                    }
                    try
                    {
                        Thread.sleep(dtick);
                    }catch(Exception e){}
                }
            }
        }).start();
    }
    public void addProperty(Property p)
    {
        p.body=this;
        properties.add(p);
    }
    public Property getProperty(Class c)
    {
        for (int i=0;i<properties.size();i++)
        {
            Property p=properties.get(i);
            if (c.equals(p.getClass()))
            {
                return p;
            }
        }
        return null;
    }
    public Property getProperty(String name)
    {
        for (int i=0;i<properties.size();i++)
        {
            Property p=properties.get(i);
            if (name.equals(p.getName()))
            {
                return p;
            }
        }
        return null;
    }
    public Property getProperty(int i)
    {
        return properties.get(i);
    }
    public void removeProperty(Property p)
    {
        properties.remove(p);
        p.body=null;
    }
    public void removeProperty(int i)
    {
        removeProperty(properties.get(i));
    }
    public void removeProperty(String name)
    {
        removeProperty(getProperty(name));
    }
    public void removeProperty(Class c)
    {
        removeProperty(getProperty(c));
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public int update()
    {
        if (getProperty(TimeDilation.class)==null){dtick=tick;}
        for (int i=0;i<properties.size();i++)
        {
            Property p=properties.get(i);
            p.update();
        }
        return dtick;
    }
}
