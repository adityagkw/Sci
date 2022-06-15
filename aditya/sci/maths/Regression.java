package aditya.sci.maths;

public class Regression
{
    double[][] X;
    double[] Y;
    int n;
    int m;
    public Matrix mat;
    public Regression()
    {
        this(3);
    }
    public Regression(int n)
    {
        this.n=n;
    }
    public void fit(double[][] X,double[] Y)
    {
        this.X=X;
        this.Y=Y;
        m=X[0].length;
    }
    private double avg(int[] xpow,int ypow)
    {
        double sum=0;
        for (int i=0;i<Y.length;i++)
        {
            double prod=Math.pow(Y[i],ypow);
            prod*=pow(X[i],xpow);
            //for (int j=0;j<xpow.length;prod*=Math.pow(X[i][j],xpow[j]),j++);
            sum+=prod;
        }
        return sum/Y.length;
    }
    private double pow(double[] x,int[] xpow)
    {
        double prod=1;
        for (int i=0;i<xpow.length;prod*=Math.pow(x[i],xpow[i]),i++);
        return prod;
    }
    public void train()
    {
        //mat=new Matrix((int)Math.pow(n,m),1);
        Matrix inp=new Matrix((int)Math.pow(n+1,m),(int)Math.pow(n+1,m));
        Matrix outp=new Matrix((int)Math.pow(n+1,m),1);
        int[] ind1=new int[m];
        int i1=0;
        while (ind1[ind1.length-1]<=n)
        {
            int[] ind2=new int[m];
            int i2=0;
            while (ind2[ind2.length-1]<=n)
            {
                int[] ind=new int[m];
                for (int i=0;i<ind.length;ind[i]=ind1[i]+ind2[i],i++);
                double ans=avg(ind,0);
                inp.set(i1,i2,ans);
                ind2[0]++;
                i2++;
                for (int i=1;i<ind2.length;i++)
                {
                    if (ind2[i-1]>n)
                    {
                        ind2[i-1]=0;
                        ind2[i]++;
                    }
                }
            }
            outp.set(i1,0,avg(ind1,1));
            ind1[0]++;
            i1++;
            for (int i=1;i<ind1.length;i++)
            {
                if (ind1[i-1]>n)
                {
                    ind1[i-1]=0;
                    ind1[i]++;
                }
            }
        }
        System.out.println(inp);
        System.out.println(outp);
        Matrix inv=inp.inverse();
        System.out.println(inv);
        mat=Matrix.mul(inv,outp);
    }
    public double predict(double[] inp)
    {
        double ans=0;
        Matrix min=new Matrix(1,(int)Math.pow(n+1,m));
        int[] ind1=new int[m];
        int i1=0;
        while (ind1[ind1.length-1]<=n)
        {
            
            min.set(0,i1,pow(inp,ind1));
            
            ind1[0]++;
            i1++;
            for (int i=1;i<ind1.length;i++)
            {
                if (ind1[i-1]>n)
                {
                    ind1[i-1]=0;
                    ind1[i]++;
                }
            }
        }
        Matrix a=Matrix.mul(min,mat);
        ans=a.get(0,0);
        return ans;
    }
}
