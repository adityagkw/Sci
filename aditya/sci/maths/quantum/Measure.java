package aditya.sci.maths.quantum;
public class Measure extends QGate
{
    String name;
    public Measure(QBit b,String name)
    {
        operand=new QBit[]{b};
        this.name=name;
    }
    public void run()
    {
        parent.measurement.put(name,operand[0].measure());
    }
    public String toString(int i)
    {
        return ""+name+"";
    }
}
