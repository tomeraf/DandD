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
        visionRange=2;
    }
    @Override
    public int GetResourcePool() {
        return energyPool;
    }
    @Override
    public void SetResourcePool(int value) {
        energyPool = value;
    }
    @Override
    public int GetResourceRemaining() {
        return energyRemaining;
    }
    @Override
    public void SetResourceRemaining(int value) {
        energyRemaining = value;
    }

    @Override
    public String rest(){
        String messege="$"+super.rest();
        energyRemaining=Math.min(energyRemaining+10,100);
        messege+="Energy gained: 20\n$";
        return messege;
    }

    @Override
    public Pair<LinkedList<Unit>,String> castAbility(Player p) {
        LinkedList<Unit> killed = new LinkedList<>();
        if (energyRemaining >= energyCost) {
            energyRemaining -= energyCost+10;
            for(Enemy e:power) {
                e.attacked(attackPoints);
                if (e.isDead()) {
                    this.EXPGain(e.EXPgain);
                    power.remove(e);
                    killed.addFirst(e);
                }
            }
            return new Pair<>(killed, "i have only 1 fan of my Knives,he is OnlyFan of Knives");
        }
        return new Pair<>(killed, "fail,not enough energy");
    }
    @Override
    public String LVLUP(){
        String messege="$";
        messege+=super.LVLUP();
        messege+="for being a Rogue, extra stats gain:\n";
        energyRemaining = 100;
        messege+="Current Energy -  100\n";
        attackPoints+=3*LVL;
        messege+="Attack Points - "+3*LVL +"\n";
        return messege+'$';
    }
    @Override
    public String tick(LinkedList<Enemy> e){
        {
            energyRemaining=Math.min(energyRemaining+10,100);
            powerRefresh(e);
            String Messege="";
            while(didLVLUP())
                Messege+=LVLUP();
            return Messege;
        }
    }

    @Override
    public String toString(){
        String messege=super.toString();
        messege+="Energy: "+energyRemaining+"/"+energyPool+"  ";
        messege+="Fan of Knives cost: "+energyCost;
        return messege+"\n";
    }
}

