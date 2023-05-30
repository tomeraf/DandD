public class Trap extends Enemy{
    protected boolean visibility;
    protected int ticksCount;
    public Trap(int X,int Y,char Sign,int EXPGain,int TicksCount,int HealthPool,int AttackPoints,int DefencePoints,String Name){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        sign=Sign;
        EXPgain=EXPGain;
        visibility=false;
        ticksCount=TicksCount;
    }

    public boolean GetVisibility(){return visibility;}
    public void SetVisibility(boolean value){visibility=value;}

    public int GetTicksCount(){return ticksCount;}
    public void SetTicksCount(int value){ticksCount=value;}

    public void tick(){
        //check for player to attack

    }

    public void move(){}
}
