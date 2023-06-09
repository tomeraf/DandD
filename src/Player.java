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
    public String LVLUP(){
        String send="";
            send+="Level Up!\n new Level:";
            EXP-=LVL*50;
            LVL++;
            send+=LVL+"stats gained:\n";
            healthPool+=10*LVL;
            send+="Max Health:"+ 10*LVL+"\n";
            healthAmount=healthPool;
            attackPoints+=4*LVL;
            send+="Attack points:"+ 4*LVL+"\n";
            defencePoints+=LVL;
            send+="Defence points:"+ LVL+"\n";
        return send;
    }
    @Override
    public String toString(){
        String s=super.toString();
        s+="EXP: "+ EXP+"  ";
        s+="LVL: "+ LVL+"  ";
        return s+"\n";
    }

    public String move(){return "";};

    protected boolean didLVLUP(){
        return EXP>=50*LVL;
    }

    abstract public String tick(LinkedList<Enemy> e);


}
