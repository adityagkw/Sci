package aditya.sci.maths.quantum;
import java.util.*;
public class QCircuit
{
    QBit[] qbit;
    ArrayList<QGate> gate=new ArrayList<QGate>();
    HashMap<String,Object> measurement=new HashMap<String,Object>();
    public QCircuit(int qbits)
    {
        qbit=new QBit[qbits];
        for (int i=0;i<qbits;i++)
        {
            qbit[i]=new QBit();
            qbit[i].parent=this;
            qbit[i].index=i;
        }
    }
    public QCircuit(QBit... qbits)
    {
        qbit=new QBit[qbits.length];
        for (int i=0;i<qbit.length;i++)
        {
            qbit[i]=qbits[i];
            //qbit[i].parent=this;
            //qbit[i].index=i;
        }
    }
    public QBit getQBit(int i)
    {
        return qbit[i];
    }
    public void add(QGate g)
    {
        gate.add(g);
        g.parent=this;
    }
    public void remove(QGate g)
    {
        gate.remove(g);
        g.parent=null;
    }
    public void add(QCircuit c)
    {
        for (int i=0;i<c.gate.size();i++)
        {
            add(c.gate.get(i));
        }
    }
    public void remove(QCircuit c)
    {
        for (int i=0;i<c.gate.size();i++)
        {
            remove(c.gate.get(i));
        }
    }
    public void run()
    {
        for (int i=0;i<qbit.length;i++)
        {
            qbit[i].state=new QState(new double[]{1,0});
            qbit[i].entangled=false;
            qbit[i].e=null;
        }
        for (int i=0;i<gate.size();i++)
        {
            //System.out.println(gate.get(i).getClass());
            gate.get(i).run();
        }
    }
    public void showMeasurements()
    {
        for (String key:measurement.keySet())
        {
            System.out.println(key+":\n"+measurement.get(key));
        }
    }
    private String format(String s)
    {
        if (s.length()>7)
        {
            s=s.substring(0,7);
        }
        for (int i=s.length();i<7;s+="-",i++);
        return s;
    }
    public String toString()
    {
        String str="",con="-------";
        String[][] s=new String[qbit.length*2][gate.size()*2+2];
        for (int i=0;i<s.length;i++)
        {
            for (int j=0;j<s[i].length;j++)
            {
                s[i][j]="";
            }
        }
        for (int i=0;i<qbit.length;i++)
        {
            s[2*i][0]=format("Q"+i);
            s[2*i][1]=con;
        }
        for (int i=0;i<gate.size();i++)
        {
            QGate g=gate.get(i);
            int qi=g.operand[0].index;
            s[2*qi][2+2*i]=format(g.toString(0));
            s[2*qi][3+2*i]=con;
            if (g.operand.length>1) s[2*qi+1][2+2*i]="|";
            for (int j=0;j<qbit.length;j++)
            {
                if (j!=qi)
                {
                    boolean c=false;
                    int k=0;
                    for (k=0;k<g.operand.length;k++)
                    {
                        if (g.operand[k].index==j)
                        {
                            c=true;
                            break;
                        }
                    }
                    s[2*j][2+2*i]=c?format(g.toString(k)):con;
                    s[2*j][3+2*i]=con;
                    if (g.operand.length>1)s[2*j+1][2+2*i]="|";
                }
                
            }
        }
        
        for (int i=0;i<s.length-1;i++)
        {
            for (int j=0;j<s[i].length;j++)
            {
                str+=s[i][j]+"\t";
            }
            str+="\n";
        }
        return str;
    }
    public static void test1()
    {
        QCircuit c=new QCircuit(2);
        QBit q0=c.getQBit(0);
        QBit q1=c.getQBit(1);
        c.add(new X(q0));
        c.add(new CNOT(q0,q1));
        c.add(new Measure(q0,"A"));
        c.add(new Measure(q1,"B"));
        System.out.println(c);
        for (int i=0;i<10;i++)
        {
            c.run();
            c.showMeasurements();
            System.out.println();
        }
    }
    public static void test2()
    {
        QCircuit c=new QCircuit(3);
        QBit q0=c.getQBit(0);
        QBit q1=c.getQBit(1);
        QBit q2=c.getQBit(2);
        
        c.add(new H(q2));
        c.add(new CNOT(q2,q1));
        //
        c.add(new DebugMatrix(q0,"A",true));
        //c.add(new X(q0));
        //
        c.add(new CNOT(q0,q1));
        c.add(new H(q0));
        c.add(new CNOT(q1,q2));
        c.add(new CZ(q0,q2));
        //c.add(new Measure(q0,"1"));
        //c.add(new Measure(q1,"2"));
        //c.add(new Measure(q2,"3"));
        c.add(new DebugMatrix(q2,"B",true));
        System.out.println(c);
        for (int i=0;i<10;i++)
        {
            c.run();
            c.showMeasurements();
            System.out.println("------------------------------");
        }
    }
    public static void test3()
    {
        QCircuit c=new QCircuit(3);
        QBit q0=c.getQBit(0);
        QBit q1=c.getQBit(1);
        QBit q2=c.getQBit(2);
        c.add(new H(q0));
        c.add(new CNOT(q0,q1));
        c.add(new CNOT(q0,q2));
        c.add(new Measure(q0,"0"));
        c.add(new Measure(q1,"1"));
        c.add(new Measure(q2,"2"));
        System.out.println(c);
        for (int i=0;i<10;i++)
        {
            c.run();
            c.showMeasurements();
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        test2();
    }
}
