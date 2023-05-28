abstract public class Trap extends Enemy{
    protected boolean visibility;
    protected int ticksCount;
    public Trap(int X,int Y,char Sign,int EXPGain,int TicksCount){
        super(X,Y);
        sign=Sign;
        EXPgain=EXPGain;
        visibility=false;
        ticksCount=TicksCount;
    }

    public boolean GetVisibility(){return visibility;}
    public void SetVisibility(boolean value){visibility=value;}

    public int GetTicksCount(){return ticksCount;}
    public void SetTicksCount(int value){ticksCount=value;}
}
