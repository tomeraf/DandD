package DaD;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Warrior extends Player {
    protected int cd;
    protected int cdRemaining;
    public Warrior(int X,int Y,int CD,int HealthPool,int AttackPoints,int DefencePoints,String Name) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        cd=CD;
        cdRemaining=0;
        visionRange=3;
    }
    @Override
    public int GetResourcePool(){return cd;}
    @Override
    public void SetResourcePool(int value){cd=value;}
    @Override
    public int GetResourceRemaining(){return cdRemaining;}
    @Override
    public void SetResourceRemaining(int value){cdRemaining=value;}
    @Override
    public String rest(){
        String message="$"+super.rest();
        cdRemaining=Math.max(0,cdRemaining-1);
        message+="CD got lower by 2\n$";
        return message;
    }
    @Override
    public Pair<LinkedList<Enemy>,String> castAbility(){
        LinkedList<Enemy> killed = new LinkedList<>();
        if(cdRemaining==0){
            cdRemaining=cd+1;
            if(!power.isEmpty()) {
                Random r = new Random();
                int randomEnemyIndex = r.nextInt(power.size());
                Iterator<Enemy> iter = power.iterator();
                for (int i = 0; i < randomEnemyIndex; i++,iter.next()) ;
                Enemy e = iter.next();
                e.reduceHealth(0.1 * healthPool);
                if (e.isDead()) {
                    this.EXPGain(e.EXPgain);
                    power.remove(e);
                    killed.addFirst(e);
                }
            }
            healthAmount=Math.min(healthPool,healthAmount+defencePoints*10);
            return new Pair<>(killed, "GO GO Avenger’s Shield!");
        }
        return new Pair<>(killed, "fail,cooldown Remaining:" + cdRemaining);
    }
    @Override
    public String LVLUP(){
        String message="$";
        message+=super.LVLUP();
        message+="for being a Warrior, extra stats gain:\n";
        cdRemaining=0;
        healthPool+= 5*LVL;
        message+="Max Health - "+ 5*LVL+"\n";
        healthAmount=healthPool;
        attackPoints+=2*LVL;
        message+="Attack points - "+ 2*LVL+"\n";
        defencePoints+=LVL;
        message+="Defence points - "+ LVL+"\n";
        return message+"\n$";
    }
    @Override
    public String tick(LinkedList<Enemy> e){
        cdRemaining=Math.max(0,cdRemaining-1);
        powerRefresh(e);
        String message="";
        while(didLVLUP())
            message+=LVLUP();
        return message;
    }
    @Override
    public String toString(){
        String s=super.toString();
        if(x!=0)
            s+="Avenger’s Shield CD: "+cdRemaining+"/"+cd;
        return s+"\n";
    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Warrior){
            Warrior war = (Warrior) other;
            if (this.GetX()== war.GetX()&&
                this.GetY()== war.GetY()&&
                this.cdRemaining==war.cdRemaining&&
                this.healthPool==war.healthPool&&
                this.cd==war.cd&&
                this.visionRange== war.visionRange&&
                this.healthAmount==war.healthAmount&&
                this.attackPoints==war.attackPoints&&
                this.defencePoints==war.defencePoints&&
                this.EXP== war.EXP&&
                this.LVL== war.LVL)

            {
                    return true;
            }
        }
        return false;
    }
}
