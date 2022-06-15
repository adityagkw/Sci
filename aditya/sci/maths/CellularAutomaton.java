package aditya.sci.maths;
public class CellularAutomaton
{
    private int d;
    private NArray<Integer> state;
    private int[] rule;
    private int cs;
    private int ns;
    public CellularAutomaton(int d,int s)
    {
        this.d=d;
        cs=2;
        ns=1;
        rule=new int[(int)Math.pow(cs,Math.pow(2*ns+1,d))];
        int[] shape=new int[d];
        for (int i=0;i<shape.length;shape[i]=s,i++);
        state=new NArray<Integer>(shape);
        for (int[] ind=new int[shape.length];ind!=null;state.set(0,ind),ind=state.getNextIndex(ind));
        int[] ind=new int[d];
        for (int i=0;i<ind.length;ind[i]=shape[i]/2,i++);
        state.set(1,ind);
    }
    public CellularAutomaton(NArray<Integer> s)
    {
        state=s;
        d=s.shape.length;
    }
    
    public int getFromRule(int[] s)
    {
        int r=0;
        int ind=0;
        for (int i=0;i<s.length;ind+=s[i]*(int)Math.pow(cs,s.length-i-1),i++);
        r=rule[ind];
        return r;
    }
    public NArray<Integer> update()
    {
        NArray<Integer> copy=new NArray<Integer>(state.shape);
        for (int[] ind=new int[state.shape.length];ind!=null;ind=copy.getNextIndex(ind))
        {
            int[] s=new int[(int)Math.pow(2*ns+1,d)];
            int i2=0;
            for (int i=0;i<copy.shape.length;i++)
            {
                for (int j=-ns;j<=ns;j++)
                {
                    int[] ind2=new int[ind.length];
                    for (int k=0;k<ind2.length;ind2[k]=ind[k],k++);
                    ind2[i]+=j;
                    if (ind2[i]>=copy.shape[i])
                        ind2[i]-=copy.shape[i];
                    else if (ind2[i]<0)
                        ind2[i]+=copy.shape[i];
                    s[i2]=state.get(ind2);
                    i2++;
                }
            }
            copy.set(getFromRule(s),ind);
        }
        state=copy;
        return state;
    }
    
    public void setRuleSet(int[] ruleset)
    {
        rule=ruleset;
    }
    public int[] getRuleSet()
    {
        return rule;
    }
    public void setCellStates(int s)
    {
        cs=s;
    }
    public int getCellStates()
    {
        return cs;
    }
    public void setNeighbourhoodSize(int s)
    {
        ns=s;
    }
    public int getNeighbourhoodSize()
    {
        return ns;
    }
    public void setState(NArray<Integer> s)
    {
        state=s;
        d=s.shape.length;
    }
    public NArray<Integer> getState()
    {
        return state;
    }
    
    public int[] getRuleSetFromNumber(long n)
    {
        int l=(int)Math.pow(cs,Math.pow(2*ns+1,d));
        int[] ind=new int[l];
        long n2=n;
        for (int i=0;i<l;i++)
        {
            ind[i]=(int)(n2%cs);
            n2/=cs;
        }
        return ind;
    }
    
    public static void main(String[] args)
    {
        int n=40;
        CellularAutomaton ca=new CellularAutomaton(1,n);
        //ca.setRuleSet(new int[]{0,1,1,1,1,0,1,1});
        ca.setCellStates(2);
        ca.setNeighbourhoodSize(1);
        ca.setRuleSet(ca.getRuleSetFromNumber(3));
        for (int i=0;i<16;i++)
        {
            NArray<Integer> s=ca.getState();
            //System.out.println(s);
            for (int j=0;j<s.shape[0];j++)
                System.out.print(s.get(j)==0?" ":"X");
            System.out.println();
            ca.update();
        }
    }
}
