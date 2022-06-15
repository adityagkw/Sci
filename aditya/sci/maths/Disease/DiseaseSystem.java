package aditya.sci.maths.Disease;
import java.util.*;
public class DiseaseSystem
{
    double incubation_period;
    double spread_probability;
    double spread_range;
    double asymtamatic_probability;
    double spread_start;
    double death_probability;
    double d_death_probability;
    double cure_probability;
    double R0;
    
    ArrayList<Agent> agent=new ArrayList<Agent>();
    ArrayList<Double> infected_data=new ArrayList<Double>();
    ArrayList<Double> dead_data=new ArrayList<Double>();
    ArrayList<Double> cured_data=new ArrayList<Double>();
    ArrayList<Double> unaffected_data=new ArrayList<Double>();
    volatile double infected;
    volatile double dead;
    volatile double cured;
    double time;
    
    public DiseaseSystem()
    {
        incubation_period=5;
        spread_probability=0.1;
        spread_range=0.1;
        spread_start=1;
        asymtamatic_probability=0.1;
        death_probability=0.01;
        cure_probability=0.01;
        
        for (int i=0;i<100;i++)
        {
            Agent a=new Agent(this);
            agent.add(a);
        }
        agent.get(0).infected=true;
        infected=1;
        infected_data.add(infected);
        dead_data.add(dead);
        cured_data.add(cured);
        unaffected_data.add((double)99);
    }
    
    public Agent[] getAgents()
    {
        Agent[] a=new Agent[agent.size()];
        Object[] o=agent.toArray();
        for (int i=0;i<a.length;a[i]=(Agent)o[i],i++);
        return a;
    }
    
    public void update()
    {
        Agent[] agents=getAgents();
        boolean inf=false;
        for (int i=0;i<agents.length;i++)
        {
            if (agents[i].infected)
            {
                inf=true;
                break;
            }
        }
        if (inf)
        {
            time++;
            for (int i=0;i<agents.length;i++)
            {
                if (!agents[i].dead)
                    agents[i].update();
            }
            infected_data.add(infected);
            dead_data.add(dead);
            cured_data.add(cured);
            unaffected_data.add(agents.length-infected-cured);
        }
    }
}
