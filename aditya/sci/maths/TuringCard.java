package aditya.sci.maths;
public class TuringCard
{
    int number;
    int change;
    int direction;
    int card0;
    int card1;
    public TuringCard(int number,String data)
    {
        this.number=number;
        String[] ins=data.trim().split(",");
        change=ins[0].trim().equals("1")?1:0;
        change+=ins[3].trim().equals("1")?2:0;
        direction=ins[1].trim().equals("1")?1:0;
        direction+=ins[4].trim().equals("1")?2:0;
        card0=Integer.parseInt(ins[2]);
        card1=Integer.parseInt(ins[5]);
    }
    public void setNumber(int number)
    {
        this.number=number;
    }
    public int getNumber()
    {
        return number;
    }
    public void load(String data)
    {
        String[] ins=data.trim().split(",");
        change=ins[0].trim().equals("1")?1:0;
        change+=ins[3].trim().equals("1")?2:0;
        direction=ins[1].trim().equals("1")?1:0;
        direction+=ins[4].trim().equals("1")?2:0;
        card0=Integer.parseInt(ins[2]);
        card1=Integer.parseInt(ins[5]);
    }
    public String data()
    {
        String ret="";
        ret+=(change%2)+",";
        ret+=(direction%2)+",";
        ret+=card0+",";
        ret+=(change/2)+",";
        ret+=(direction/2)+",";
        ret+=card1;
        return ret;
    }
}
