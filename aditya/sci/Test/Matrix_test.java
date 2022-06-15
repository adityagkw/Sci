package aditya.sci.Test;
import aditya.sci.maths.*;
public class Matrix_test
{
    public static void main(String args[])
    {
        //x+2y=11
        //3x-3y=6
        Matrix c=new Matrix(new double[][]
        {
            {1,2},
            {3,-3}
        });
        Matrix a=new Matrix(new double[][]
        {
            {11},
            {6}
        });
        Matrix x=Matrix.mul(c.inverse(),a);
        System.out.println(x);
    }
}
