package aditya.sci.maths;
import java.util.*;
public class KMeans
{
    class KMeansPoint
    {
        double[] position;
        int k;
        public KMeansPoint(double[] pos)
        {
            position=pos;
        }
        public KMeansPoint clone()
        {
            double[] pos=new double[position.length];
            for (int i=0;i<pos.length;pos[i]=position[i],i++);
            KMeansPoint p=new KMeansPoint(pos);
            p.k=k;
            return p;
        }
        public double distance(KMeansPoint p)
        {
            double d=0;
            for (int i=0;i<position.length;i++)
            {
                double l=p.position[i]-position[i];
                d+=l*l;
            }
            d=Math.sqrt(d);
            return d;
        }
        public void calcK(KMeansPoint[] p)
        {
            k=0;
            double d=distance(p[0]);
            for (int i=1;i<p.length;i++)
            {
                double d2=distance(p[i]);
                if (d2<d)
                {
                    k=i;
                    d=d2;
                }
            }
        }
        public void moveMean(KMeansPoint[] p)
        {
            int n=0;
            //position=new double[position.length];
            for (int i=0;i<position.length;position[i]=0,i++);
            for (int i=0;i<p.length;i++)
            {
                if (p[i].k==k)
                {
                    n++;
                    for (int j=0;j<position.length;position[j]+=p[i].position[j],j++);
                }
            }
            for (int i=0;i<position.length;position[i]=position[i]/n,i++);
        }
    }
    ArrayList<KMeansPoint> data=new ArrayList<KMeansPoint>();
    KMeansPoint[] mean;
    int k;
    public KMeans(int k)
    {
        this.k=k;
        mean=new KMeansPoint[k];
    }
    public void fit(double... point)
    {
        data.add(new KMeansPoint(point));
    }
    public void fit(double[][] points)
    {
        for (int i=0;i<points.length;i++)
        {
            data.add(new KMeansPoint(points[i]));
        }
    }
    public void train(int iterations)
    {
        KMeansPoint[] point=new KMeansPoint[data.size()];
        for (int i=0;i<point.length;point[i]=data.get(i),i++);
        for (int i=0;i<mean.length;i++)
        {
            mean[i]=point[(int)(Math.random()*point.length)].clone();
            mean[i].k=i;
            for (int j=0;j<i;j++)
            {
                boolean f=true;
                for (int k=0;k<mean.length;k++)
                {
                    if (mean[j].position[k]!=mean[i].position[j])
                    {
                        f=false;
                        break;
                    }
                }
                if (f)
                {
                    i--;
                    break;
                }
            }
            //System.out.println(i+" "+string(mean[i].position));
        }
        for (int i=0;i<iterations;i++)
        {
            for (int j=0;j<point.length;j++)
            {
                point[j].calcK(mean);
            }
            for (int j=0;j<mean.length;j++)
            {
                mean[j].moveMean(point);
                //System.out.println(i+" "+j+" "+string(mean[j].position));
            }
        }
    }
    public int predict(double... point)
    {
        KMeansPoint p=new KMeansPoint(point);
        p.calcK(mean);
        return p.k;
    }
    private String string(double[] point)
    {
        String s="(";
        for (int i=0;i<point.length;i++)
        {
            s+=(i==0?"":",")+point[i];
        }
        return s+")";
    }
}
