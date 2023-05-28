abstract public class Unit extends Tiles{
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected  int defencePoints;
    public Unit(int X,int Y){
        super(X,Y);
        healthPool=0;
        healthAmount=0;
        attackPoints=0;
        defencePoints=0;
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
}
