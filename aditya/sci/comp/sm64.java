package aditya.sci.comp;
public class sm64
{
    public short rng(short input)
    {
        if (Short.toUnsignedInt(input)==0x560A) input=0;
        short S0=(short)(((byte)input)<<8);
        S0=(short)(S0^input);
        input=(short)(((S0&0xFF)<<8)|((S0&0xFF00)>>8));
        S0=(short)(((byte)S0<<1)^input);
        short S1=(short)((S0>>1)^0xFF80);
        if ((S0 & 1) == 0) {
            if (Short.toUnsignedInt(S1)==0xAA55) input=0;
            else input=(short)(S1^0x1FF4);}
        else input=(short)(S1^0x8180);
        return input;
    }
    public int getUnsignedRNG(int input)
    {
        return Short.toUnsignedInt(rng((short)input));
    }
}
