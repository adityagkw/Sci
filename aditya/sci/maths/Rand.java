package aditya.sci.maths;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.lang.reflect.*;
public class Rand extends Random implements Cloneable
{
    private  AtomicLong seed=new AtomicLong();
    public static final long multiplier = 0x5DEECE66DL;
    public static final long addend = 0xBL;
    public static final long mask = (1L << 48) - 1;
    public interface Generator
    {
        public int next(int bits);
    }
    Generator g=(int bits)->super.next(bits);
    public Rand()
    {
        this(seedUniquifier() ^ System.nanoTime());
    }
    public Rand(long seed)
    {
        super(seed);
        this.seed=new AtomicLong();
        this.seed = new AtomicLong(initialScramble(seed));
    }
    public Rand(Random r)
    {
        this(unscrambleSeed(getSeedFromRandom(r)));
    }
    public static final AtomicLong seedUniquifier= new AtomicLong(8682522807148012L);
    public static long seedUniquifier() {
        for (;;) {
            long current = seedUniquifier.get();
            long next = current * 181783497276652981L;
            if (seedUniquifier.compareAndSet(current, next))
                return next;
        }
    }
    public static long initialScramble(long seed) {
        return (seed ^ multiplier) & mask;
    }
    public static long unscrambleSeed(long seed)
    {
        return (seed & mask)^multiplier;
    }
    public void setSeed(long seed)
    {
        super.setSeed(seed);
        try
        {
        this.seed.set(Rand.initialScramble(seed));
        }catch(Exception e){}
    }
    public long getSeed()
    {
        return seed.get();
    }
    public long getUsableSeed()
    {
        return unscrambleSeed(getSeed());
    }
    public int next(int bits)
    {
        long os=seed.get();
        int o=g.next(bits);
        if (os==seed.get())
        {
            long oldseed,nextseed;
            do {
                oldseed = seed.get();
                nextseed = (oldseed * multiplier + addend) & mask;
            } while (!seed.compareAndSet(oldseed, nextseed));
        }
        return o;
    }
    public static long getSeedFromRandom(Random r)
    {
        long s=0;
        try
        {
            Field f=Random.class.getDeclaredField("seed");
            f.setAccessible(true);
            s=((AtomicLong)f.get(r)).get();
        }catch(Exception e){}
        return s;
    }
    public byte nextByte(byte ub)
    {
        return (byte)(nextDouble()*ub);
    }
    public byte nextByte(byte lb,byte ub)
    {
        return (byte)((int)nextByte((byte)(ub-lb))+lb);
    }
    public short nextShort(short ub)
    {
        return (short)(nextDouble()*ub);
    }
    public short nextShort(short lb,short ub)
    {
        return (short)(nextShort((short)(ub-lb))+lb);
    }
    public int nextInt(int ub)
    {
        return (int)(nextDouble()*ub);
    }
    public int nextInt(int lb,int ub)
    {
        return nextInt(ub-lb)+lb;
    }
    public long nextLong(long ub)
    {
        return (long)(nextDouble()*ub);
    }
    public long nextLong(long lb,long ub)
    {
        return nextLong(ub-lb)+lb;
    }
    public float nextFloat(float ub)
    {
        return (float)(nextDouble()*ub);
    }
    public float nextFloat(float lb,float ub)
    {
        return nextFloat(ub-lb)+lb;
    }
    public double nextDouble(double ub)
    {
        return nextDouble()*ub;
    }
    public double nextDouble(double lb,double ub)
    {
        return nextDouble(ub-lb)+lb;
    }
    public char nextChar(char lb,char ub)
    {
        return (char)(nextInt(ub-lb)+lb);
    }
    public <T extends Object> T[] shuffle(T[] in)
    {
        T[] out=(T[])new Object[in.length];
        int[] isa=new int[in.length];
        for (int a=0;a<in.length;a++)
        {
            int i=0;
            boolean f=true;
            while(f)
            {
                i=nextInt(in.length);
                f=false;
                for (int b=0;b<a;b++)
                {
                    if (i==isa[b])
                    {
                        f=true;
                        break;
                    }
                }
            }
            out[a]=in[i];
            isa[a]=i;
        }
        return out;
    }
    public <T> T choose(T[] in)
    {
        return in[nextInt(in.length)];
    }
    
}
