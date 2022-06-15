package aditya.sci.maths;

public class MiniMax
{
    public static double minimax(MiniMaxNode node,int depth,boolean maximize)
    {
        MiniMaxNode[] children=node.getChildren();
        if (depth==0 || children==null || children.length==0)
        {
            return node.eval();
        }
        if (maximize)
        {
            double max=Double.NEGATIVE_INFINITY;
            for (MiniMaxNode child:children)
            {
                double v=minimax(child,depth-1,false);
                max=Math.max(max,v);
            }
            return max;
        }
        else
        {
            double min=Double.POSITIVE_INFINITY;
            for (MiniMaxNode child:children)
            {
                double v=minimax(child,depth-1,true);
                min=Math.min(min,v);
            }
            return min;
        }
    }
    public static double minimaxalphabeta(MiniMaxNode node,int depth,boolean maximize,double alpha,double beta)
    {
        MiniMaxNode[] children=node.getChildren();
        if (depth==0 || children==null || children.length==0)
        {
            return node.eval();
        }
        if (maximize)
        {
            double max=Double.NEGATIVE_INFINITY;
            for (MiniMaxNode child:children)
            {
                double v=minimaxalphabeta(child,depth-1,false,alpha,beta);
                max=Math.max(max,v);
                alpha=Math.max(max,alpha);
                if (beta<=alpha)
                    break;
            }
            return max;
        }
        else
        {
            double min=Double.POSITIVE_INFINITY;
            for (MiniMaxNode child:children)
            {
                double v=minimaxalphabeta(child,depth-1,true,alpha,beta);
                min=Math.min(min,v);
                beta=Math.min(min,beta);
                if (beta<=alpha)
                    break;
            }
            return min;
        }
    }
    public static double minimaxalphabeta(MiniMaxNode node,int depth,boolean maximize)
    {
        return minimaxalphabeta(node,depth,maximize,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
    }
}
