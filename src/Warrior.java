public class Warrior extends Player{
    protected int cd;
    protected int cdRemaining;
    public Warrior(int X,int Y,int CD,int HealthPool,int AttackPoints,int DefencePoints) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints);
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
    public boolean LVLUP(){
        if(this.LVLUP()) {
            cdRemaining=0;
            healthPool+= 5*LVL;
            healthAmount=healthPool;
            attackPoints+=2*LVL;
            defencePoints+=LVL;
            return true;
        }
        return false;



    }
    public void tick(){
        cdRemaining=Math.max(0,cdRemaining-1);
    }
}
