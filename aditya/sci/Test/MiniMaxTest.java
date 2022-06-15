package aditya.sci.Test;
import aditya.sci.maths.*;
public class MiniMaxTest
{
    public static void main(String[] args)
    {
        SimpleMiniMaxNode a1=new SimpleMiniMaxNode(1);
        SimpleMiniMaxNode a2=new SimpleMiniMaxNode(2);
        SimpleMiniMaxNode a3=new SimpleMiniMaxNode(3);
        SimpleMiniMaxNode a4=new SimpleMiniMaxNode(4);
        SimpleMiniMaxNode b1=new SimpleMiniMaxNode(a1,a2);
        SimpleMiniMaxNode b2=new SimpleMiniMaxNode(a3,a4);
        SimpleMiniMaxNode c1=new SimpleMiniMaxNode(b1,b2);
        System.out.println("Minimum: "+MiniMax.minimax(c1,2,false));
        System.out.println("Maximum: "+MiniMax.minimax(c1,2,true));
        System.out.println("Minimum alpha beta: "+MiniMax.minimaxalphabeta(c1,2,false));
        System.out.println("Maximum alpha beta: "+MiniMax.minimaxalphabeta(c1,2,true));
    }
}
