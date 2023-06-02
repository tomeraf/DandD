import java.util.LinkedList;

public class Rogue extends Player {
    protected int energyPool;
    protected int energyRemaining;
    protected int energyCost;

    public Rogue(int X, int Y,int EnergyCost,int HealthPool,int AttackPoints,int DefencePoints,String Name) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        energyPool = 100;
        energyRemaining =100;
        energyCost = EnergyCost;
    }

    public int GetResourcePool() {
        return energyPool;
    }

    public void SetResourcePool(int value) {
        energyPool = value;
    }

    public int GetResourceRemaining() {
        return energyRemaining;
    }

    public void SetResourceRemaining(int value) {
        energyRemaining = value;
    }

    public String cast() {
        if (energyRemaining >= energyCost) {
            energyRemaining -= Math.max(0, energyRemaining - energyCost);
            //need to implemet the hit enemy, maybe in the UI

            return "i have only 1 fan of my Knives,he is OnlyFan of Knives";
        }
        return "fail,not enough mana";
    }
    public boolean LVLUP(){
        if(this.LVLUP()) {
            energyRemaining = 100;
            attackPoints+=3*LVL;
            return true;
        }
        return false;
    }
    public void tick(LinkedList<Enemy> e){
        {
            energyRemaining=Math.min(energyRemaining+10,100);
            powerRefresh(e);
        }
    }

    public String toString(){
        String s=super.toString();
        s+="Energy: "+energyRemaining+"/"+energyRemaining+"  ";
        s+="Fan of Knives cost: "+energyCost+"  ";
        return s;
    }
}

