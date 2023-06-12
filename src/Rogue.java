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
    public String LVLUP(){
        String messege="";
        messege=super.LVLUP();
        messege+="for being a Rogue, extra stats gain:\n";
        energyRemaining = 100;
        messege+="Current Energy: 100\n";
        attackPoints+=3*LVL;
        messege+="Attack Points:"+3*LVL +"\n";
        return messege;
    }
    public String tick(LinkedList<Enemy> e){
        {
            energyRemaining=Math.min(energyRemaining+10,100);
            powerRefresh(e);
            String send="";
            while(didLVLUP())
                send+=LVLUP();
            return send;
        }
    }

    public String toString(){
        String messege=super.toString();
        messege+="Energy: "+energyRemaining+"/"+energyRemaining+"  ";
        messege+="Fan of Knives cost: "+energyCost;
        return messege;
    }
}

