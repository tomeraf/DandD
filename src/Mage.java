import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Mage extends Player {
    protected int manaPool;
    protected int manaRemaining;
    protected  int manaCost;
    protected int spellPower;
    protected int hitsCount;

    public Mage(int X,int Y,int ManaPool,int ManaCost,int SpellPower,int HitsCount,int HealthPool,int AttackPoints,int DefencePoints,int Range,String Name) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
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
    public Pair<LinkedList<Unit>,String> castAbility(Player p){
        LinkedList<Unit> killed = new LinkedList<>();
        if(manaRemaining>=manaCost){
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

            return new Pair<LinkedList<Unit>,String>(killed,"hocus pocus,Blizzard apearus");
        }
        return new Pair<LinkedList<Unit>,String>(killed,"fail,not enough mana");
    }
    @Override
    public String LVLUP(){
        String messege="$";
        messege+=super.LVLUP();
        messege+="for being a Mage, extra stats gain:\n";
        manaPool+=25*LVL;
        messege+="Max Mana - "+ 25*LVL+"\n";
        manaRemaining = Math.min(manaRemaining+manaPool/4,manaPool);
        messege+="Current Mana - "+ Math.min(manaRemaining+manaPool/4,manaPool)+"\n";
        spellPower+=10*LVL;
        messege+="Spell Power - "+ 10*LVL+"\n";
        return messege+'$';
    }
    @Override
    public String tick(LinkedList<Enemy> e){

        manaRemaining=Math.min(manaPool,manaRemaining+LVL);
        powerRefresh(e);
        String messege="";
        while(didLVLUP())
            messege+=LVLUP();
        return messege;
    }

    @Override
    public String toString(){
        String messege=super.toString();
        messege+="Mana: "+manaRemaining+"/"+manaPool+"  ";
        messege+="Spell power: "+spellPower+"  ";
        messege+="Blizard's cost: "+ manaCost;
        return messege+"\n";
    }

}
