abstract public class Unit extends Tiles{
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

    abstract public String move();
    @Override
    public String toString(){
        String messege = "";
        messege+="HP: "+ healthAmount+"/"+healthPool+"  ";
        messege+="Attack: "+ attackPoints+"  ";
        messege+="Defence: "+ defencePoints+"  ";
        return messege;
    }

    public double range(Unit u){
        return Math.sqrt(Math.pow(x-u.x,2)+Math.pow(y-u.y,2));
    }
    public boolean isInRange(Unit u,double range){
        return range(u)<range;
    }
}
