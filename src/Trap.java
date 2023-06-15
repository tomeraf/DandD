public class Trap extends Enemy {
    protected boolean visibility;
    final private double activationRange = 2.0;
    protected  int visibilityTime;
    protected  int invisibilityTime;
    protected int ticksCount;
    protected char trueSign;
    public Trap(int X,int Y,char Sign,int EXPGain,int HealthPool,int AttackPoints,int DefencePoints,String Name,int VisibilityTime,int InvisibilityTime){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        trueSign =Sign;
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

    public Pair<Integer,Integer> move(Player p ){
        ticksCount++;
        if (visibility && ticksCount==visibilityTime){
            visibility = false;
            sign = '.';
            ticksCount=0;
        } else if (!visibility && ticksCount==invisibilityTime){
            visibility = true;
            sign = trueSign;
            ticksCount=0;
        }
        if (this.isInRange(p,activationRange)){
            return new Pair<Integer,Integer>(p.GetX(),p.GetY());
        } else {
            return new Pair<Integer,Integer>(this.GetX(),this.GetY());
        }
    }
}
