package aditya.sci.comp;
public class Test
{
    public static void main(String[] args)
    {
        List<String> l=new List<String>();
        l.add("10");
        l.add("20");
        l.add("Hello");
        l.add("World!!!");
        System.out.println("Size: "+l.size());
        System.out.println("Index of 10: "+l.getIndex("10"));
        System.out.println("Index of 20: "+l.getIndex("20"));
        System.out.println("Index of 30: "+l.getIndex("30"));
        System.out.println(l);
        String[] li=(String[])l.toArray(String.class);
    }
}
