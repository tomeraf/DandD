package Backend;

import java.util.LinkedList;

public class Rogue extends Player {
    protected int energyPool;
    protected int energyRemaining;
    protected int energyCost;

    public Rogue(int X, int Y,int EnergyCost,int HealthPool,int AttackPoints,int DefencePoints,String Name,PrintInStyle printInStyle) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name,printInStyle);
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
    public void rest(){
        String message="$";
        super.rest();
        energyRemaining=Math.min(energyRemaining+10,100);
        message+="Energy gained: 20\n$";
        printInStyle.print(message);
    }

    @Override
    public LinkedList<Enemy> castAbility() {
        LinkedList<Enemy> killed = new LinkedList<>();
        energyRemaining -= energyCost+10;
        for(Enemy e:power) {
            e.attacked(attackPoints);
            if (e.isDead()) {
                this.EXPGain(e.EXPgain);
                power.remove(e);
                killed.addFirst(e);
            }
        }
        printInStyle.print("i have only 1 fan of my Knives,he is OnlyFan of Knives");
        return killed;
    }
    @Override
    public void LVLUP(){
        String message="$$";
        super.LVLUP();
        message+="for being a Rogue, extra stats gain:\n";
        energyRemaining = 100;
        message+="Current Energy -  100\n";
        attackPoints+=3*LVL;
        message+="Attack Points - "+3*LVL +"\n";
        printInStyle.print(message+'$');
    }
    @Override
    public void tick(LinkedList<Enemy> e){
        {
            energyRemaining=Math.min(energyRemaining+10,100);
            powerRefresh(e);
            String message="";
            while(didLVLUP())
                LVLUP();
            printInStyle.print(message);
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
    public boolean canCast(){
        if (energyRemaining<energyCost){
            printInStyle.print("fail,not enough energy - "+energyRemaining+"/"+energyCost);
            return false;
        } else {
            return true;
        }
    }
}

