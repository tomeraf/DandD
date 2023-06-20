package DaD;

abstract public class Unit extends Tiles {
    protected String name;
    protected double healthPool;
    protected double healthAmount;
    protected int attackPoints;
    protected  int defencePoints;
    public Unit(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints,String Name){
        super(X,Y);
        healthPool=HealthPool;
        healthAmount=HealthPool;
        attackPoints=AttackPoints;
        defencePoints=DefencePoints;
        name=Name;
    }
    public double GetHealthPool(){return healthPool;}
    public void SetHealthPool(int value){healthPool=value;}
    public double GetHealthAmount(){return healthAmount;}
    public void SetHealthAmount(int value){healthAmount=value;}
    public int GetAttackPoints(){return attackPoints;}
    public void SetAttackPoints(int value){attackPoints=value;}
    public int GetDefencePoints(){return defencePoints;}
    public void SetDefencePoints(int value){defencePoints=value;}
    public void movement(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double reduceHealth(double amount)
    {
        healthAmount=Math.max(0,healthAmount-amount);
        return healthAmount;
    }

    public double increaseHealth(double amount)
    {
        healthAmount=Math.min(healthPool,healthAmount+amount);
        return healthAmount;
    }
    public boolean isDead(){return healthAmount==0;}

    @Override
    public String toString(){
        String message = "";
        message+="HP: "+ healthAmount+"/"+healthPool+"  ";
        message+="Attack: "+ attackPoints+"  ";
        message+="Defence: "+ defencePoints+"  ";
        return message+"\n";
    }

    protected double range(Unit u){
        return Math.sqrt(Math.pow(x-u.x,2)+Math.pow(y-u.y,2));
    }
    protected boolean isInRange(Unit u,double range){
        return range(u)<range;
    }
    abstract public Pair<Unit,String> attack(Unit u);
    abstract public Pair<Unit,String> attack(Enemy e);
    abstract public Pair<Unit,String> attack(Player p);
    abstract public Pair<Unit,String> attack(Wall w);
    abstract public Pair<Unit,String> attack(Empty empty);
    abstract public Pair<Unit,String> attack(Tiles t);
}
