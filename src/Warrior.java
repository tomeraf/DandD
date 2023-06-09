import java.util.LinkedList;

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

    public String cast(){
        if(cdRemaining==0){
            cdRemaining=cd;
            //need to implemet the hit enemy, maybe in the UI
            healthAmount=Math.min(healthPool,healthAmount+defencePoints*10);
            return "GO GO Avenger’s Shield!";
        }
        return "fail,cooldown Remaining:"+ cdRemaining;
    }
    @Override
    public String LVLUP(){
        String send="";
        send=super.LVLUP();
        send+="for being a Warrior, extra stats gain:\n";
        cdRemaining=0;
        healthPool+= 5*LVL;
        send+="Max Health:"+ 5*LVL+"\n";
        healthAmount=healthPool;
        attackPoints+=2*LVL;
        send+="Attack points:"+ 2*LVL+"\n";
        defencePoints+=LVL;
        send+="Defence points:"+ 2*LVL+"\n";
        return send;
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
