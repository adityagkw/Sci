package aditya.sci.maths;
/**
A class to find frustum projection of a n dimentional object.
Projects a point in n dimensions to a point in (n-1) dimensions.
Can be used repeatedly to convert a point from n dimension to-
<br>
n -> (n-1) -> (n-2) -> ... -> 4 -> 3 -> 2 -> 1.
<br>
<br>
<b>Note:</b> Whenever a function asks for an axis, the no. of that axis (X=0,Y=1,Z=2,...) should be used.
@author Aditya
@version 1.0
 */
public class NDCamera
{
    /**
    Number of dimensions.
     */
    public final int n;
    private double[] cl;
    private double[] cr;
    private double l;
    /**
    Creates a NDCamera with n number of dimensions.
    @param n Number of dimensions.
     */
    public NDCamera(int n)
    {
        this.n=n;
        cl=new double[n];
        cr=new double[(int)Maths.C(n,2)];
        l=-1/Math.tan((Math.PI/2)/2); //FOV=Pi/2
        
       /*int i1=0,i2=0;
       for (int j=0;j<cr.length;j++)
       {
           i2=i2+1;
           if (i2==n)
           {
               i1++;
               i2=i1+1;
           }
           System.out.println((char)('X'+i1)+" "+(char)('X'+i2));
       }*/
    }
    /**
    Moves the camera by l on axis.
    @param axis Axis number of the axis.
    @param l Number of units to move by on axis.
     */
    public void moveOnAxisBy(int axis,double l)
    {
        cl[axis]+=l;
    }
    /**
    Moves the camera to l on axis.
    @param axis Axis number of the axis.
    @param l Location of camera in units on axis.
     */
    public void moveOnAxisTo(int axis,double l)
    {
        cl[axis]=l;
    }
    /**
    Returns the current location of camera on axis.
    @param axis Axis number of the axis.
    @return Location of camera on axis.
     */
    public double getLocationOnAxis(int axis)
    {
        return cl[axis];
    }
    /**
    Returns the current location of Camera.
    @return Location of camera.
     */
    public double[] getLocation()
    {
        return cl;
    }
    
    public void rotateInPlaneBy(int axis1,int axis2,double ang)
    {
        int i=0,i1=0,i2=1;
        for (;i<cr.length;i++) //Locates index of axises used for rotation
        {
            if (i1==axis1 && i2==axis2)
                break;
            i2=i2+1;
            if (i2==n)
            {
                i1++;
                i2=i1+1;
            }
            //System.out.println((char)('X'+i1)+" "+(char)('X'+i2));
        }
        cr[i]+=ang;
    }
    public void rotateInPlaneTo(int axis1,int axis2,double ang)
    {
        int i=0,i1=0,i2=1;
        for (;i<cr.length;i++) //Locates index of axises used for rotation
        {
            i2=i2+1;
            if (i2==n)
            {
                i1++;
                i2=i1+1;
            }
            //System.out.println((char)('X'+i1)+" "+(char)('X'+i2));
        }
        cr[i]=ang;
    }
    public double getRotationInPlane(int axis1,int axis2)
    {
        int i=0,i1=0,i2=1;
        for (;i<cr.length;i++) //Locates index of axises used for rotation
        {
            i2=i2+1;
            if (i2==n)
            {
                i1++;
                i2=i1+1;
            }
            //System.out.println((char)('X'+i1)+" "+(char)('X'+i2));
        }
        return cr[i];
    }
    public double[] getRotation()
    {
        return cr;
    }
    public void setFOV(double ang)
    {
        l=-1/Math.tan(ang/2);
    }
    public double getFOV()
    {
        return Math.atan(-1/l)*2;
    }
    public double[] getPoint(double[] p)
    {
        double[] op=new double[n-1];
        if (p.length!=n)
            return null;
        double[] p2=new double[n];
        for(int i=0;i<n;i++)  //Translates Camera to appropriate location
        {
            p2[i]=p[i]-cl[i];
        }
        for (int i=0;i<cr.length;i++) //Rotates camera to appropriate rotation
        {
           int i1=0,i2=1;
           for (int j=0;j<i;j++) //Locates index of axises used for rotation
           {
               i2=i2+1;
               if (i2==n)
               {
                   i1++;
                   i2=i1+1;
               }
               //System.out.println((char)('X'+i1)+" "+(char)('X'+i2));
           }
           //System.out.println((char)('X'+i1)+" "+(char)('X'+i2));
           double a1=p2[i1],a2=p2[i2];
           double ang=Math.atan2(a2,a1);
           //System.out.println(a1+" "+a2);
           ang=ang-cr[i];
           double h=Math.hypot(a1,a2);
           p2[i2]=Math.sin(ang)*h;
           p2[i1]=Math.cos(ang)*h;
        }
        for(int i=0;i<n-1;i++)
        {
            //System.out.println(l);
            op[i]=(l/p2[n-1])*p2[i];
        }
        return op;
    }
    public static double[] getPoint(double[] camloc,double[] camrot,double[] p)
    {
        NDCamera cam=new NDCamera(camloc.length);
        cam.cl=camloc;
        cam.cr=camrot;
        return cam.getPoint(p);
    }
    public static double[] getPoint2(double[] p)
    {
        NDCamera cam=new NDCamera(p.length);
        return cam.getPoint(p);
    }
}
