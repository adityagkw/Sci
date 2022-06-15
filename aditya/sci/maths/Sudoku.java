package aditya.sci.maths;
public class Sudoku implements Cloneable
{
    int[][] puzzle=new int[9][9];
    int xl,yl,gxl,gyl,m;
    Sudoku original=null;
    public Sudoku()
    {
        xl=9;
        yl=9;
        gxl=3;
        gyl=3;
        m=9;
    }
    public Sudoku(int[][] p)
    {
        this();
        puzzle=p;
        yl=p.length;
        xl=p[0].length;
        gyl=(int)Math.sqrt(yl);
        gxl=(int)Math.sqrt(xl);
        m=yl>xl?yl:xl;
    }
    
    protected void answer(int[][] ans)
    {
        System.out.println(new Sudoku(ans));
        puzzle=ans;
    }
    
    public boolean checkGrid(int x,int y,int n)
    {
        for (int i=0;i<gyl;i++)
        {
            for (int j=0;j<gxl;j++)
            {
                if (puzzle[(y/gyl)*gyl+i][(x/gxl)*gxl+j]==n)
                    return false;
            }
        }
        return true;
    }
    public boolean checkHorizontal(int x,int y,int n)
    {
        for (int i=0;i<puzzle[0].length;i++)
        {
            if (puzzle[y][i]==n)
                return false;
        }
        return true;
    }
    public boolean checkVertical(int x,int y,int n)
    {
        for (int i=0;i<puzzle.length;i++)
        {
            if (puzzle[i][x]==n)
                return false;
        }
        return true;
    }
    public boolean check(int x,int y,int n)
    {
        return checkGrid(x,y,n)&&checkHorizontal(x,y,n)&&checkVertical(x,y,n);
    }
    public void solve()
    {
        //Sudoku o=null;
        for (int y=0;y<puzzle.length;y++)
        {
            for (int x=0;x<puzzle[y].length;x++)
            {
                if (puzzle[y][x]==0)
                {
                    for (int i=1;i<=m;i++)
                    {
                        if (check(x,y,i))
                        {
                            try
                            {
                                Sudoku o=(Sudoku)this.clone();
                                o.puzzle[y][x]=i;
                                //System.out.println("t: "+this);
                                //System.out.println("o: "+o);
                                o.solve();
                            }catch(Exception e){e.printStackTrace();}
                        }
                    }
                    return;
                }
            }
        }
        //System.out.println(this);
        original.answer(this.puzzle);
    }
    public void generate()
    {
        if (yl==0 || xl==0)
        {
            yl=puzzle.length;
            xl=puzzle[0].length;
        }
        puzzle=new int[yl][xl];
        gen();
    }
    private boolean gen()
    {
        for (int y=0;y<yl;y++)
        {
            for (int x=0;x<xl;x++)
            {
                if (puzzle[y][x]==0)
                {
                    int[] n=new int[m];
                    for (int i=0;i<n.length;i++)
                    {
                        n[i]=i+1;
                    }
                    for (int i=0;i<n.length;i++)
                    {
                        int j=(int)(Math.random()*n.length);
                        int t=n[i];
                        n[i]=n[j];
                        n[j]=t;
                    }
                    for (int i=0;i<n.length;i++)
                    {
                        if (check(x,y,n[i]))
                        {
                            Sudoku s=(Sudoku)this.clone();
                            s.puzzle[y][x]=n[i];
                            if (s.gen())
                            {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        //System.out.println(this);
        original.answer(puzzle);
        return true;
    }
    
    public String toString()
    {
        String o="";
        String ind=" ";
        for (int y=0;y<puzzle.length;y++)
        {
            if (y%gyl==0)
            {
                //o+="| ";
                //o+="| - - - | - - - | - - - |\n";
                for (int i=0;i<puzzle[y].length;i++)
                {
                    if (i%gxl==0)
                    {
                        o+="|"+ind;
                    }
                    o+="-"+ind;
                }
                o+="|\n";
            }
            for (int x=0;x<puzzle[y].length;x++)
            {
                if (x%gxl==0)
                {
                    o+="|"+ind;
                }
                o+=puzzle[y][x]+ind;
                
            }
            o+="|\n";
        }
        for (int i=0;i<puzzle[0].length;i++)
        {
            if (i%gxl==0)
            {
                o+="|"+ind;
            }
            o+="-"+ind;
        }
        o+="|\n";
        return o;
    }
    public Sudoku clone()
    {
        Sudoku s=null;
        int[][] p=new int[puzzle.length][puzzle[0].length];
        for (int y=0;y<puzzle.length;y++)
        {
            for (int x=0;x<puzzle[y].length;x++)
            {
                p[y][x]=puzzle[y][x];
            }
        }
        try
        {
            s=this.getClass().getConstructor().newInstance();
            s.puzzle=p;
            s.xl=xl;
            s.yl=yl;
            s.gxl=gxl;
            s.gyl=gyl;
            s.m=m;
            if (original!=null)
            {
                s.original=original;
            }
            else
            {
                s.original=this;
            }
        }catch(Exception e){e.printStackTrace();}
        return s;
    }
    
    public static void main()
    {
        int[][] p=new int[][]{
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}};
        Sudoku s=new Sudoku(p);
        //Sudoku s=new Sudoku(new int[9][9]);
        s.m=9;
        System.out.println("Actual Solution:");
        //s.generate();
        /*for (int i=0;i<30;i++)
        {
            int x=0,y=0;
            do
            {
                x=(int)(Math.random()*9);
                y=(int)(Math.random()*9);
            }while(s.puzzle[y][x]==0);
            s.puzzle[y][x]=0;
        }*/
        System.out.println("Question:");
        System.out.println(s);
        System.out.println("Answer:");
        s.solve();
    }
}
