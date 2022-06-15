package aditya.sci.comp;
import java.io.*;
import java.net.*;
import java.util.*;
public class Server
{
    ServerSocket ss;
    int port;
    boolean running;
    public Server(int port)
    {
        this.port=port;
        try
        {
            ss=new ServerSocket(port);
            
        }catch(Exception e){}
    }
    public void start()
    {
        running=true;
        Thread t=new Thread()
        {
            public void run()
            {
                while(running)
                {
                    try
                    {
                        Socket s=ss.accept();
                        startThread(s);
                    }catch(Exception e){}
                }
            }
        };
        t.start();
    }
    public void stop()
    {
        running=false;
    }
    public void startThread(Socket s)
    {
        Thread t=new Thread()
        {
            public void run()
            {
                try
                {
                    InputStream is=s.getInputStream();
                    OutputStream os=s.getOutputStream();
                    Scanner s=new Scanner(is);
                    String line=s.nextLine();
                    while(!line.isEmpty())
                    {
                        System.out.println(line);
                        line=s.nextLine();
                    }
                    Date d=new Date();
                    String response="HTTP/1.1 200 OK\r\n\r\n";
                    response+="<html><body>";
                    response+="<h1>Hello World</h1>";
                    response+="</body></html>";
                    //response+=d;
                    os.write(response.getBytes("UTF-8"));
                    os.flush();
                    os.close();
                    System.out.println("close");
                }catch(Exception e){}
            }
        };
        t.start();
    }
    public static void main(String[] args)
    {
        Server s=new Server(1234);
        s.start();
    }
}
