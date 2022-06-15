package aditya.sci.maths;
import java.util.*;
public class SimpleMiniMaxNode implements MiniMaxNode
{
    ArrayList<MiniMaxNode> children=new ArrayList<MiniMaxNode>();
    double score=0;
    public SimpleMiniMaxNode()
    {
        
    }
    public SimpleMiniMaxNode(double score)
    {
        this.score=score;
    }
    public SimpleMiniMaxNode(MiniMaxNode... child)
    {
        for (MiniMaxNode c:child)
        {
            children.add(c);
        }
    }
    
    public void add(MiniMaxNode... child)
    {
        for (MiniMaxNode c:child)
        {
            children.add(c);
        }
    }
    public void remove(MiniMaxNode... child)
    {
        for (MiniMaxNode c:child)
        {
            children.remove(c);
        }
    }
    
    public double eval()
    {
        return score;
    }
    public MiniMaxNode[] getChildren()
    {
        MiniMaxNode[] n=new MiniMaxNode[children.size()];
        for (int i=0;i<n.length;n[i]=children.get(i),i++);
        return n;
    }
}
