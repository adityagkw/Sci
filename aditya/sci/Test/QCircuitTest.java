package aditya.sci.Test;
import aditya.sci.maths.quantum.*;
public class QCircuitTest
{
    public static void main(String[] args)
    {
        int n=10;
        QCircuit c=new QCircuit(n);
        for (int i=1;i<n;i++)
        {
            c.add(new SWAP(c.getQBit(i-1),c.getQBit(i)));
        }
        for (int i=0;i<n;i++)
        {
            c.add(new Measure(c.getQBit(i),"M"+i));
        }
        System.out.println(c);
        c.run();
        c.showMeasurements();
    }
}
