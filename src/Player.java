import java.util.LinkedList;

abstract public class Player extends Unit {
    final char sign = '@';
    protected int EXP;
    protected int LVL;

    protected int visionRange;

    protected LinkedList<Enemy> power;

    public Player(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints,String Name){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        EXP=0;
        LVL=1;
        visionRange=0;
        power=new LinkedList<Enemy>();
    }
    public void powerRefresh(LinkedList<Enemy> e){
        power=new LinkedList<Enemy>();
        for(Enemy enemy:e)
            if(isInRange(enemy,visionRange))
                power.add(enemy);

    }
    public int GetEXP(){return EXP;}
    public void SetEXP(int value){EXP=value;}

    public int GetLVL(){return LVL;}
    public void SetLVL(int value){LVL=value;}

    abstract public int GetResourcePool();
    abstract void SetResourcePool(int value);

    abstract public int GetResourceRemaining();
    abstract public void SetResourceRemaining(int value);

    public int AddEXP(int amount){
        EXP+=amount;
        return EXP;
    }

    public boolean isDead(){
        return healthAmount==0;
    }

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
    @Override
    public String toString(){
        String s=super.toString();
        s+="EXP: "+ EXP+"  ";
        s+="LVL: "+ LVL+"  ";
        return s;
    }

    public void move(){};



}
