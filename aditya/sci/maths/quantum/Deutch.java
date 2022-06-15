package aditya.sci.maths.quantum;
public class Deutch
{
    interface BlackBox
    {
        public void run(QBit in,QBit out,QCircuit c);
    }
    public static void set0(QBit in,QBit out,QCircuit c)
    {
        
    }
    public static void set1(QBit in,QBit out,QCircuit c)
    {
        c.add(new X(out));
    }
    public static void identity(QBit in,QBit out,QCircuit c)
    {
        c.add(new CNOT(out,in));
    }
    public static void inverse(QBit in,QBit out,QCircuit c)
    {
        c.add(new X(out));
        c.add(new CNOT(out,in));
    }
    public static boolean blackbox(BlackBox bb,QBit in,QBit out,QCircuit c)
    {
        c.add(new X(in));
        c.add(new H(out));
        c.add(new H(in));
        bb.run(in,out,c);
        c.add(new H(out));
        c.add(new Measure(out,"BBO"));
        c.run();
        return (boolean)c.measurement.get("BBO");
    }
    public static void main(String[] args)
    {
        QCircuit c=new QCircuit(2);
        QBit out=c.getQBit(0);
        QBit in=c.getQBit(1);
        System.out.println(Deutch.blackbox(Deutch::set1,in,out,c));
        System.out.println(c);
    }
}
