package aditya.sci.maths;
import java.util.*;
public class NeuralLayer
{
    public NeuralLayer prev,next;
    public int neurons;
    public Neuron[] neuron;
    public double[][] weight;
    public double[] bias;
    public NeuralNetwork parent;
    double[] error;
    double[] x;
    public NeuralLayer(int neurons)
    {
        this.neurons=neurons;
        neuron=new Neuron[neurons];
        for (int i=0;i<neurons;i++)
        {
            neuron[i]=new Neuron();
        }
    }
    public void setWB()
    {
        if (next!=null)
        {
            if (weight==null || weight.length!=next.neurons)
            {
                weight=new double[next.neurons][neurons];
                bias=new double[next.neurons];
                randomizeWB();
            }
        }
    }
    public void randomizeWB()
    {
        for (int i=0;i<weight.length;i++)
        {
            bias[i]=random();
            for (int j=0;j<weight[i].length;j++)
            {
                weight[i][j]=random();
            }
        }
    }
    public void run()
    {
        if (next!=null)
        {
            setWB();
            x=new double[weight.length];
            for (int i=0;i<weight.length;i++)
            {
                x[i]=0;
                for (int j=0;j<weight[i].length;j++)
                {
                    x[i]+=neuron[j].activation*weight[i][j];
                }
                x[i]+=bias[i];
                next.neuron[i].activation=activation(x[i]);
            }
        }
    }
    public void train()
    {
        double[] be=new double[bias.length];
        double[][] we=new double[weight.length][weight[0].length];
        for (int i=0;i<next.neurons;i++)
        {
            be[i]=next.error[i]*dactivation(x[i]);
            for (int j=0;j<neurons;j++)
            {
                we[i][j]=be[i]*neuron[j].activation;
                error[j]+=weight[i][j]*be[i];
            }
        }
        for (int i=0;i<next.neurons;i++)
        {
            //bias[i]-=parent.trainrate*parent.error/be[i];
            bias[i]-=parent.trainrate*be[i];
            for (int j=0;j<neurons;j++)
            {
                //weight[i][j]-=parent.trainrate*parent.error/we[i][j];
                weight[i][j]-=parent.trainrate*we[i][j];
                //System.out.println(weight[i][j]+" "+bias[i]);
            }
        }
    }
    public double random()
    {
        return 2*Math.random()-1;
    }
    public double activation(double x)
    {
        return 1/(1+Math.exp(-x));
        //return x;
    }
    public double dactivation(double x)
    {
        return (activation(x+parent.dx)-activation(x))/parent.dx;
    }
}
