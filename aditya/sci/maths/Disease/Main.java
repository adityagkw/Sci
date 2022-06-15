package aditya.sci.maths.Disease;
import java.awt.*;
import javax.swing.*;
public class Main extends JPanel
{
    DiseaseSystem s=new DiseaseSystem();
    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,500,500);
        Agent[] agent=s.getAgents();
        for (int i=0;i<agent.length;i++)
        {
            if (agent[i].dead)
            {
                g.setColor(Color.BLACK);
            }
            else if (agent[i].asymtamatic)
            {
                g.setColor(Color.YELLOW);
            }
            else if (agent[i].infected)
            {
                g.setColor(Color.RED);
            }
            else if (agent[i].immunity)
            {
                g.setColor(Color.BLUE);
            }
            else
            {
                g.setColor(Color.GREEN);
            }
            g.fillOval((int)(agent[i].x*300),(int)(agent[i].y*300),5,5);
        }
        g.setColor(Color.GRAY);
        g.fillRect(0,300,500,100);
        int ti=(int)(s.time<100?0:(s.time-100));
        for (int i=0;i<(s.time>100?100:s.time);i++)
        {
            g.setColor(Color.GREEN);
            g.fillRect(i*5,(int)(400-s.unaffected_data.get(i+ti)),5,1);
            g.setColor(Color.BLUE);
            g.fillRect(i*5,(int)(400-s.cured_data.get(i+ti)),5,1);
            g.setColor(Color.BLACK);
            g.fillRect(i*5,(int)(400-s.dead_data.get(i+ti)),5,1);
            g.setColor(Color.RED);
            g.fillRect(i*5,(int)(400-s.infected_data.get(i+ti)),5,1);
            
        }
        g.setColor(Color.GREEN);
        g.drawString("SUSEPTIBLE",320,50);
        g.setColor(Color.RED);
        g.drawString("INFECTED",320,80);
        g.setColor(Color.YELLOW);
        g.drawString("ASYMTAMATIC",320,110);
        g.setColor(Color.BLUE);
        g.drawString("RECOVERED",320,140);
        g.setColor(Color.BLACK);
        g.drawString("DEAD",320,170);
        g.setColor(Color.BLACK);
        String str="Time: "+s.time+"\t Infected: "+s.infected+"\t Death: "+s.dead+"\t Suseptible: "+(agent.length-s.infected-s.cured)+"\t Cured: "+s.cured;
        g.drawString(str,0,450);
    }
    
    public static void main(String[] args)
    {
        JFrame f=new JFrame("Disease Simulation");
        f.setSize(500,500);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main m=new Main();
        f.add(m,BorderLayout.CENTER);
        f.setVisible(true);
        while(true)
        {
            try
            {
                m.s.update();
                m.repaint();
                f.repaint();
                Thread.sleep(100);
            }catch(Exception e){e.printStackTrace();}
        }
    }
}
