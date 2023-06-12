import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Warrior extends Player{
    protected int cd;
    protected int cdRemaining;

    public Warrior(int X,int Y,int CD,int HealthPool,int AttackPoints,int DefencePoints,String Name) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        cd=CD;
        cdRemaining=0;
    }

    public int GetResourcePool(){return cd;}
    public void SetResourcePool(int value){cd=value;}

    public int GetResourceRemaining(){return cdRemaining;}
    public void SetResourceRemaining(int value){cdRemaining=value;}

    public Pair<LinkedList<Enemy>,String> cast(){
        LinkedList<Enemy> killed = new LinkedList<>();
        if(cdRemaining==0){
            cdRemaining=cd;
            Random r = new Random();
            int randomEnemyIndex = r.nextInt(power.size());
            Iterator<Enemy> iter = power.iterator();
            for(int i=0;i<randomEnemyIndex;iter.next());
            Enemy e=iter.next();
            e.reduceHealth(0.1*healthPool);
            if(e.isDead()) {
                power.remove(e);
                killed.addFirst(e);
            }
            healthAmount=Math.min(healthPool,healthAmount+defencePoints*10);
            return new Pair<LinkedList<Enemy>,String>(killed,"GO GO Avenger’s Shield!");
        }
        return new Pair<LinkedList<Enemy>,String>(killed,"fail,cooldown Remaining:"+ cdRemaining);
    }
    @Override
    public String LVLUP(){
        String messege="";
        messege=super.LVLUP();
        messege+="for being a Warrior, extra stats gain:\n";
        cdRemaining=0;
        healthPool+= 5*LVL;
        messege+="Max Health:"+ 5*LVL+"\n";
        healthAmount=healthPool;
        attackPoints+=2*LVL;
        messege+="Attack points:"+ 2*LVL+"\n";
        defencePoints+=LVL;
        messege+="Defence points:"+ 2*LVL+"\n";
        return messege;
    }



    @Override
    public String tick(LinkedList<Enemy> e){
        cdRemaining=Math.max(0,cdRemaining-1);
        powerRefresh(e);
        String send="";
        while(didLVLUP())
            send+=LVLUP();
        return send;
    }

    @Override
    public String toString(){
        String s=super.toString();
        s+="Avenger’s Shield CD: "+cdRemaining+"/"+cd;
        return s;
    }
}
