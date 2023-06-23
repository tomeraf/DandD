package Backend;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Mage extends Player {
    protected int manaPool;
    protected int manaRemaining;
    protected  int manaCost;
    protected int spellPower;
    protected int hitsCount;

    public Mage(int X,int Y,int ManaPool,int ManaCost,int SpellPower,int HitsCount,int HealthPool,int AttackPoints,int DefencePoints,int Range,String Name,PrintInStyle printInStyle) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name,printInStyle);
        manaPool=ManaPool;
        manaRemaining=manaPool/4;
        manaCost=ManaCost;
        spellPower=SpellPower;
        hitsCount=HitsCount;
        visionRange=Range;

    }
    @Override
    public int GetResourcePool(){return manaPool;}
    @Override
    public void SetResourcePool(int value){manaPool=value;}
    @Override
    public int GetResourceRemaining(){return manaRemaining;}
    @Override
    public void SetResourceRemaining(int value){manaRemaining=value;}

    @Override
    public void rest(){
        String messege="$";
        super.rest();
        manaRemaining=Math.min(manaPool,manaRemaining+LVL);
        messege+="Mana gained: "+LVL*2+"\n$";
        printInStyle.print(messege);
    }


    @Override
    public LinkedList<Enemy> castAbility(){
        LinkedList<Enemy> killed = new LinkedList<>();
        manaRemaining-=manaCost+LVL;
        if(!power.isEmpty()) {
            int hits = 0;
            while (hits != hitsCount && !power.isEmpty()) {
                Random r = new Random();
                int randomEnemyIndex = r.nextInt(power.size());
                Iterator<Enemy> iter = power.iterator();
                for (int i = 0; i < randomEnemyIndex ; iter.next(),i++);
                Enemy e = iter.next();
                e.attacked(spellPower);
                if (e.isDead()) {
                    this.EXPGain(e.EXPgain);
                    power.remove(e);
                    killed.addFirst(e);

                }//if dead enemy
                hits++;
            }//while hits
        }//if power>0
        printInStyle.print("hocus pocus,Blizzard apearus");
        return killed;
    }
    @Override
    public void LVLUP(){
        String messege="$$";
        super.LVLUP();
        messege+="for being a Mage, extra stats gain:\n";
        manaPool+=25*LVL;
        messege+="Max Mana - "+ 25*LVL+"\n";
        manaRemaining = Math.min(manaRemaining+manaPool/4,manaPool);
        messege+="Current Mana - "+ Math.min(manaRemaining+manaPool/4,manaPool)+"\n";
        spellPower+=10*LVL;
        messege+="Spell Power - "+ 10*LVL+"\n";
        printInStyle.print(messege+'$');
    }
    @Override
    public void tick(LinkedList<Enemy> e){

        manaRemaining=Math.min(manaPool,manaRemaining+LVL);
        powerRefresh(e);
        String messege="";
        while(didLVLUP())
            LVLUP();
        printInStyle.print(messege);
    }

    @Override
    public String toString(){
        String messege=super.toString();
        messege+="Mana: "+manaRemaining+"/"+manaPool+"  ";
        messege+="Spell power: "+spellPower+"  ";
        messege+="Range of Blizard: "+visionRange+"  ";
        messege+="Blizard's cost: "+ manaCost;
        return messege+"\n";
    }
    public boolean equals(Object other) {
        if(other instanceof Mage){
            Mage mage = (Mage) other;
            if (this.GetX()== mage.GetX()&&
                    this.GetY()== mage.GetY()&&
                    this.healthAmount==mage.healthAmount&&
                    this.healthPool==mage.healthPool&&
                    this.attackPoints==mage.attackPoints&&
                    this.defencePoints==mage.defencePoints&&
                    this.manaCost==mage.manaCost &&
                    this.manaPool==mage.manaPool &&
                    this.spellPower==mage.spellPower &&
                    this.hitsCount == mage.hitsCount &&
                    this.manaRemaining==mage.manaRemaining&&
                    this.visionRange==mage.visionRange&&
                    this.EXP==mage.EXP&&
                    this.LVL== mage.LVL)
            {
                return true;
            }
        }
        return false;
    }

    public boolean canCast(){
        if (manaRemaining<manaCost){
            printInStyle.print("fail,not enough mana - " + manaRemaining + "/"+ manaCost);
            return false;
        } else {
            return true;
        }
    }
}
