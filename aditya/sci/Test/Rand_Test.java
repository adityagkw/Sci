package aditya.sci.Test;
import aditya.sci.maths.*;
import java.util.*;
public class Rand_Test
{
    public static void main(String[] args)
    {
        Rand r1=new Rand();
        for (int i=0;i<r1.nextInt(100);i++)
            r1.nextDouble();
        long s=r1.getUsableSeed();
        Random r2=new Random(s);
        for (int i=0;i<10;i++)
        {
            System.out.println("Output from r1: "+r1.nextInt());
            System.out.println("Output from r2: "+r2.nextInt());
        }
    }
}
