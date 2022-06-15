package aditya.sci.maths;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class GameOfLife extends JPanel
{
    boolean[][] board;
    public GameOfLife(boolean[][] board)
    {
        super();
        this.board=board;
    }
    public GameOfLife(int width,int height)
    {
        this(new boolean[height][width]);
    }
    public boolean check(int y,int x)
    {
        int c=0;
        for (int i=-1;i<=1;i++)
        {
            for (int j=-1;j<=1;j++)
            {
                int cy=(y+i+board.length)%board.length;
                int cx=(x+j+board[cy].length)%board[cy].length;
                if (board[cy][cx])
                    c++;
            }
        }
        if (board[y][x] && (c==3 || c==4))
            return true;
        else if (!board[y][x] && c==3)
            return true;
        return false;
    }
    public void update()
    {
        boolean[][] next=new boolean[board.length][];
        for (int i=0;i<next.length;next[i]=new boolean[board[i].length],i++);
        for (int i=0;i<next.length;i++)
        {
            for (int j=0;j<next[i].length;j++)
            {
                next[i][j]=check(i,j);
            }
        }
        board=next;
    }
    String name=null;
    public void save(String fn)
    {
        try
        {
            FileOutputStream fos=new FileOutputStream(fn);
            for (int i=0;i<board.length;i++)
            {
                for (int j=0;j<board[i].length;j++)
                {
                    fos.write(board[i][j]?'1':'0');
                }
                fos.write('\n');
                fos.flush();
            }
            fos.close();
        }catch(Exception e){}
        name=fn;
    }
    public void open(String fn)
    {
        try
        {
            FileInputStream fis=new FileInputStream(fn);
            String b="";
            int bv=0;
            while ((bv=fis.read())!=-1)
            {
                b+=(char)bv;
            }
            String[] bl=b.split("\n");
            board=new boolean[bl.length][];
            for (int i=0;i<board.length;i++)
            {
                board[i]=new boolean[bl[i].length()];
                for (int j=0;j<board[i].length;j++)
                {
                    board[i][j]=bl[i].charAt(j)=='1';
                }
            }
        }catch(Exception e){}
        name=fn;
    }
    
    int sx=0,sy=0;
    public void paint(Graphics g)
    {
        int width=(int)getWidth();
        int height=(int)getHeight();
        g.setColor(Color.BLACK);
        int cheight=height/board.length;
        for (int i=0;i<board.length;i++)
        {
            int cwidth=width/board[i].length;
            for (int j=0;j<board[i].length;j++)
            {
                if (board[i][j])
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(j*cwidth,i*cheight,cwidth,cheight);
                }
                if (i==sy && j==sx)
                {
                    g.setColor(Color.BLUE);
                    g.drawRect(j*cwidth,i*cheight,cwidth,cheight);
                }
            }
        }
    }
    float fps=10f;
    boolean play=true;
    public static void main(String[] args)
    {
        if (args.length==1)
        {
            GameOfLife gof=new GameOfLife(0,0);
            gof.open(args[0]);
            gof.run();
            return;
        }
        int w=100,h=100;
        if (args.length==2)
        {
            h=Integer.parseInt(args[0]);
            w=Integer.parseInt(args[1]);
        }
        new GameOfLife(h,w).run();
    }
    public void run()
    {
        
        JFrame f=new JFrame("Game of Life");
        f.setSize(500,500);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //f.setFocusable(true);
        GameOfLife gof=this;
        //board[4][3]=true;
        //board[4][4]=true;
        //board[4][5]=true;
        //board[3][5]=true;
        //board[2][4]=true;
        f.add(gof,BorderLayout.CENTER);
        JPanel bp=new JPanel();
        SpringLayout bpl=new SpringLayout();
        bp.setLayout(bpl);
        f.add(bp,BorderLayout.PAGE_END);
        JButton playb=new JButton("\u23ef");
        playb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                play=!play;
            }
        });
        bp.add(playb);
        bpl.putConstraint(SpringLayout.WEST,playb,5,SpringLayout.WEST,bp);
        bpl.putConstraint(SpringLayout.NORTH,playb,5,SpringLayout.NORTH,bp);
        JButton stepb=new JButton("\u25b6");
        stepb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gof.update();
                f.repaint();
                f.revalidate();
            }
        });
        bp.add(stepb);
        bpl.putConstraint(SpringLayout.WEST,stepb,5,SpringLayout.EAST,playb);
        bpl.putConstraint(SpringLayout.NORTH,stepb,5,SpringLayout.NORTH,bp);
        JLabel fpsl=new JLabel("FPS:");
        bp.add(fpsl);
        bpl.putConstraint(SpringLayout.WEST,fpsl,5,SpringLayout.EAST,stepb);
        bpl.putConstraint(SpringLayout.NORTH,fpsl,5,SpringLayout.NORTH,bp);
        JTextField fpst=new JTextField(""+fps);
        bp.add(fpst);
        bpl.putConstraint(SpringLayout.WEST,fpst,5,SpringLayout.EAST,fpsl);
        bpl.putConstraint(SpringLayout.NORTH,fpst,5,SpringLayout.NORTH,bp);
        
        bpl.putConstraint(SpringLayout.EAST,bp,5,SpringLayout.EAST,fpst);
        bpl.putConstraint(SpringLayout.SOUTH,bp,5,SpringLayout.SOUTH,playb);
        
        JMenuBar mb=new JMenuBar();
        JMenu fm=new JMenu("File");
        JMenuItem newm=new JMenuItem("New");
        newm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("New");
                showNew();
            }
        });
        fm.add(newm);
        JMenuItem openm=new JMenuItem("Open");
        openm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("Open");
                showOpen(f);
            }
        });
        fm.add(openm);
        JMenuItem savem=new JMenuItem("Save");
        savem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("Save");
                showSave(f);
            }
        });
        fm.add(savem);
        JMenuItem saveasm=new JMenuItem("Save As");
        saveasm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("Save As");
                showSaveAs(f);
            }
        });
        fm.add(saveasm);
        mb.add(fm);
        f.add(mb,BorderLayout.PAGE_START);
        //f.pack();
        f.setVisible(true);
        
        KeyListener kl=new KeyListener()
        {
            public void keyReleased(KeyEvent e){}
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode()==KeyEvent.VK_A)
                {
                    sx=(sx-1+board[sy].length)%board[sy].length;
                }
                else if (e.getKeyCode()==KeyEvent.VK_D)
                {
                    sx=(sx+1)%board[sy].length;
                }
                if (e.getKeyCode()==KeyEvent.VK_W)
                {
                    sy=(sy-1+board.length)%board.length;
                }
                else if (e.getKeyCode()==KeyEvent.VK_S)
                {
                    sy=(sy+1)%board.length;
                }
                if (e.getKeyCode()==KeyEvent.VK_P)
                {
                    board[sy][sx]=!board[sy][sx];
                }
                if (e.getKeyCode()==KeyEvent.VK_I)
                {
                    open("game of life.txt");
                }
                if (e.getKeyCode()==KeyEvent.VK_O)
                {
                    save("game of life.txt");
                }
            }
            public void keyTyped(KeyEvent e){}
        };
        playb.addKeyListener(kl);
        stepb.addKeyListener(kl);
        
        Thread t=new Thread()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        fps=Float.parseFloat(fpst.getText());
                        if (fps<0)
                        {
                            fps=-fps;
                            fpst.setText(""+fps);
                        }
                    }catch(Exception e){fpst.setText(""+fps);}
                    try
                    {
                        if (fps>=1 && play)
                        {
                            Thread.sleep((int)(1000/fps));
                            gof.update();
                            f.repaint();
                            f.revalidate();
                        }
                        else
                        {
                            Thread.sleep((int)(1000/60));
                            f.repaint();
                            f.revalidate();
                        }
                    }catch(Exception e){}
                }
            }
        };
        t.start();
    }
    
    public void showNew()
    {
        //for (int i=0;i<50;ts+=" ",i++);
        JFrame f=new JFrame("New");
        f.setSize(300,150);
        SpringLayout sl=new SpringLayout();
        f.setLayout(sl);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel wl=new JLabel("Width:");
        f.add(wl);
        sl.putConstraint(SpringLayout.WEST,wl,5,SpringLayout.WEST,f);
        sl.putConstraint(SpringLayout.NORTH,wl,5,SpringLayout.NORTH,f);
        JTextField wt=new JTextField(""+board[0].length);
        wt.setPreferredSize(new Dimension(100,20));
        f.add(wt);
        sl.putConstraint(SpringLayout.WEST,wt,5,SpringLayout.EAST,wl);
        sl.putConstraint(SpringLayout.NORTH,wt,5,SpringLayout.NORTH,f);
        JLabel hl=new JLabel("Height:");
        f.add(hl);
        sl.putConstraint(SpringLayout.WEST,hl,5,SpringLayout.WEST,f);
        sl.putConstraint(SpringLayout.NORTH,hl,5,SpringLayout.SOUTH,wl);
        JTextField ht=new JTextField(""+board.length);
        ht.setPreferredSize(new Dimension(100,20));
        f.add(ht);
        sl.putConstraint(SpringLayout.WEST,ht,5,SpringLayout.EAST,wl);
        sl.putConstraint(SpringLayout.NORTH,ht,5,SpringLayout.SOUTH,wl);
        JButton createb=new JButton("Create");
        createb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int w=Integer.parseInt(wt.getText());
                    int h=Integer.parseInt(ht.getText());
                    GameOfLife.main(new String[]{""+w,""+h});
                    f.dispose();
                }catch(Exception ex){}
            }
        });
        f.add(createb);
        sl.putConstraint(SpringLayout.WEST,createb,5,SpringLayout.WEST,f);
        sl.putConstraint(SpringLayout.NORTH,createb,5,SpringLayout.SOUTH,hl);
        
        //sl.putConstraint(SpringLayout.EAST,f,5,SpringLayout.EAST,wl);
        //sl.putConstraint(SpringLayout.SOUTH,f,5,SpringLayout.SOUTH,wl);
        //f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
    public void showOpen(JFrame f)
    {
        JFileChooser fc=new JFileChooser();
        int r=fc.showOpenDialog(f);
        if (r==JFileChooser.APPROVE_OPTION)
        {
            GameOfLife.main(new String[]{fc.getSelectedFile().getName()});
        }
    }
    public void showSave(JFrame f)
    {
        if (name==null)
        {
            showSaveAs(f);
        }
        else
        {
            save(name);
        }
    }
    public void showSaveAs(JFrame f)
    {
        JFileChooser fc=new JFileChooser();
        int r=fc.showSaveDialog(f);
        if (r==JFileChooser.APPROVE_OPTION)
        {
            save(fc.getSelectedFile().getName());
        }
    }
}
