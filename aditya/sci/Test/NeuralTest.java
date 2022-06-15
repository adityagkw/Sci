package aditya.sci.Test;
import aditya.sci.maths.*;
public class NeuralTest
{
    public static void main(String[] args)
    {
        NeuralNetwork nn=new NeuralNetwork();
        nn.addLayer(new NeuralLayer(2));
        nn.addLayer(new NeuralLayer(2));
        nn.addLayer(new NeuralLayer(1));
        nn.addTraining(new double[]{0,0},new double[]{0});
        nn.addTraining(new double[]{0,1},new double[]{1});
        nn.addTraining(new double[]{1,0},new double[]{1});
        nn.addTraining(new double[]{1,1},new double[]{0});
        /*for (int i=1;i<100;i++)
        {
            nn.addTraining(new double[]{i},new double[]{i});
        }*/
        for (int i=0;i<100;i++)
        {
            nn.train();
            nn.trainrate/=1.01;
        }
        System.out.println(nn.run(new double[]{1,0})[0]);
    }
}
