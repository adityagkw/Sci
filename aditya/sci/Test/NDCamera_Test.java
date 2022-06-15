package aditya.sci.Test;
import aditya.sci.maths.*;
public class NDCamera_Test
{
    public static void main(String[] args)
    {
        int n=3;
        NDCamera cam=new NDCamera(n);
        double[] p=new double[n];
        p[0]=0;
        p[1]=0;
        p[2]=-1;
        //cam.moveOnAxisBy(2,1);
        cam.rotateInPlaneBy(1,2,Math.PI/2);
        double[] o=cam.getPoint(p);
        for (int i=0;i<o.length;i++)
        {
            System.out.print(o[i]+" ");
        }
    }
}
