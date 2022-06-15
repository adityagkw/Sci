package aditya.sci.maths;
public class Hanoi
{
    public void move(String s,String d)
    {
        System.out.println(s+" -> "+d);
    }
    public void moveHanoi(int n,String s,String h,String d)
    {
        if (n<=1)
        {
            move(s,d);
        }
        else
        {
            moveHanoi(n-1,s,d,h);
            move(s,d);
            moveHanoi(n-1,h,s,d);
        }
    }
    
    public static void main(String args[])
    {
        Hanoi h=new Hanoi();
        h.moveHanoi(4,"A","B","C");
        ///(2^n)-1
    }
}
