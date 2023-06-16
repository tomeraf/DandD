import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Hunter extends Player {
    protected int tickCount;
    protected int arrowsCount;

    public Hunter(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints,int Range,String Name) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        arrowsCount=10;
        tickCount=0;
        visionRange=Range;

    }
    @Override
    public int GetResourcePool(){return arrowsCount;}
    @Override
    public void SetResourcePool(int value){arrowsCount=value;}
    @Override
    public int GetResourceRemaining(){return arrowsCount;}
    @Override
    public void SetResourceRemaining(int value){arrowsCount=value;}

    @Override
    public void powerRefresh(LinkedList<Enemy> e){
        super.powerRefresh((e));
        if(!power.isEmpty()) {
            Enemy enemy = power.getFirst();
            for (Enemy enemyPower : power)
                if (range(enemyPower) < range(enemy))
                    enemy=enemyPower;
            power=new LinkedList<Enemy>();
            power.addFirst(enemy);
        }
    }

    @Override
    public Pair<LinkedList<Unit>,String> castAbility(Player p){
        LinkedList<Unit> killed = new LinkedList<>();
        if(arrowsCount==0)
            return new Pair<LinkedList<Unit>,String>(killed,"fail,no arrows left");
        if(!power.isEmpty() ){
            arrowsCount--;
            Enemy e=power.getFirst();
            e.attacked(attackPoints);
            if (e.isDead()) {
                this.EXPGain(e.EXPgain);
                power.remove(e);
                killed.addFirst(e);

            }//if dead enemy
            return new Pair<LinkedList<Unit>,String>(killed,"I AM THE HUNTER!");
        }//if power>0
        return new Pair<LinkedList<Unit>,String>(killed,"fail,no enemy near");
    }
    @Override
    public String LVLUP(){
        String messege="$";
        messege+=super.LVLUP();
        messege+="for being a Hunter, extra stats gain:\n";
        arrowsCount+=10*LVL;
        messege+="Arrow Count - "+ 10*LVL+"\n";
        attackPoints+=2*LVL;
        messege+="Attack points - "+ 2*LVL+"\n";
        defencePoints+=LVL;
        messege+="Defence points - "+ LVL+"\n";
        return messege+'$';
    }
    @Override
    public String tick(LinkedList<Enemy> e){

        if(tickCount==10) {
            arrowsCount += LVL;
            tickCount=0;
        }
        else
            tickCount++;
        powerRefresh(e);
        String messege="";
        while(didLVLUP())
            messege+=LVLUP();
        return messege;
    }

    @Override
    public String toString(){
        String messege=super.toString();
            messege += "Arrow Counter: " + arrowsCount + "\n";
        if(x!=0) {
            messege += "ticks Counts till more Arrows: " + (11-tickCount) + "\n";
            if(power.isEmpty())
                messege += "Nearest enemy to shot: none  ";
            else
                messege += "Nearest enemy to shot: " + power.getFirst().name + "  ";
        }
        return messege+"\n";
    }

}
