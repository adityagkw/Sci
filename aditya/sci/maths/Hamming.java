package aditya.sci.maths;
public class Hamming extends ErrorCorrection
{
    Matrix G;
    Matrix H;
    public Hamming(int n)
    {
        int m=(int)Math.pow(2,n)-1;
        G=new Matrix(m-n,m);
        H=new Matrix(n,m);
        int x=0,y=m-n;
        for (int i=1;i<=m;i++)
        {
            int k=0;
            double l=Math.log(i)/Math.log(2);
            if (l==(int)l)
            {
                k=y;
                y++;
            }
            else
            {
                k=x;
                x++;
            }
            int i2=i;
            for (int j=0;j<n;j++)
            {
                H.set(j,k,i2%2);
                i2/=2;
            }
        }
        for (int i=0;i<m-n;i++)
        {
            G.set(i,i,1);
            for (int j=0;j<n;j++)
            {
                G.set(i,m-n+j,H.get(j,i));
            }
        }
    }
    public String encode(String s)
    {
        for (;s.length()%G.m!=0;s="0"+s);
        String str="";
        String[] in=new String[s.length()/G.m];
        for (int i=0;i<in.length;in[i]="",i++);
        for (int i=0;i<s.length();in[i/G.m]+=s.charAt(i),i++);
        //String[] out=new String[in.length];
        double[] ci=new double[G.m];
        for (int i=0;i<in.length;i++)
        {
            //out[i]="";
            for (int j=0;j<ci.length;j++)
            {
                ci[j]=in[i].charAt(j);
            }
            Matrix mi=new Matrix(new double[][]{ci});
            Matrix mo=Matrix.mul(mi,G);
            for (int j=0;j<G.n;j++)
            {
                str+=((int)mo.get(0,j)&1);
            }
        }
        return str;
    }
    public String decode(String s)
    {
        String str="";
        for (;s.length()%G.n!=0;s="0"+s);
        String[] in=new String[s.length()/G.n];
        for (int i=0;i<in.length;in[i]="",i++);
        for (int i=0;i<s.length();in[i/G.n]+=s.charAt(i),i++);
        int[] c=new int[G.n];
        for (int i=0;i<in.length;i++)
        {
            for (int j=0;j<c.length;j++)
            {
                c[j]=in[i].charAt(j)&1;
                //c[j]=Integer.parseInt(""+in[i].charAt(j));
            }
            int[] cp=check(c);
            int k=-1;
            for (int x=0;x<H.n;x++)
            {
                boolean f=true;
                for (int y=0;y<H.m;y++)
                {
                    if ((int)H.get(y,x)!=cp[y])
                    {
                        f=false;
                        break;
                    }
                }
                if (f)
                {
                    k=x;
                }
            }
            if (k>-1)
            {
                c[k]=(c[k]+1)&1;
                cp=check(c);
                boolean f=false;
                for (int j=0;j<cp.length;j++)
                {
                    if (cp[j]>0)
                    {
                        f=true;
                        break;
                    }
                }
                if (f)
                {
                    c[k]=2;
                }
            }
            for (int j=0;j<G.m;j++)
            {
                str+=c[j];
            }
        }
        return str;
    }
    public int[] check(int[] c)
    {
        int[] o=null;
        Matrix mi=new Matrix(c.length,1);
        for (int i=0;i<c.length;i++)
        {
            mi.set(i,0,c[i]);
        }
        Matrix m2=Matrix.mul(H,mi);
        o=new int[m2.m];
        for (int i=0;i<o.length;i++)
        {
            o[i]=(int)m2.get(i,0)&1;
        }
        return o;
    }
    
    public static void main(String[] args)
    {
        Hamming h=new Hamming(3);
        String msg="ABCD";
        String e=h.encodeFromUnicode(msg);
        String d=h.decodeToUnicode(e);
        System.out.println("Message: "+msg);
        System.out.println("Encode: "+e);
        System.out.println("Decode: "+d);
    }
}
