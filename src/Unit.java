abstract public class Unit extends Tiles{
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected  int defencePoints;
    public Unit(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints){
        super(X,Y);
        healthPool=HealthPool;
        healthAmount=HealthPool;
        attackPoints=AttackPoints;
        defencePoints=DefencePoints;
    }
    public int GetHealthPool(){return healthPool;}
    public void SetHealthPool(int value){healthPool=value;}
    public int GetHealthAmount(){return healthAmount;}
    public void SetHealthAmount(int value){healthAmount=value;}
    public int GetAttackPoints(){return attackPoints;}
    public void SetAttackPoints(int value){attackPoints=value;}
    public int GetDefencePoints(){return defencePoints;}
    public void SetDefencePoints(int value){defencePoints=value;}

    abstract public void move();

    abstract public void tick();

    public double range(Unit u){
        return Math.sqrt(Math.pow(x-u.x,2)+Math.pow(y-u.y,2));
    }
    public boolean isInRange(Unit u,double range){
        return range(u)<range;
    }
}
