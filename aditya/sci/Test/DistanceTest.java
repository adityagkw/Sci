package aditya.sci.Test;
import aditya.sci.physics.*;
public class DistanceTest
{
    public static void main(String[] args)
    {
        Position p1=new Position(0,0,0);
        Position p2=new Position(1,1,1);
        Position pd=new Position(Vector.sub(p2,p1));
        System.out.println(pd.getMagnitude());
    }
}
