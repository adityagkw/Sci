package aditya.sci.maths;
public class ErrorCorrection
{
    public String encode(String s)
    {
        String str="";
        return str;
    }
    public String decode(String s)
    {
        String str="";
        return null;
    }
    
    public String encodeFromUnicode(String s)
    {
        String in="";
        for (int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            String bc=Integer.toBinaryString(c);
            for (int j=bc.length();j<16;bc="0"+bc,j++);
            in+=bc;
        }
        return encode(in);
    }
    public String decodeToUnicode(String s)
    {
        String out="";
        String in=decode(s);
        for (int i=0;i<in.length()/16;i++)
        {
            out+=(char)Integer.parseInt(in.substring(i*16,(i+1)*16),2);
        }
        return out;
    }
}
