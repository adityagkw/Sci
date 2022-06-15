package aditya.sci.maths.Disease;

public class Agent
{
    public boolean infected;
    public boolean asymtamatic;
    public boolean dead;
    public boolean immunity;
    public double x,y;
    public double infection_time;
    public DiseaseSystem parent;
    public Agent(DiseaseSystem s)
    {
        x=Math.random();
        y=Math.random();
        infected=false;
        asymtamatic=false;
        dead=false;
        immunity=false;
        infection_time=0;
        parent=s;
    }
    public void move()
    {
        x+=(Math.random()-0.5)*0.1;
        y+=(Math.random()-0.5)*0.1;
        x=(x+1)%1;
        y=(y+1)%1;
    }
    public void infect()
    {
        Agent[] agent=parent.getAgents();
        for (int i=0;i<agent.length;i++)
        {
            if (Math.hypot(agent[i].x-x,agent[i].y-y)<=parent.spread_range && !agent[i].infected && !agent[i].immunity && Math.random()<parent.spread_probability)
            {
                agent[i].infected=true;
                if (Math.random()<parent.asymtamatic_probability)
                {
                    agent[i].asymtamatic=true;
                }
                agent[i].infection_time=parent.time;
                parent.infected++;
            }
        }
    }
    public void cure()
    {
        infected=false;
        asymtamatic=false;
        immunity=true;
        parent.infected--;
        parent.cured++;
    }
    public void die()
    {
        dead=true;
        infected=false;
        asymtamatic=false;
        parent.infected--;
        parent.dead++;
    }
    public void update()
    {
        move();
        if (infected)
        {
            if ((parent.time-infection_time)>parent.spread_start)
                infect();
            if (Math.random()<parent.cure_probability)
                cure();
            else if (Math.random()<parent.death_probability && !asymtamatic && (parent.time-infection_time)>parent.spread_start)
                die();
        }
    }
}
