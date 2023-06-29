package Backend;

import java.util.LinkedList;

public class Hunter extends Player {
    protected int tickCount;
    protected int arrowsCount;

    public Hunter(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints,int Range,String Name,PrintInStyle printInStyle) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name,printInStyle);
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

    public int GetTickCount() {return tickCount;}
    public void SetTickCount(int value){tickCount=value;}

    @Override
    public void powerRefresh(LinkedList<Enemy> e){
        super.powerRefresh((e));
        if(!power.isEmpty()) {
            Enemy enemy = power.getFirst();
            for (Enemy enemyPower : power)
                if (range(enemyPower) < range(enemy))
                    enemy=enemyPower;
            power= new LinkedList<>();
            power.addFirst(enemy);
        }
    }

    @Override
    public void rest(){
        String messege="$";
        super.rest();
        if(tickCount==10) {
            arrowsCount += LVL;
            tickCount=0;
        }
        else
            tickCount++;
        messege+="tick Counter got lower by 2\n$";
        printInStyle.print(messege);
    }

    @Override
    public LinkedList<Enemy> castAbility(){
        LinkedList<Enemy> killed = new LinkedList<>();
        arrowsCount--;
        Enemy e=power.getFirst();
        e.attacked(attackPoints);
        if (e.isDead()) {
            this.EXPGain(e.EXPgain);
            power.remove(e);
            killed.addFirst(e);

        }//if dead enemy
        printInStyle.print("I AM THE HUNTER!\n");
        return killed;
    }
    @Override
    public void LVLUP(){
        String messege="$$";
        super.LVLUP();
        messege+="for being a Hunter, extra stats gain:\n";
        arrowsCount+=10*LVL;
        messege+="Arrow Count - "+ 10*LVL+"\n";
        attackPoints+=2*LVL;
        messege+="Attack points - "+ 2*LVL+"\n";
        defencePoints+=LVL;
        messege+="Defence points - "+ LVL+"\n";
        printInStyle.print(messege+'$');
    }
    @Override
    public void tick(LinkedList<Enemy> e){

        if(tickCount==10) {
            arrowsCount += LVL;
            tickCount=0;
        }
        else
            tickCount++;
        powerRefresh(e);
        String messege="";
        while(didLVLUP())
            LVLUP();
        printInStyle.print(messege);
    }

    @Override
    public String toString(){
        String messege=super.toString();
        messege += "Arrow Counter: " + arrowsCount + "\n";
        messege+="Range: "+visionRange+"  ";
        if(x!=0) {
            messege += "ticks Counts till more Arrows: " + (11-tickCount) + "\n";
            if(power.isEmpty())
                messege += "Nearest enemy to shot: none  ";
            else
                messege += "Nearest enemy to shot: " + power.getFirst().name + "  ";
        }
        return messege+"\n";
    }
    public boolean equals(Object other) {
        if(other instanceof Hunter){
            Hunter hunter = (Hunter) other;
            if (this.GetX()==hunter.GetX()&&
                    this.GetY()==hunter.GetY()&&
                    this.healthAmount==hunter.healthAmount&&
                    this.healthPool==hunter.healthPool&&
                    this.attackPoints==hunter.attackPoints&&
                    this.defencePoints==hunter.defencePoints&&
                    this.visionRange==hunter.visionRange&&
                    this.arrowsCount==hunter.arrowsCount&&
                    this.EXP==hunter.EXP&&
                    this.tickCount==hunter.tickCount&&
                    this.LVL==hunter.LVL)
            {
                return true;
            }
        }
        return false;
    }

    public boolean canCast(){
        if (arrowsCount==0 || power.isEmpty()){
            if(arrowsCount==0) {
                printInStyle.print("fail,no arrows left");
            } else {
                printInStyle.print("fail,no enemy near");
            }
            return false;
        } else {
            return true;
        }
    }

}
