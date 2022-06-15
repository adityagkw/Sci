package aditya.sci.maths;
import java.util.*;
public class NeuralNetwork
{
    public ArrayList<NeuralLayer> layer=new ArrayList<NeuralLayer>();
    public ArrayList<double[]> traininput=new ArrayList<double[]>();
    public ArrayList<double[]> trainoutput=new ArrayList<double[]>();
    public double dx=0.0001;
    public double trainrate=0.01;
    double error;
    private int ct=0;
    public NeuralNetwork()
    {
        
    }
    public void addLayer(NeuralLayer layer)
    {
        if (this.layer.size()>0)
        {
            NeuralLayer nl=this.layer.get(this.layer.size()-1);
            layer.prev=nl;
            nl.next=layer;
        }
        this.layer.add(layer);
        layer.parent=this;
    }
    public void addTraining(double[] in,double[] out)
    {
        traininput.add(in);
        trainoutput.add(out);
    }
    public double[] run(double[] in)
    {
        double[] out=null;
        Object[] nlo=layer.toArray();
        NeuralLayer[] nl=new NeuralLayer[nlo.length];
        for (int i=0;i<nl.length;nl[i]=(NeuralLayer)nlo[i],i++);
        if (nl[0].neurons!=in.length)
            return null;
        for (int i=0;i<nl[0].neurons;i++)
        {
            nl[0].neuron[i].activation=in[i];
        }
        for (int i=0;i<nl.length-1;i++)
        {
            nl[i].run();
        }
        out=new double[nl[nl.length-1].neurons];
        for (int i=0;i<out.length;i++)
        {
            out[i]=nl[nl.length-1].neuron[i].activation;
        }
        return out;
    }
    public void train()
    {
        ct=(int)(Math.random()*traininput.size());
        double[] ti=(double[])traininput.get(ct);
        double[] to=(double[])trainoutput.get(ct);
        Object[] nlo=layer.toArray();
        NeuralLayer[] nl=new NeuralLayer[nlo.length];
        for (int i=0;i<nl.length;nl[i]=(NeuralLayer)nlo[i],i++);
        double[] o=run(ti);
        error=loss(o,to);
        
        //error=0;
        //for(int i=0;i<o.length;error+=Math.pow(o[i]-to[i],2),i++);
        int s=nl.length;
        for (int i=0;i<s;nl[i].error=new double[nl[i].neurons],i++);
        for (int i=0;i<nl[s-1].neurons;i++)
        {
            //double a=o[i]-to[i];
            double[] o2=o.clone();
            o2[i]=o2[i]+dx;
            
            double a=to[i]-o[i];
            double te=2*a+error-a*a;
            //nl[s-1].error[i]=2*a+error-a*a;
            nl[s-1].error[i]=(loss(o2,to)-error)/dx;
            System.out.println(te-nl[s-1].error[i]);
        }
        for (int i=s-2;i>=0;i--)
        {
            nl[i].train();
        }
        ct++;
        ct%=traininput.size();
        //trainrate/=1.01;
    }
    public double loss(double[] out,double[] train)
    {
        double l=0;
        for (int i=0;i<out.length;i++)
        {
            l-=Math.pow(out[i]-train[i],2);
            //l-=train[i]*Math.log(out[i])+(1-train[i])*Math.log(1-out[i]);
        }
        return l;
    }
}
