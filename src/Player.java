abstract public class Player extends Unit {
    protected int EXP;
    protected int LVL;

    public Player(int X,int Y){
        super(X,Y);
        sign='@';
        EXP=0;
        LVL=1;
    }
    public int GetEXP(){return EXP;}
    public void SetEXP(int value){EXP=value;}

    public int GetLVL(){return LVL;}
    public void SetLVL(int value){LVL=value;}

    abstract public String cast();
    public boolean LVLUP(){
        if(EXP>=50*LVL)
        {
            EXP-=LVL*50;
            LVL++;
            healthPool+=10*LVL;
            healthAmount=healthPool;
            attackPoints+=4*LVL;
            defencePoints+=LVL;
            return true;
        }
        return false;
    }

    abstract public int GetResourcePool();
    abstract void SetResourcePool(int value);

    abstract public int GetResourceRemaining();
    abstract public void SetResourceRemaining(int value);

    abstract public void tick();
    public void move(){};
}
