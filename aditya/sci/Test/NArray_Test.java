package aditya.sci.Test;
import aditya.sci.maths.*;
public class NArray_Test
{
    public static void main(String[] args)
    {
        NArray<Integer> a=new NArray<Integer>(3,2);
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<2;j++)
            {
                a.set(i*2+j+1,i,j);
                System.out.println("a["+i+"]["+j+"]="+a.get(i,j));
            }
        }
    }
}
