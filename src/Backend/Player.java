package Backend;

import utilites.Pair;

import java.util.LinkedList;
import java.util.Random;

abstract public class Player extends Unit implements HeroicUnit {
    protected int EXP;
    protected int LVL;
    protected int visionRange;
    protected LinkedList<Enemy> power;
    protected  int HPpotions;


    public Player(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints,String Name,PrintInStyle printInStyle){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name, printInStyle);
        EXP=0;
        LVL=1;
        visionRange=0;
        power= new LinkedList<>();
        sign='@';
        HPpotions=0;
    }
    public void powerRefresh(LinkedList<Enemy> e){
        power= new LinkedList<>();
        for(Enemy enemy:e)
            if(isInRange(enemy,visionRange))
                power.add(enemy);

    }

    public LinkedList<Enemy> GetPower(){return power;}
    public int GetEXP(){return EXP;}
    public void SetEXP(int value){EXP=value;}
    public int GetLVL(){return LVL;}
    public void SetLVL(int value){LVL=value;}
    abstract public int GetResourcePool();
    abstract void SetResourcePool(int value);
    abstract public int GetResourceRemaining();
    abstract public void SetResourceRemaining(int value);
    public int GetHPPotions(){return HPpotions;}
    public void SetHPotions(int value){HPpotions=value;}
    public int AddHPPotions(int amount){
        HPpotions+=amount;
        return EXP;
    }
    public int AddEXP(int amount){
        EXP+=amount;
        return EXP;
    }

    public abstract boolean canCast();
    abstract public LinkedList<Enemy> castAbility();
    public void LVLUP(){
        String message="$$";
        message+="Level Up!\nnew Level - ";
        EXP-=LVL*50;
        LVL++;
        message+=LVL+" stats gained: \n";
        healthPool+=10*LVL;
        message+="Max Health - "+ 10*LVL+"\n";
        healthAmount=healthPool;
        attackPoints+=4*LVL;
        message+="Attack points - "+ 4*LVL+"\n";
        defencePoints+=LVL;
        message+="Defence points - "+ LVL+"\n";
        printInStyle.print(message);
    }
    public Pair<Integer,Integer> move(char input){
        if (input == 'a'){
            return new Pair<>(this.GetX()-1,this.GetY());
        }
        if (input == 's'){
            return new Pair<>(this.GetX(),this.GetY()+1);
        }
        if (input == 'w'){
            return new Pair<>(this.GetX(),this.GetY()-1);
        }
        if (input == 'd'){
            return new Pair<>(this.GetX()+1,this.GetY());
        }
        throw new RuntimeException("fault in move function player");
    }
     public void rest(){
        String message="$$Rest HP gain "+LVL+" HP back: \n$";
        increaseHealth(LVL);
        printInStyle.print(message);
     }
     public void drinkHPPotion(){
         String message="$$You drink HP Potion and gain "+LVL*5+" HP back: \n$";
         increaseHealth(LVL*5);
         HPpotions--;
         printInStyle.print(message);
     }
    protected boolean didLVLUP(){
        return EXP>=50*LVL;
    }
    abstract public void tick(LinkedList<Enemy> e);
    @Override
    public Unit attack(Unit u){
        return u.accept(this);
    }
    @Override
    public Unit attack(Enemy e){
        return e.accept(this);
    }
    @Override
    public Unit attack(Tiles t){
        return t.accept(this);
    }
    @Override
    public Unit attack(Player p){
        return p.accept(this);
    }
    @Override
    public Unit attack(Wall w){
        return w.accept(this);
    }
    @Override
    public Unit attack(Empty empty){
        return empty.accept(this);
    }
    @Override
    public Unit accept(Unit u){
        return null;
    }
    @Override
    public Unit accept(Player p){
        return null;
    }
    @Override
    public Unit accept(Enemy e){
        String typeOfAttack= "attacking us";
        if(e.shooting) {
            typeOfAttack="shooting us";
        }
        String message ="$enemy "+e.name+ " is "+typeOfAttack+"\n$" + this.combatString()+ e.name + " stats:\n "+e+"\n";
        Random random = new Random();
        int monsterAttackPower = random.nextInt(e.attackPoints);
        printInStyle.print(message);
        this.attacked(monsterAttackPower);
        return this;
    }
    public void attacked(int monsterAttackPower) {
        String message = "";
        Random random = new Random();
        int playerDefense = random.nextInt(this.defencePoints);
        double damage= Math.max(0,monsterAttackPower - defencePoints);
        this.reduceHealth(damage);
        message +="$combat info:\nattack roll: "+monsterAttackPower+"\ndefense roll: "+playerDefense+
                "\ndamage: "+damage+"\n$";
        if (this.isDead()) {
            message += "$YOU DIED\n";
            this.sign = 'X';
        }
        printInStyle.print(message);
    }
    public void EXPGain(int EXP){
        this.EXP+=EXP;
    }
    public String combatString(){
        String message="my Combat stats:\n" + super.toString();
        return message+"\n";
    }
    public boolean CanDrink(){
        if(HPpotions==0) {
            printInStyle.print("No HP Potions left");
            return false;
        }
        else
            return true;
    }
    @Override
    public String toString(){

        String messege=name+" stats:\n" + super.toString();
        if(x!=0) {
            messege += "EXP: " + EXP + " \\" + 50 * LVL + "  ";
            messege += "LVL: " + LVL + "  ";
            messege += "HP Potions: " + HPpotions + "  ";
        }
        return messege+"\n";
    }
}
