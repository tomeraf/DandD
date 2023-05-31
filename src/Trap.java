public class Trap extends Enemy{
    protected boolean visibility;
    protected  int visibilityTime;
    protected  int invisibilityTime;
    protected int ticksCount;
    public Trap(int X,int Y,char Sign,int EXPGain,int HealthPool,int AttackPoints,int DefencePoints,String Name,int VisibilityTime,int InvisibilityTime){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        sign=Sign;
        EXPgain=EXPGain;
        visibility=false;
        visibilityTime=VisibilityTime;
        ticksCount=invisibilityTime;
        invisibilityTime=InvisibilityTime;
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
