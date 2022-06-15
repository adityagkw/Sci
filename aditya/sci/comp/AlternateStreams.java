package aditya.sci.comp;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class AlternateStreams
{
    public String[] getAlternateStreams(File f)
    {
        ArrayList<String> al=new ArrayList<String>();
        String cmd="cmd.exe /c dir "+f.getAbsolutePath()+" /r";
        String path=f.getPath();
        path=path.substring(0,path.lastIndexOf("\\")+1);
        //String out="";
        Pattern pat=Pattern.compile("\\s*"+"[0123456789,]+\\s*"+"([^:]+:"+"[^:]+:"+".+)");
        try
        {
            Process p=Runtime.getRuntime().exec(cmd);
            p.waitFor();
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ln="";
            while ((ln=br.readLine())!=null)
            {
                //out+=ln+"\n";
                Matcher m=pat.matcher(ln);
                if (m.matches())
                {
                    //out+=m.group(1)+"\n";
                    //System.out.println(path+m.group(1));
                    al.add(path+m.group(1));
                }
            }
        }catch(Exception e){e.printStackTrace();}
        //System.out.println(cmd);
        //System.out.println(out);
        String[] asa=new String[al.size()];
        for (int i=0;i<asa.length;asa[i]=al.get(i),i++);
        return asa;
    }
    public static void main(String[] args)
    {
        try
        {
            String[] as=new AlternateStreams().getAlternateStreams(new File("test\\2.txt"));
            System.out.println(as[0]);
            File f=new File(as[0]);
            Scanner s=new Scanner(f);
            while (s.hasNextLine())
            {
                System.out.println(s.nextLine());
            }
            s.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
