import java.util.LinkedList;
import java.util.Random;

abstract public class Player extends Unit {
    char sign = '@';
    protected int EXP;
    protected int LVL;

    protected int visionRange;

    protected LinkedList<Enemy> power;

    public Player(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints,String Name){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        EXP=0;
        LVL=1;
        visionRange=0;
        power=new LinkedList<Enemy>();
    }
    public void powerRefresh(LinkedList<Enemy> e){
        power=new LinkedList<Enemy>();
        for(Enemy enemy:e)
            if(isInRange(enemy,visionRange))
                power.add(enemy);

    }
    public int GetEXP(){return EXP;}
    public void SetEXP(int value){EXP=value;}

    public int GetLVL(){return LVL;}
    public void SetLVL(int value){LVL=value;}

    abstract public int GetResourcePool();
    abstract void SetResourcePool(int value);

    abstract public int GetResourceRemaining();
    abstract public void SetResourceRemaining(int value);

    public int AddEXP(int amount){
        EXP+=amount;
        return EXP;
    }

    public boolean isDead(){
        return healthAmount==0;
    }

    abstract public Pair<LinkedList<Enemy>,String> cast();
    public String LVLUP(){
        String messege="";
            messege+="Level Up!\n new Level:";
            EXP-=LVL*50;
            LVL++;
            messege+=LVL+"stats gained:\n";
            healthPool+=10*LVL;
            messege+="Max Health:"+ 10*LVL+"\n";
            healthAmount=healthPool;
            attackPoints+=4*LVL;
            messege+="Attack points:"+ 4*LVL+"\n";
            defencePoints+=LVL;
            messege+="Defence points:"+ LVL+"\n";
        return messege;
    }
    @Override
    public String toString(){
        String messege=super.toString();
        messege+="EXP: "+ EXP+" \\"+50*LVL+"  ";
        messege+="LVL: "+ LVL+"  ";
        return messege+"\n";
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


    protected boolean didLVLUP(){
        return EXP>=50*LVL;
    }

    abstract public String tick(LinkedList<Enemy> e);
    public Pair<Unit,String> attack(Unit u){
        return u.accept(this);
    }
    public Pair<Unit,String> attack(Enemy e){
        return e.accept(this);
    }
    public Pair<Unit,String> attack(Tiles t){
        return t.accept(this);
    }
    public Pair<Unit,String> attack(Player p){
        return p.accept(this);
    }
    public Pair<Unit,String> attack(Wall w){
        return w.accept(this);
    }
    public Pair<Unit,String> attack(Empty empty){
        return empty.accept(this);
    }
    public Pair<Unit,String> accept(Unit u){
        return new Pair<Unit,String>(null,"");
    }
    public Pair<Unit,String> accept(Player p){
        return new Pair<Unit,String>(null,"");
    }
    public Pair<Unit,String> accept(Enemy e){
        String messege =this.toString()+e.toString();
        Random random = new Random();
        int monsterAttackPower = random.nextInt(e.attackPoints);
        messege+=this.attacked(monsterAttackPower);
        return new Pair<Unit,String>(this,messege);
    }
    public String attacked(int monsterAttackPower) {
        String messege = "";
        Random random = new Random();
        int playerDefense = random.nextInt(this.defencePoints);
        double damage = this.reduceHealth(monsterAttackPower-playerDefense);
        messege +="combat info:\nattack roll: "+monsterAttackPower+"\ndefense roll: "+playerDefense+
                "\ndamage: "+damage+"\n";
        if (this.isDead()){
            messege+= "YOU DIED\n";
            this.sign = 'X';
        }
        return messege;
    }
}
