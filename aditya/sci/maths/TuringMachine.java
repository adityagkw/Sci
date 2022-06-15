package aditya.sci.maths;
import java.util.*;
public class TuringMachine
{
    ArrayList<TuringCard> card=new ArrayList<TuringCard>();
    String data="0";
    int cc=1,cp=0;
    boolean halt=false;
    public TuringMachine()
    {
        
    }
    public TuringMachine(String data)
    {
        this.data=data;
    }
    public void addCard(TuringCard card)
    {
        this.card.add(card);
    }
    public void removeCard(TuringCard card)
    {
        this.card.remove(card);
    }
    public TuringCard getCardByIndex(int i)
    {
        return card.get(i);
    }
    public TuringCard getCardByNumber(int i)
    {
        for (int a=0;a<card.size();a++)
        {
            if (card.get(a).getNumber()==i)
                return card.get(a);
        }
        return null;
    }
    public void runCard(TuringCard card)
    {
        int r=0,d=0;
        if (data.charAt(cp)=='1')
        {
            r=card.change/2;
            d=card.direction/2==0?-1:1;
            cc=card.card1;
        }
        else
        {
            r=card.change%2;
            d=card.direction%2==0?-1:1;
            cc=card.card0;
        }
        data=data.substring(0,cp)+r+data.substring(cp+1);
        cp+=d;
        if (cp<0)
        {
            data="0"+data;
            cp++;
        }
        else if(cp>=data.length())
        {
            data+="0";
        }
        if (cc==0)
        {
            halt=true;
        }
    }
    public String update()
    {
        TuringCard tc=getCardByNumber(cc);
        runCard(tc);
        return data;
    }
    public static void main(String[] args)
    {
        TuringMachine tm=new TuringMachine("1");
        TuringCard tc=new TuringCard(1,"1,1,0,0,0,1");
        tm.addCard(tc);
        while (!tm.halt)
        {
            System.out.println(tm.update());
        }
    }
}
