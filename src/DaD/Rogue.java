package DaD;

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
        String message="$"+super.rest();
        energyRemaining=Math.min(energyRemaining+10,100);
        message+="Energy gained: 20\n$";
        return message;
    }

    @Override
    public Pair<LinkedList<Enemy>,String> castAbility() {
        LinkedList<Enemy> killed = new LinkedList<>();
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
        String message="$";
        message+=super.LVLUP();
        message+="for being a Rogue, extra stats gain:\n";
        energyRemaining = 100;
        message+="Current Energy -  100\n";
        attackPoints+=3*LVL;
        message+="Attack Points - "+3*LVL +"\n";
        return message+'$';
    }
    @Override
    public String tick(LinkedList<Enemy> e){
        {
            energyRemaining=Math.min(energyRemaining+10,100);
            powerRefresh(e);
            String message="";
            while(didLVLUP())
                message+=LVLUP();
            return message;
        }
    }

    @Override
    public String toString(){
        String message=super.toString();
        message+="Energy: "+energyRemaining+"/"+energyPool+"  ";
        message+="Fan of Knives cost: "+energyCost;
        return message+"\n";
    }
    public boolean equals(Object other) {
        if(other instanceof Rogue){
            Rogue rog = (Rogue) other;
            if (this.GetX()== rog.GetX()&&
                    this.GetY()== rog.GetY()&&
                    this.healthAmount==rog.healthAmount&&
                    this.healthPool==rog.healthPool&&
                    this.attackPoints==rog.attackPoints&&
                    this.defencePoints==rog.defencePoints&&
                    this.energyCost==rog.energyCost&&
                    this.energyPool==rog.energyPool&&
                    this.energyRemaining==rog.energyRemaining&&
                    this.visionRange==rog.visionRange&&
                    this.EXP==rog.EXP&&
                    this.LVL== rog.LVL)
            {
                return true;
            }
        }
        return false;
    }
}

