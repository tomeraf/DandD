import java.util.LinkedList;

public class Mage extends Player{
    protected int manaPool;
    protected int manaRemaining;
    protected  int manaCost;
    protected int spellPower;
    protected int hitsCount;
    protected  int range;

    public Mage(int X,int Y,int ManaPool,int ManaCost,int SpellPower,int HitsCount,int HealthPool,int AttackPoints,int DefencePoints,int Range,String Name) {
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        manaPool=ManaPool;
        manaRemaining=manaPool/4;
        manaCost=ManaCost;
        spellPower=SpellPower;
        hitsCount=HitsCount;
        range=Range;

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
    public String cast(){
        if(manaRemaining>=manaCost){
            manaRemaining-=Math.max(0,manaRemaining-manaCost);

            //need to implemet the hit enemy, maybe in the UI



            return "hocus pocus,Blizzard apearus";
        }
        return "fail,not enough mana";
    }
    @Override
    public boolean LVLUP(){
        if(this.LVLUP()) {
            manaPool+=25*LVL;
            manaRemaining = Math.min(manaRemaining+manaPool/4,manaPool);
            spellPower+=10*LVL;
            return true;
        }
        return false;
    }
    public void tick(LinkedList<Enemy> e){

        manaRemaining=Math.min(manaPool,manaRemaining+LVL);
        powerRefresh(e);
    }

    public String toString(){
        String s=super.toString();
        s+="Mana: "+manaRemaining+"/"+manaPool+"  ";
        s+="Spell power: "+spellPower+"  ";
        s+="Blizard's cost: "+ manaCost +"  ";
        return s;
    }

}
